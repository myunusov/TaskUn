package org.maxur.taskun.services;

import org.hibernate.JDBCException;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/13/11
 */
public class TaskunServiceException extends Exception {

    private static final long serialVersionUID = 3936274426270879669L;

    public TaskunServiceException(final JDBCException cause) {
        super(cause);
    }
}
