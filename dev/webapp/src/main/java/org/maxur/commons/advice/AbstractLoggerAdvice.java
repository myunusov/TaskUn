package org.maxur.commons.advice;

import org.maxur.commons.service.Logger;
import org.maxur.commons.service.impl.BaseLogger;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/24/11
 */
public class AbstractLoggerAdvice {

    private Logger logger = BaseLogger.instance();

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
