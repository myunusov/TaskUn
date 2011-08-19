package org.maxur.taskun.services.impl;

import org.aspectj.lang.Signature;
import org.maxur.commons.service.ServiceNotifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/18/11
 */
public class BaseNotifier implements ServiceNotifier {

    private final Map<String, Logger> loggerMap = new HashMap<String, Logger>();

    final void addLogger(final String name, final Logger logger) {
        loggerMap.put(name, logger);
    }

    @Override
    public final void notifyBenchmark(final Signature signature, final StopWatch stopWatch) {
        getLoggerBy("benchmark").info(signature.toShortString() + ", " + stopWatch.toString());
    }

    @Override
    public final void notifyEnter(final Signature signature) {
        getLoggerBy("trace").debug(signature.toShortString() + " ENTER");
    }

    @Override
    public final void notifyExit(final Signature signature) {
        getLoggerBy("trace").debug(signature.toShortString() + " EXIT");
    }

    private Logger getLoggerBy(final String name) {
        Logger logger = loggerMap.get(name);
        if (null == logger) {
            logger = LoggerFactory.getLogger(name);
            loggerMap.put(name, logger);
        }
        return logger;
    }

    protected void debug(Class<?> source, String msg) {
        getLoggerBy(source.getName()).debug(msg);
    }
}
