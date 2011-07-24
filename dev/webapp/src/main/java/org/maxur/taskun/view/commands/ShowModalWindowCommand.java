package org.maxur.taskun.view.commands;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;
import org.maxur.taskun.view.components.BeanWindow;
import org.maxur.taskun.view.model.Command;

/**
 * The command for show modal dialog.
 * GoF Command pattern
 *
 * @author Maxim Yunusov
 * @version 1.0 7/22/11
 */
public class ShowModalWindowCommand<T> extends Command<T> {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 3993725280279440239L;

    /**
     * The Modal Window under control.
     */
    private final BeanWindow<T> window;

    /**
     * Constructs Command with window instance.
     * @param window The Modal Window.
     */
    public ShowModalWindowCommand(final BeanWindow<T> window) {
        this.window = window;
    }

    /**
     * @see org.maxur.taskun.view.model.Command#execute(
     *      org.apache.wicket.ajax.AjaxRequestTarget,
     *      org.apache.wicket.model.IModel)
     *
     * @param target A request target that produces ajax response.
     * @param model The ModalWindow Model.
     */
    @Override
    public void execute(final AjaxRequestTarget target, final IModel<T> model) {
        window.show(target, model);
    }
}
