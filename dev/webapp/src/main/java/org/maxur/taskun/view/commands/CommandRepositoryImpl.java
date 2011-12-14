package org.maxur.taskun.view.commands;

import org.maxur.taskun.view.model.Bean;
import org.maxur.taskun.view.model.Command;
import org.maxur.taskun.view.model.CommandRepository;

import java.util.HashMap;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/22/11
 */
public class CommandRepositoryImpl implements CommandRepository {

    private static final long serialVersionUID = 3397130411188839360L;

    private final HashMap<String, Command> commands;

    public CommandRepositoryImpl() {
        this.commands = new HashMap<String, Command>();
    }

    @Override
    public final void persist(final String id, final Command command) {
        this.commands.put(id, command);
    }

    @Override
    @SuppressWarnings("unchecked")
    public final <T extends Bean> Command<T> get(final String id) {
        Command<T> result = null;
        try {
            final Command<T> command = (Command<T>) this.commands.get(id);
            if (null != command) {
                result = command.clone();
            } else {
                throw new IllegalArgumentException(String.format("Command '%s' is not registered", id));
            }
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(String.format("Command '%s' has invalid type", id), e);
        } catch (CloneNotSupportedException ignored) {
            assert false : String.format("Command '%s' is not cloneable", id);
        }
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public final <T extends Bean> Command<T> reserve(final String id) {
        return new CommandProxy<T>(this, id);
    }

}
