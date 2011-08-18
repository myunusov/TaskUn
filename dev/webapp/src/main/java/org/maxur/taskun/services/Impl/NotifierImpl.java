package org.maxur.taskun.services.Impl;

import org.maxur.taskun.datasource.DataSourceNotifier;
import org.springframework.stereotype.Component;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/18/11
 */
@Component
public class NotifierImpl extends BaseNotifier implements DataSourceNotifier {

    @Override
    public void notifyEmployeeCreate(final Class<?> source) {
        debug(source, "New Employee was created.");
    }

}
