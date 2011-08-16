package org.maxur.taskun.view.commands;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.maxur.taskun.view.model.Command;
import org.maxur.taskun.view.model.employee.EmployeeBean;

/**
 * The command for submitting employee.
 * GoF Command pattern
 *
 * @author Maxim Yunusov
 * @version 1.0 7/22/11
 */
public class EmployeeSubmitCommand extends Command<EmployeeBean> {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -1408594441869584895L;

    /**
     * @param target A request target that produces ajax response.
     * @param model  The EmployeeBean Model.
     * @see org.maxur.taskun.view.model.Command#execute(org.apache.wicket.ajax.AjaxRequestTarget,
     *      org.maxur.taskun.view.model.Bean)
     */
    @Override
    public void execute(AjaxRequestTarget target, EmployeeBean model) {
        model.save(target);
    }
}
