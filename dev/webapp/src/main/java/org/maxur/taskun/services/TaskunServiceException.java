package org.maxur.taskun.services;

import org.hibernate.JDBCException;

/**
 * Service layer's exception.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/13/11
 */
public class TaskunServiceException extends Exception {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 3936274426270879669L;

    /**
     * Constructs exception.
     * @param cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A <tt>null</tt> value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     */
    public TaskunServiceException(final JDBCException cause) {
        super(cause);
    }
}
