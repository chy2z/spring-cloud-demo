package org.chy.aop;

import com.google.common.collect.Sets;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.chy.annotation.PrintLog;
import org.chy.annotation.PrintLogIgnore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.logging.LogLevel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author chy
 * @Title: 日志打印
 * @Description:
 * @date 2022/9/21 21:45
 */
@Order(10)
@Aspect
@Component
public class PrintLogAdvice {
    private final static Logger LOGGER = LoggerFactory.getLogger(PrintLogAdvice.class);

    public static final int ZERO = 0;

    //@Value("${sys.log.level:INFO}")
    private String level="INFO";

    //@Value("${sys.log.size:65535}")

    private Integer size=65535;

    //@Value("${sys.log.toggle:ON}")
    private String toggle="ON";

    @Pointcut("execution(* org.chy..*.*(..))")
    public void logAdvice() {

    }

    @Before("logAdvice()")
    public void before() {
    }

    @Around("logAdvice()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTimeMillis = System.currentTimeMillis();
        Object object = joinPoint.proceed();
        long endTimeMillis = System.currentTimeMillis();
        long cost = endTimeMillis - startTimeMillis;
        try {
            printLog(joinPoint, cost, object);
        } catch (Exception e) {
            LOGGER.error("printLog error:{}", e);
        }
        return object;
    }

    private void printLog(ProceedingJoinPoint joinPoint, long cost, Object object) {
        if ("ON".equals(toggle)) {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            Object[] args = joinPoint.getArgs();
            Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            Set<Annotation> mappingSet = getMappingSet(method);
            boolean feignClient = isFeignClient(joinPoint);
            if (feignClient) {
                if (isFeignClientImpl(joinPoint)) {
                    // 这里的Set 只有一个值，所有循环没有问题
                    mappingSet.stream().forEach(p -> {
                        if (p instanceof PostMapping) {
                            PostMapping postMapping = (PostMapping) p;
                            if (postMapping.path().length > ZERO) {
                                printRestLog(postMapping.path()[ZERO], args, parameterAnnotations, cost, object);
                            }

                        }
                        if (p instanceof RequestMapping) {
                            RequestMapping requestMapping = (RequestMapping) p;
                            if (requestMapping.path().length > ZERO) {
                                printRestLog(requestMapping.path()[ZERO], args, parameterAnnotations, cost, object);
                            }
                        }
                        if (p instanceof GetMapping) {
                            GetMapping getMapping = (GetMapping) p;
                            if (getMapping.path().length > ZERO) {
                                printRestLog(getMapping.path()[ZERO], args, parameterAnnotations, cost, object);
                            }
                        }
                        if (p instanceof DeleteMapping) {
                            DeleteMapping deleteMapping = (DeleteMapping) p;
                            if (deleteMapping.path().length > ZERO) {
                                printRestLog(deleteMapping.path()[ZERO], args, parameterAnnotations, cost, object);
                            }
                        }
                        if (p instanceof PutMapping) {
                            PutMapping putMapping = (PutMapping) p;
                            if (putMapping.path().length > ZERO) {
                                printRestLog(putMapping.path()[ZERO], args, parameterAnnotations, cost, object);
                            }
                        }
                    });
                }

            }
            if (!feignClient && !CollectionUtils.isEmpty(mappingSet) && Objects.nonNull(getRequest())) {
                String requestURI = getRequest().getRequestURI();
                printRestLog(requestURI, args, parameterAnnotations, cost, object);

            }

            PrintLog annotation = AnnotationUtils.getAnnotation(method, PrintLog.class);
            if (Objects.nonNull(annotation)) {
                Map<String, Object> annotationAttributes = AnnotationUtils
                        .getAnnotationAttributes(annotation);
                Set<String> strings = annotationAttributes.keySet();
                StringBuilder stringBuilder = new StringBuilder();
                for (String key : strings) {
                    stringBuilder.append(key).append("=").append(annotationAttributes.get(key)).append(",");
                }
                stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
                Object annotationLevel = annotationAttributes.get("level");
                if (Objects.isNull(level)) {
                    printAnnotationLog(method, args, parameterAnnotations, cost, annotationLevel.toString(),
                            stringBuilder);
                } else {
                    printAnnotationLog(method, args, parameterAnnotations, cost, level, stringBuilder);
                }

            }
        }
    }

    private void printAnnotationLog(Method method, Object[] args, Annotation[][] parameterAnnotations,
                                    long cost, String logLevel, StringBuilder stringBuilder) {
        if (LogLevel.DEBUG.toString().equals(logLevel)) {
            LOGGER.debug("方法:{},参数:{},耗时:{},注解参数:{}", method.getName(),
                    getArgsToStr(args, parameterAnnotations), cost, stringBuilder.toString());
        }
        if (LogLevel.INFO.toString().equals(logLevel)) {
            LOGGER.info("方法:{},参数:{},耗时:{},注解参数:{}", method.getName(),
                    getArgsToStr(args, parameterAnnotations), cost, stringBuilder.toString());
        }
        if (LogLevel.WARN.toString().equals(logLevel)) {
            LOGGER.warn("方法:{},参数:{},耗时:{},注解参数:{}", method.getName(),
                    getArgsToStr(args, parameterAnnotations), cost, stringBuilder.toString());
        }
        if (LogLevel.ERROR.toString().equals(logLevel)) {
            LOGGER.error("方法:{},参数:{},耗时:{},注解参数:{}", method.getName(),
                    getArgsToStr(args, parameterAnnotations), cost, stringBuilder.toString());
        }
    }

    private void printRestLog(String requestURI, Object[] args, Annotation[][] parameterAnnotations,
                              long cost, Object object) {
        if (LogLevel.DEBUG.toString().equals(level)) {
            LOGGER.debug("接口:{},参数:{},耗时:{},响应结果:{}", requestURI,
                    getArgsToStr(args, parameterAnnotations), cost, getArgsToStr(object));
        }
        if (LogLevel.INFO.toString().equals(level)) {
            LOGGER.info("接口:{},参数:{},耗时:{},响应结果:{}", requestURI,
                    getArgsToStr(args, parameterAnnotations), cost, getArgsToStr(object));
        }
        if (LogLevel.WARN.toString().equals(level)) {
            LOGGER.warn("接口:{},参数:{},耗时:{},响应结果:{}", requestURI,
                    getArgsToStr(args, parameterAnnotations), cost, getArgsToStr(object));
        }
        if (LogLevel.ERROR.toString().equals(level)) {
            LOGGER.error("接口:{},参数:{},耗时:{},响应结果:{}", requestURI,
                    getArgsToStr(args, parameterAnnotations), cost, getArgsToStr(object));
        }
    }

    /**
     * 判定是不是FeignClient 接口
     *
     * @param joinPoint
     * @return
     */
    private boolean isFeignClient(ProceedingJoinPoint joinPoint) {
        boolean flag = false;
        List<Class<?>> list = new ArrayList<>();
        Class<?>[] interfaces = joinPoint.getTarget().getClass().getInterfaces();
        list.addAll(Arrays.asList(interfaces));
        Class<?> superClass = joinPoint.getTarget().getClass();
        if (Objects.nonNull(superClass)) {
            Class<?>[] superClassInterfaces = superClass.getSuperclass().getInterfaces();
            list.addAll(Arrays.asList(superClassInterfaces));
        }
        for (Class<?> cls : list) {
            FeignClient annotation = cls.getAnnotation(FeignClient.class);
            if (annotation != null) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    /**
     * 判断是不是FeignClient 实现类
     *
     * @param joinPoint
     * @return
     */
    private boolean isFeignClientImpl(ProceedingJoinPoint joinPoint) {
        Class<?> clazz = joinPoint.getTarget().getClass();
        RestController restController = clazz.getAnnotation(RestController.class);
        Controller controller = clazz.getAnnotation(Controller.class);
        if (Objects.nonNull(restController) || Objects.nonNull(controller)) {
            return true;
        }
        return false;
    }

    private String getArgsToStr(Object[] args, Annotation[][] parameterAnnotations) {

        if (Objects.isNull(args) || Objects.isNull(parameterAnnotations)) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            Object param = args[i];
            Annotation[] paramAnn = parameterAnnotations[i];
            if (!containsPrintLogIgnore(paramAnn)) {
                if (Objects.nonNull(param)) {
                    if (Objects.nonNull(param.getClass())) {
                        if (!param.getClass().getName().startsWith("java.io.File")) {
                            stringBuilder.append(param.toString()).append(",");
                        }
                    }
                } else {
                    stringBuilder.append(param).append(",");
                }
            }
        }
        if (stringBuilder.lastIndexOf(",") != -1) {
            stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        }
        String toString = stringBuilder.toString();
        if (toString.length() > size) {
            return toString.substring(0, size);
        }
        return toString;
    }

    private Boolean containsPrintLogIgnore(Annotation[] paramAnn) {
        boolean flag = false;
        if (Objects.isNull(paramAnn) || paramAnn.length == 0) {
            return flag;
        }
        for (Annotation pAnnotation : paramAnn) {
            if (pAnnotation.annotationType().equals(PrintLogIgnore.class)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    private String getArgsToStr(Object args) {

        if (Objects.isNull(args)) {
            return null;
        }
        String string = args.toString();
        if (string.length() > size) {
            return string.substring(0, size);
        }
        return string;
    }

    private Set<Annotation> getMappingSet(Method method) {
        Set<Annotation> mappingSet = Sets.newHashSet();
        PostMapping postMapping = AnnotationUtils.findAnnotation(method, PostMapping.class);
        if (Objects.nonNull(postMapping)) {
            mappingSet.add(postMapping);
        }
        RequestMapping requestMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
        if (Objects.nonNull(requestMapping)) {
            mappingSet.add(requestMapping);
        }
        PatchMapping patchMapping = AnnotationUtils.findAnnotation(method, PatchMapping.class);
        if (Objects.nonNull(patchMapping)) {
            mappingSet.add(patchMapping);
        }
        DeleteMapping deleteMapping = AnnotationUtils.findAnnotation(method, DeleteMapping.class);
        if (Objects.nonNull(deleteMapping)) {
            mappingSet.add(deleteMapping);
        }
        PutMapping putMapping = AnnotationUtils.findAnnotation(method, PutMapping.class);
        if (Objects.nonNull(putMapping)) {
            mappingSet.add(putMapping);
        }
        GetMapping getMapping = AnnotationUtils.findAnnotation(method, GetMapping.class);
        if (Objects.nonNull(getMapping)) {
            mappingSet.add(getMapping);
        }
        return mappingSet;
    }

    public static HttpServletRequest getRequest() {
        if (Objects.isNull(getRequestAttributes())) {
            return null;
        }
        return getRequestAttributes().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    public static ServletRequestAttributes getRequestAttributes() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
    }

    public static ServletContext getServletContext() {
        return ContextLoader.getCurrentWebApplicationContext().getServletContext();
    }
}