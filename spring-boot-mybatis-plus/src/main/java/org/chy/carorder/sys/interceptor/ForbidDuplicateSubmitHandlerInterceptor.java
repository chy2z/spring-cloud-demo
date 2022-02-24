package org.chy.carorder.sys.interceptor;


import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 防止重复提交拦截器
 */
@Component
public class ForbidDuplicateSubmitHandlerInterceptor implements AsyncHandlerInterceptor, ApplicationContextAware {
    private final static Logger LOGGER = LoggerFactory.getLogger(ForbidDuplicateSubmitHandlerInterceptor.class);

    private static ApplicationContext applicationContext = null;

    private final static String LOCK_KEY = "forbidDuplicateSubmitLockKey";

    private final static String EXECUTE_FLAG_KEY = "forbidDuplicateSubmitFlagKey";

    private final static int EXPIRE_TIME = 5;

    private final static int FAIL_TIME = 6;

    public static String FORBID_ERROR_MSG = "请求处理中，请勿重复提交";


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (ForbidDuplicateSubmitHandlerInterceptor.applicationContext == null) {
            ForbidDuplicateSubmitHandlerInterceptor.applicationContext = applicationContext;
        }
    }

    /**
     * 根据请求的userAgent+请求参数做md5（重复提交的请求这个值应该是一致的），作为key做重复提交判断
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception  {
        //方法级注解
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;

            //查看方法上是否有改注解
            ForbidDuplicateSubmit forbidDuplicateSubmit = handlerMethod.getMethod().getAnnotation(ForbidDuplicateSubmit.class);

            if (forbidDuplicateSubmit != null) {

                StringBuffer paramStringBuffer = new StringBuffer();

                String userAgent = request.getHeader("User-Agent");
                if (StringUtils.isNotBlank(userAgent)) {
                    paramStringBuffer.append(userAgent);
                }
                //获取请求体内的参数信息
                Map paraMap = request.getParameterMap();
                if (paraMap != null && paraMap.size() > 0) {
                    Set keSet = paraMap.entrySet();
                    for (Iterator itr = keSet.iterator(); itr.hasNext(); ) {
                        Map.Entry me = (Map.Entry) itr.next();
                        Object ok = me.getKey();
                        Object ov = me.getValue();
                        if (ov != null) {
                            String[] value = new String[1];
                            if (ov instanceof String[]) {
                                value = (String[]) ov;
                            } else {
                                value[0] = ov.toString();
                            }
                            for (int i = 0; i < value.length; i++) {
                                paramStringBuffer.append(ok + "=" + value[i]);
                            }

                        }
                    }
                } else {
                    StringBuffer sb = new StringBuffer();
                    String inputLine = null;
                    BufferedReader bufferedReader = request.getReader();
                    while ((inputLine = bufferedReader.readLine()) != null) {
                        sb.append(inputLine);
                    }
                    if (sb.length() == 0) {
                        LOGGER.error("没有获取到参数，不做重复提交校验!");
                        return false;
                    }
                    paramStringBuffer.append(sb);
                }


                String encryptParamsKey = DigestUtils.md5Hex(paramStringBuffer.toString());

                LOGGER.info("ForbidDuplicateSubmitHandlerInterceptor encryptParamsKey:{}", encryptParamsKey);

                return disLock(encryptParamsKey);
            }
        }

        return true;
    }

    private boolean disLock(String encryptParamsKey) throws Exception {
        RedissonClient redissonClient  = applicationContext.getBean(RedissonClient.class);
        RedisTemplate<Object, Object> redisTemplate = (RedisTemplate<Object, Object>)applicationContext.getBean("redisTemplate");
        if (StringUtils.isNotBlank(encryptParamsKey) && redisTemplate != null) {
            //按请求标识生成分布式锁
            String lockKey = LOCK_KEY.concat(encryptParamsKey);

            //请求执行标识key
            String EXECUTE_FLAG = EXECUTE_FLAG_KEY.concat(encryptParamsKey);

            // redis 分布式锁
            RLock disLock = redissonClient.getLock(lockKey);

            // false:不重复提交
            boolean flag = false;

            try {
                boolean getResult= disLock.tryLock(FAIL_TIME,TimeUnit.SECONDS);

                if(getResult) {
                    Object object = redisTemplate.opsForValue().get(EXECUTE_FLAG);

                    if (object == null) {
                        //第一次初始化
                        redisTemplate.opsForValue().set(EXECUTE_FLAG, true, EXPIRE_TIME, TimeUnit.SECONDS);
                    } else {
                        flag = (boolean) object;

                        // true:说明重复提交
                        if (flag) {
                            LOGGER.error("请求处理中，请勿重复提交");
                            return false;
                        } else {
                            redisTemplate.opsForValue().set(EXECUTE_FLAG, true, EXPIRE_TIME, TimeUnit.SECONDS);
                        }
                    }
                }
                else {
                    LOGGER.error("请求处理中，请勿重复提交");
                    flag = true;
                }
            } catch (Exception e) {
                LOGGER.error("异步锁异常{}", e);
            } finally {
                try {
                    disLock.unlock();
                } catch (Exception e) {
                    LOGGER.error("异步锁lock.unlock异常{}", e);
                }
                // 重复提交在解锁后，抛出异常
                if (flag) {
                    LOGGER.error("请求处理中，请勿重复提交");
                    throw new Exception(new Throwable(FORBID_ERROR_MSG));
                }
            }
        }
        return true;
    }
}