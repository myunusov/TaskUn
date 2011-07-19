package org.maxur.taskun.view.pages;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.maxur.taskun.services.ApplicationController;
import org.maxur.taskun.view.components.AjaxChangeManager;
import org.maxur.taskun.view.model.EmployeeBean;
import org.maxur.taskun.view.model.EmployeesGroup;

/**
* @author Maxim Yunusov
* @version 1.0 7/21/11
*/
class EmployeeWindow extends ModalWindow {

    private static final long serialVersionUID = 3684929621343694368L;

    /**
     * The ApplicationController bean. It's injected by Wicket IoC.
     */
    @SpringBean
    private ApplicationController controller;

    public EmployeeWindow(
            final String id,
            final EmployeesGroup group,
            final AjaxChangeManager changeManager
    ) {
        super(id);
        setPageMapName("createdialogpnel");
        setCookieName("createdialogpnel");

        setMinimalHeight(145);
        setMinimalWidth(350);

        setWindowClosedCallback(new WindowClosedCallback() {
            public void onClose(AjaxRequestTarget target) {
                group.refresh();
                changeManager.update(target);
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
        final EmployeeBean employee = new EmployeeBean(controller);
        show(target, employee);
    }

    public void show(final AjaxRequestTarget target, final EmployeeBean employee) {
        setTitle("Сотрудник: " + employee.getTitle());    //TODO
        setContent(new EmployeePanel(getContentId(), employee, this));
        super.show(target);
    }
}
