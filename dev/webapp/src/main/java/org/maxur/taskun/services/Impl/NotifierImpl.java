package org.maxur.taskun.services.Impl;

import org.maxur.commons.service.Logger;
import org.maxur.taskun.datasource.DataSourceNotifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/18/11
 */
@Component
public class NotifierImpl implements DataSourceNotifier {

    private final Logger logger;

    @Autowired
    public NotifierImpl(final Logger logger) {
        this.logger = logger;
    }

    @Override
    public void notifyEmployeeCreate(final Class<?> source) {
        logger.debug(source, "New Employee was created.");
    }

}
