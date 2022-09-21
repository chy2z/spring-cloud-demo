package org.chy.aop;

import com.alibaba.csp.sentinel.context.ContextUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class SentinelFeignFallbackAspect {
  final static Logger logger = LoggerFactory.getLogger(SentinelFeignFallbackAspect.class);

  public static final String FALLBACK_CLASS_FLAG = "FallBack";

  @Pointcut("execution(* org.chy..*.*(..))")
  private void point() {
  }

  @Around("point()")
  public Object doAroundPoint(ProceedingJoinPoint pjp) throws Throwable {
    if (pjp.getSignature().getDeclaringTypeName().endsWith(FALLBACK_CLASS_FLAG)
            && ContextUtil.getContext() != null) {
      logger.error("fallback: ", ContextUtil.getContext().getCurEntry().getError());
    }
    return pjp.proceed();
  }

}