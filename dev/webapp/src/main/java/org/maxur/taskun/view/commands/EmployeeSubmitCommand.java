package org.maxur.taskun.view.commands;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;
import org.maxur.taskun.services.TaskunServiceException;
import org.maxur.taskun.view.model.Command;
import org.maxur.taskun.view.model.EmployeeBean;

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
     * @see org.maxur.taskun.view.model.Command#execute(
     *      org.apache.wicket.ajax.AjaxRequestTarget,
     *      org.apache.wicket.model.IModel)
     *
     * @param target A request target that produces ajax response.
     * @param model The EmployeeBean Model.
     */
    @Override
    public void execute(final AjaxRequestTarget target, final IModel<EmployeeBean> model) {
        try {
            model.getObject().save(target);
        } catch (TaskunServiceException e) {
            //TODO May be processed
        }
    }
}
