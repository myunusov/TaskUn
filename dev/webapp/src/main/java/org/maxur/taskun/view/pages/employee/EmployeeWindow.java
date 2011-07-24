package org.maxur.taskun.view.pages.employee;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.model.IModel;
import org.maxur.taskun.view.components.BeanWindow;
import org.maxur.taskun.view.model.BatchCommand;
import org.maxur.taskun.view.model.Command;
import org.maxur.taskun.view.model.CommandRepository;
import org.maxur.taskun.view.model.EmployeeBean;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/21/11
 */
class EmployeeWindow extends ModalWindow implements BeanWindow<EmployeeBean> {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 3684929621343694368L;

    private static final String PAGE_MAP_NAME = "employee_editor";

    private static final String COOKIE_NAME = "employee_editor";

    private static final int MINIMAL_HEIGHT = 145;

    private static final int MINIMAL_WIDTH = 350;

    private final CommandRepository commands;

    public EmployeeWindow(final String id, final CommandRepository commands) {
        super(id);
        this.commands = commands;
        setPageMapName(PAGE_MAP_NAME);
        setCookieName(COOKIE_NAME);
        setMinimalHeight(MINIMAL_HEIGHT);
        setMinimalWidth(MINIMAL_WIDTH);
    }

    @Override
    public void show(final AjaxRequestTarget target) {
        throw new UnsupportedOperationException("Method show(AjaxRequestTarget,EmployeeBean) must be used");
    }

    public void show(final AjaxRequestTarget target, final IModel<EmployeeBean> model) {
        final EmployeeBean employee = model.getObject();

        setTitle(getTitle(employee));

        final Command<EmployeeBean> close = new Command<EmployeeBean>() {
            @Override
            public void execute(final AjaxRequestTarget target, final IModel<EmployeeBean> model) {
                close(target);
            }
        };

        final Command<EmployeeBean> submit
                = new BatchCommand<EmployeeBean>(commands.<EmployeeBean>get("employee.submit"), close);

        setContent(new EmployeePanel(getContentId(), employee, submit, close));
        super.show(target);
    }

    private String getTitle(final EmployeeBean employee) {
        //TODO exclude strings constant
        String result;
        if (employee.isNew()) {
            result = "Вводим нового сотрудника";
        } else {
            result = "Редактируем данные по сотруднику " + employee.getTitle();
        }
        return result;
    }
}
