package org.chy.datasource.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.chy.datasource.annotation.DataSource;
import org.chy.datasource.DataSourceContextHolder;
import org.chy.datasource.DataSourceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 通过方法注解确定数据源的key,写入当前线程的共享变量DataSourceContextHolder
 */
@Component
@Aspect
public class DynamicDataSourceAspect {
    private final static Logger LOGGER = LoggerFactory.getLogger(DataSourceContextHolder.class);

    @Before("execution(* org.chy.carorder.mapper..*.*(..))||execution(* org.chy.carorder.service..*.*(..))")
    public void before(JoinPoint point) {
        try {
            DataSource annotationOfClass = (DataSource) point.getSignature().getDeclaringType().getAnnotation(DataSource.class);
            String methodName = point.getSignature().getName();
            Class[] parameterTypes = ((MethodSignature) point.getSignature()).getParameterTypes();
            Method method = point.getSignature().getDeclaringType().getMethod(methodName, parameterTypes);
            DataSource methodAnnotation = method.getAnnotation(DataSource.class);
            methodAnnotation = methodAnnotation == null ? annotationOfClass : methodAnnotation;
            DataSourceType dataSourceType = methodAnnotation != null && methodAnnotation.value() != null ? methodAnnotation.value() : DataSourceType.DEFAULT;
            DataSourceContextHolder.setDataSource(dataSourceType.getName());
        } catch (NoSuchMethodException e) {
            LOGGER.error("DynamicDataSourceAspect",e);
        }
    }

    @After("execution(* org.chy.carorder.mapper..*.*(..))||execution(* org.chy.carorder.service..*.*(..))")
    public void after(JoinPoint point) {
        DataSourceContextHolder.clearDataSource();
    }
}
