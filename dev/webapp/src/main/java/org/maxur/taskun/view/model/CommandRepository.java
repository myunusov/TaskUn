package org.maxur.taskun.view.model;

import java.io.Serializable;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/28/11
 */
public interface CommandRepository extends Serializable {

    void persist(String id, Command command);

    <T extends Bean> Command<T> get(String id);

    <T extends Bean> Command<T> reserve(String id);

}
