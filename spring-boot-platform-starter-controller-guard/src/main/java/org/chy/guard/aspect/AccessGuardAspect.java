package org.chy.guard.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.chy.guard.UserContext;
import org.chy.guard.annotation.AccessGuard;

import java.lang.reflect.Method;


/**
 * 不要被自动扫描到
 *
 * @author admin
 */
@Aspect
public class AccessGuardAspect {

    private final UserContext userContext;

    public AccessGuardAspect(UserContext userContext) {
        this.userContext = userContext;
    }

    @Pointcut("@annotation(org.chy.guard.annotation.AccessGuard)")
    public void guardPointcut() {
    }

    @Around("guardPointcut()")
    public Object invokeResource(ProceedingJoinPoint pjp) throws Throwable {
        if (!userContext.isValid()) {
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            Method method = pjp.getTarget().getClass()
                    .getDeclaredMethod(signature.getName(), signature.getParameterTypes());
            AccessGuard annotation = method.getAnnotation(AccessGuard.class);
            throw new Throwable("访问受保护资源");
        }
        return pjp.proceed();
    }
}