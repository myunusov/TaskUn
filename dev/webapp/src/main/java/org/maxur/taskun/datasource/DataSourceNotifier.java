package org.maxur.taskun.datasource;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/18/11
 */
public interface DataSourceNotifier {

    void notifyEmployeeCreate(Class<?> source, String valueAsString);

}
