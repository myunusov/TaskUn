package org.maxur.taskun.view.pages.employee;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.model.IComponentAssignedModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.model.StringResourceModel;
import org.maxur.taskun.view.components.BeanWindow;
import org.maxur.taskun.view.model.BatchCommand;
import org.maxur.taskun.view.model.BeanFactory;
import org.maxur.taskun.view.model.Command;
import org.maxur.taskun.view.model.CommandRepository;
import org.maxur.taskun.view.model.employee.EmployeeBean;

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
        throw new UnsupportedOperationException("Method show(AjaxRequestTarget, EmployeeBean) must be used");
    }

    public void show(final AjaxRequestTarget target, final EmployeeBean model) {
    //    setTitle(String.format(new ResourceModel("title.edit").getObject(), model.getTitle()));
        final StringResourceModel title = new StringResourceModel("title.edit", model);
        doShow(target, model, title);
    }

    @Override
    public void show(AjaxRequestTarget target, BeanFactory<EmployeeBean> factory) {
        doShow(target, factory.getObject(), new ResourceModel("title.create"));
    }

    private void doShow(AjaxRequestTarget target, EmployeeBean model, IComponentAssignedModel<String> title) {
        final Command<EmployeeBean> close = new Command<EmployeeBean>() {
            @Override
            public void execute(final AjaxRequestTarget target, final EmployeeBean model) {
                close(target);
            }
        };

        final Command<EmployeeBean> submit
                = new BatchCommand<EmployeeBean>(commands.<EmployeeBean>get("employee.submit"), close);

        final EmployeePanel panel = new EmployeePanel(getContentId(), model, submit, close);
        setContent(panel);
        title.wrapOnAssignment(panel);
        setTitle(title);
        super.show(target);
    }

}
