package org.maxur.commons.service;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/21/11
 */
public interface Logger {

    void debug(Class<?> source, String msg);

    void info(String source, String msg);

    void debug(String source, String msg);
}
