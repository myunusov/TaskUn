package org.maxur.taskun.datasource;

/**
 * This exception is raised on unexpected result from Data Source.
 * It cause may be error on data schema or error in SQL query.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/27/11
 */
public class UnexpectedResultException extends DatasourceException {

    private static final long serialVersionUID = -2318173749258043901L;

    public UnexpectedResultException(String message) {
        super(message);
    }
}
