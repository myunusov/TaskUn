package org.maxur.taskun.view.commands;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.maxur.taskun.view.model.Command;
import org.maxur.taskun.view.model.employee.EmployeesGroup;

/**
 * The command for removing employee.
 * GoF Command pattern
 *
 * @author Maxim Yunusov
 * @version 1.0 7/22/11
 */
public class EmployeeRemoveCommand extends Command<EmployeesGroup> {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -6692352727405371452L;

    /**
     * @see org.maxur.taskun.view.model.Command#execute(
     *      org.apache.wicket.ajax.AjaxRequestTarget,
     *      org.maxur.taskun.view.model.Bean)
     *
     * @param target A request target that produces ajax response.
     * @param model The EmployeesGroup Model.
     */
    @Override
    public void execute(final AjaxRequestTarget target, final EmployeesGroup model) {
        model.removeSelected(target);
    }
}
