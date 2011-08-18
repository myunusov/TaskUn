package org.maxur.commons.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.maxur.commons.service.ServiceNotifier;
import org.springframework.beans.factory.annotation.Autowired;
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
     * The ServiceNotifier.
     */
    @Autowired
    private ServiceNotifier notifier;
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
        Object retVal;
        try {
            notifier.notifyEnter(pjp.getSignature());
            retVal = pjp.proceed();
        } finally {
            notifier.notifyExit(pjp.getSignature());
        }
        return retVal;
      }
}

