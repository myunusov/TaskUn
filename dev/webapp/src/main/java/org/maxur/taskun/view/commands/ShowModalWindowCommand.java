package org.maxur.taskun.view.commands;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.maxur.taskun.view.components.BeanWindow;
import org.maxur.taskun.view.model.Bean;
import org.maxur.taskun.view.model.BeanFactory;
import org.maxur.taskun.view.model.Command;

/**
 * The command for show modal dialog.
 * GoF Command pattern
 *
 * @author Maxim Yunusov
 * @version 1.0 7/22/11
 */
public class ShowModalWindowCommand<E extends Bean> extends Command<E> {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 3993725280279440239L;

    /**
     * The Modal Window under control.
     */
    private final BeanWindow<E> window;

    /**
     * Constructs Command with window instance.
     *
     * @param window The Modal Window.
     */
    public ShowModalWindowCommand(final BeanWindow<E> window) {
        this.window = window;
    }

    /**
     * @param target A request target that produces ajax response.
     * @param model  The ModalWindow Model.
     * @see org.maxur.taskun.view.model.Command#execute(org.apache.wicket.ajax.AjaxRequestTarget,
     *          org.maxur.taskun.view.model.Bean)
     */
    @Override
    public void execute(final AjaxRequestTarget target, final E model) {
        window.show(target, model);
    }

    /**
     * @param target A request target that produces ajax response.
     * @param factory  The ModalWindow Model Factory.
     * @see org.maxur.taskun.view.model.Command#execute(org.apache.wicket.ajax.AjaxRequestTarget,
     *          org.maxur.taskun.view.model.BeanFactory)
     */
    @Override
    public void execute(AjaxRequestTarget target, BeanFactory<E> factory) {
        window.show(target, factory);
    }

}
