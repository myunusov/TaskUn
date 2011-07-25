package org.maxur.taskun.domain;

/**
 * The Entity Factory interface.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/25/11
 */
public interface Factory<T extends Entity> {

     /**
     * The Entity Factory Method. It creates new instance of Entity.
     * @return The Entity implementation.
     */
    T create();

}
