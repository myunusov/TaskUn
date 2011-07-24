package org.maxur.taskun.view.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/22/11
 */
public class CommandRepository implements Serializable {

    private static final long serialVersionUID = 3397130411188839360L;

    private Map<String, Command> commands = new HashMap<String, Command>();

    public void persist(final String id, final Command command) {
        commands.put(id, command);
    }

    public <T> Command<T> get(final String id) {
        final Command<T> result;
        try {
            result = (Command<T>) commands.get(id);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(String.format("Command '%s' has invalid type", id), e);
        }
        if (result == null) {
            throw new IllegalArgumentException(String.format("Command '%s' is not registered", id));
        }
        try {
            return result.clone();
        } catch (CloneNotSupportedException e) {
            throw new IllegalArgumentException(String.format("Command '%s' is not cloneable", id), e);
        }
    }

}
