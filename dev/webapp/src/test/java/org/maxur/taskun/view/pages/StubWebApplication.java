package org.maxur.taskun.view.pages;

import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.spring.injection.annot.test.AnnotApplicationContextMock;
import org.jmock.Mockery;
import org.maxur.taskun.domain.employee.EmployeeBuilder;
import org.maxur.taskun.services.ApplicationController;
import org.maxur.taskun.services.Impl.NotifierImpl;
import org.maxur.taskun.view.TaskunApplication;
import org.maxur.taskun.view.UserSession;
import org.maxur.taskun.view.config.ViewConfigurator;
import org.maxur.taskun.view.model.MenuItems;
import org.maxur.taskun.view.pages.employee.EmployeePage;

import javax.validation.Validator;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/15/11
 */
public class StubWebApplication extends TaskunApplication {

    private final Mockery context;

    public ViewConfigurator viewConfigurator;

    public ApplicationController controller;


    public StubWebApplication(Mockery context) {
        this.context = context;
    }


    @Override
    protected void springInjection() {
        AnnotApplicationContextMock mockContext = new AnnotApplicationContextMock();
        viewConfigurator = context.mock(ViewConfigurator.class);
        mockContext.putBean("viewConfigurator", viewConfigurator);
        mockContext.putBean("validator", context.mock(Validator.class));
        mockContext.putBean("notifierImpl", context.mock(NotifierImpl.class));
        mockContext.putBean("employeeBuilder", context.mock(EmployeeBuilder.class));
        controller = context.mock(ApplicationController.class);
        mockContext.putBean("applicationController", controller);
        addComponentInstantiationListener(new SpringComponentInjector(this, mockContext, false));
        context.checking(new org.jmock.Expectations() {{
            allowing(viewConfigurator).menuItems(); will(returnValue(new MenuItems(1)));
        }});
        setHomePage(BasePage.class);
    }

    @Override
    public Session newSession(final Request request, final Response response) {
        return new UserSession(request, createMenuItems());
    }

    private MenuItems createMenuItems() {
        final MenuItems result = new MenuItems(3);
        result.add("menu.item.main", EmployeePage.class, true);
        result.add("menu.item.task", ExamplePage.class);
        result.add("menu.item.self", ExamplePage.class);
        return result;
    }
}
