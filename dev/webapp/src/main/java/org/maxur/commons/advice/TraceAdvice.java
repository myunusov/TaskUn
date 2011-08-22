package org.maxur.commons.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.maxur.commons.service.Logger;
import org.maxur.commons.service.impl.BaseLogger;

/**
/**
 * The Trace Advice. It processes tracing on annotated method.
 *
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>21.04.11</pre>
 */
@Aspect
public class TraceAdvice {

    /**
     * Aspect's ID
     */
    private static final String TRACE = "trace";

    /**
     * The BaseLogger.
     */
    private Logger logger = BaseLogger.instance();


    /**
     * The Pointcut for tracing methods.
     */
    @SuppressWarnings("unused")
    @Pointcut("@annotation(org.maxur.commons.annotation.Trace)")
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
        final Object retVal;
        final Signature signature = pjp.getSignature();
        try {
            notifyEnter(signature);
            retVal = pjp.proceed();
        } finally {
            notifyExit(signature);
        }
        return retVal;
      }

    public final void notifyEnter(final Signature signature) {
        logger.debug(TRACE, signature.toShortString() + " ENTER");
    }

    public final void notifyExit(final Signature signature) {
        logger.debug(TRACE, signature.toShortString() + " EXIT");
    }


}

