package org.maxur.taskun.services.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>21.04.11</pre>
 */
@Aspect
@Component
public class BenchmarkAdvice {

    public static final Logger LOGGER = LoggerFactory.getLogger("benchmark");

    @Pointcut("@annotation(org.maxur.taskun.utils.Benchmark)")
    private void benchmarkPointcut() {
    }

    @Around("benchmarkPointcut()")
    public Object benchmarkAdvice(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch sw = new StopWatch();
        Object retVal;
        try {
            sw.start();
            retVal = pjp.proceed();
        } finally {
            sw.stop();
            LOGGER.info(pjp.getSignature().toShortString() + ", " + sw.toString());
        }
        return retVal;
      }

}

