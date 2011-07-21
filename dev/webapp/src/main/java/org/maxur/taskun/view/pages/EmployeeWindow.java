package org.maxur.taskun.view.pages;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.maxur.taskun.view.model.EmployeeBean;
import org.maxur.taskun.view.model.EmployeesGroup;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/21/11
 */
class EmployeeWindow extends ModalWindow {

    private static final long serialVersionUID = 3684929621343694368L;

    private final EmployeesGroup group;

    public EmployeeWindow(
            final String id,
            final EmployeesGroup group
    ) {
        super(id);
        this.group = group;
        setPageMapName("createdialogpnel");
        setCookieName("createdialogpnel");

        setMinimalHeight(145);
        setMinimalWidth(350);

        setWindowClosedCallback(new WindowClosedCallback() {
            public void onClose(AjaxRequestTarget target) {
                if (target != null) {
                    group.refresh(target);
                }
            }
        });

        setCloseButtonCallback(new CloseButtonCallback() {
            public boolean onCloseButtonClicked(AjaxRequestTarget target) {
                return true;
            }
        });
    }

    @Override
    public void show(final AjaxRequestTarget target) {
        final EmployeeBean employee = group.createEmployee();
        show(target, employee);
    }

    public void show(final AjaxRequestTarget target, final EmployeeBean employee) {
        if (employee.isNew()) {
            setTitle("Вводим нового сотрудника");    //TODO
        } else {
            setTitle("Редактируем данные по сотруднику " + employee.getTitle());    //TODO
        }
        setContent(new EmployeePanel(getContentId(), employee, this));
        super.show(target);
    }
}
