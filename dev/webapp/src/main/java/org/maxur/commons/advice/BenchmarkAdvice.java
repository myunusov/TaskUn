package org.maxur.commons.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.maxur.commons.service.ServiceNotifier;
import org.maxur.taskun.services.impl.NotifierImpl;
import org.springframework.util.StopWatch;

/**
 * The Benchmark Advice. It processes benchmarking on annotated method.
 *
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>21.04.11</pre>
 */
@Aspect
public class BenchmarkAdvice {

    /**
     * The ServiceNotifier.
     */
    private ServiceNotifier notifier;

    /**
     * The Pointcut for benchmarking methods.
     */
    @SuppressWarnings("unused")
    @Pointcut("@annotation(org.maxur.commons.annotation.Benchmark)")
    private void benchmarkPointcut() {
    }

    /**
     * This method processes benchmarking on annotated method.
     * @param pjp  The Proceeding Join Point.
     * @return The Object which returned processed method.
     * @throws Throwable The Throwable which throws processed method.
     */
    @Around("benchmarkPointcut()")
    public Object benchmarkAdvice(final ProceedingJoinPoint pjp) throws Throwable {
        StopWatch sw = new StopWatch();
        Object retVal;
        try {
            sw.start();
            retVal = pjp.proceed();
        } finally {
            sw.stop();
            notifier.notifyBenchmark(pjp.getSignature(), sw);
        }
        return retVal;
      }

    public void setNotifier(NotifierImpl notifier) {
        this.notifier = notifier;
    }
}

