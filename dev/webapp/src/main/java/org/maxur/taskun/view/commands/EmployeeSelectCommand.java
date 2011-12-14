package org.maxur.taskun.view.commands;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.maxur.taskun.view.model.Command;
import org.maxur.taskun.view.model.employee.EmployeeBean;

/**
 * The command for selecting employee.
 * GoF Command pattern
 *
 * @author Maxim Yunusov
 * @version 1.0 7/22/11
 */
public class EmployeeSelectCommand extends Command<EmployeeBean> {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 7588291751797699029L;

    /**
     *
     * @param target A request target that produces ajax response.
     * @param model  The EmployeeBean Model.
     *
     * @see org.maxur.taskun.view.model.Command#execute(org.apache.wicket.ajax.AjaxRequestTarget,
     *      org.maxur.taskun.view.model.Bean)
     */
    @Override
    public void execute(final AjaxRequestTarget target, final EmployeeBean model) {
        model.select(target);
    }
}
