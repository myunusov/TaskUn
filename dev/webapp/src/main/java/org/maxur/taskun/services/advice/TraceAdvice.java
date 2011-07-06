package org.maxur.taskun.services.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>21.04.11</pre>
 */
@Aspect
@Component
public class TraceAdvice {

    public static final Logger LOGGER = LoggerFactory.getLogger("trace");

    @Pointcut("@annotation(org.maxur.taskun.utils.Trace)")
    private void tracePointcut() {
    }

    @Around("tracePointcut()")
    public Object traceAdvice(ProceedingJoinPoint pjp) throws Throwable {
        Object retVal;
        try {
            LOGGER.debug(pjp.getSignature().toShortString() + " ENTER");
            retVal = pjp.proceed();
        } finally {
            LOGGER.debug(pjp.getSignature().toShortString() + " EXIT");
        }
        return retVal;
      }
}

