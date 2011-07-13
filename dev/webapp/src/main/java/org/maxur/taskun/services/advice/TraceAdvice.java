package org.maxur.taskun.services.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
/**
 * The Trace Advice. It processes tracing on annotated method.
 *
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>21.04.11</pre>
 */
@Aspect
@Component
public class TraceAdvice {

    /**
     * The Logger.
     */
    public static final Logger LOGGER = LoggerFactory.getLogger("trace");

    /**
     * The Pointcut for tracing methods.
     */
    @SuppressWarnings("unused")
    @Pointcut("@annotation(org.maxur.taskun.utils.Trace)")
    private void tracePointcut() {
    }

    /**
     * This method processes tracing on annotated method.
     * @param pjp  The Proceeding Join Point.
     * @return The Object which returned processed method.
     * @throws Throwable The Throwable which throws processed method.
     */
    @Around("tracePointcut()")
    public Object traceAdvice(final ProceedingJoinPoint pjp) throws Throwable {
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

