package org.maxur.commons.advice;

import org.maxur.commons.service.Logger;
import org.maxur.commons.service.impl.BaseLogger;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/24/11
 */
public class AbstractLoggerAdvice {

    protected Logger makeLogger() {
        return BaseLogger.instance();
    }

}
