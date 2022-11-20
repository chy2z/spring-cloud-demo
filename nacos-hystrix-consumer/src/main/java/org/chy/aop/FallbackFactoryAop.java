package org.chy.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author chy
 * @Title: 暂时没有用
 * @Description: 暂时没有用
 * @date 2022/11/9 22:31
 */
@Order(10)
@Aspect
@Component
public class FallbackFactoryAop {
    final static Logger logger = LoggerFactory.getLogger(FallbackFactoryAop.class);

    @Pointcut("execution(* org.springframework.cloud.netflix.hystrix..*.*(..))")
    public void fallbackFactoryAdvice() {

    }

    @Before("fallbackFactoryAdvice()")
    public void before() {
    }

    @Around("fallbackFactoryAdvice()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        String name=pjp.getSignature().getDeclaringTypeName();
        return pjp.proceed();
    }
}