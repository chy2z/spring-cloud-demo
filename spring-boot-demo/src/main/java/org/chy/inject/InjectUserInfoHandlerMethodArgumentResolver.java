package org.chy.inject;

import org.chy.carorder.entity.CUser;
import org.chy.carorder.entity.MUser;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.PostConstruct;

/**
 * 方法参数解析注入
 */
@Component
public class InjectUserInfoHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private static InjectUserInfoHandlerMethodArgumentResolver injectUserInfoHandlerMethodArgumentResolver;

    @PostConstruct
    public void init() {
        injectUserInfoHandlerMethodArgumentResolver = this;
    }

    /**
     * 允许通过的函数参数类型
     *
     * @param methodParameter 方法参数
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if (methodParameter.hasParameterAnnotation(InjectMUser.class)) {
            return true;
        }
        if (methodParameter.hasParameterAnnotation(InjectCUser.class)) {
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        // 如果参数包含注解InjectCUser
        if (methodParameter.hasParameterAnnotation(InjectCUser.class)) {
            CUser cUser = new CUser();
            cUser.setName("cuser");
            return cUser;
        }
        // 如果参数包含注解InjectCUser
        else if (methodParameter.hasParameterAnnotation(InjectMUser.class)) {
            MUser mUser = new MUser();
            mUser.setName("muser");
            return mUser;
        }
        return null;
    }
}