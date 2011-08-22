package org.maxur.commons.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * The Base Logger Class. It's implemented Application Logger interface and adaptee org.slf4j.Logger.
 *
 * @author Maxim Yunusov
 * @version 1.0 8/18/11
 *
 * Singleton with children possible.
 *
 */
public class BaseLogger implements org.maxur.commons.service.Logger {

    private static org.maxur.commons.service.Logger logger = new BaseLogger();

    private final Map<String, Logger> loggerMap = new HashMap<String, Logger>();

    protected BaseLogger() {
    }

    public static void setInstance(final org.maxur.commons.service.Logger logger) {
        BaseLogger.logger = logger;
    }

    public static org.maxur.commons.service.Logger instance() {
        return logger;
    }

    private Logger getLoggerBy(final String name) {
        Logger logger = loggerMap.get(name);
        if (null == logger) {
            logger = createLogger(name);
            loggerMap.put(name, logger);
        }
        return logger;
    }

    protected Logger createLogger(String name) {
        return LoggerFactory.getLogger(name);
    }

    public void debug(Class<?> source, String msg) {
        getLoggerBy(source.getName()).debug(msg);
    }

    public void info(final String source, final String msg) {
        getLoggerBy(source).info(msg);
    }

    public void debug(final String source, final String msg) {
        getLoggerBy(source).debug(msg);
    }
}
