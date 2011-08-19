package org.maxur.taskun.view.pages;

import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.strategies.role.Roles;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.spring.injection.annot.test.AnnotApplicationContextMock;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.maxur.taskun.services.ApplicationController;
import org.maxur.taskun.view.TaskunApplication;
import org.maxur.taskun.view.UserSession;
import org.maxur.taskun.view.config.ViewConfigurator;
import org.maxur.taskun.view.model.MenuItem;
import org.maxur.taskun.view.model.MenuItems;
import org.maxur.taskun.view.pages.employee.EmployeePage;
import org.springframework.security.authentication.AuthenticationManager;

import javax.validation.Validator;
import java.util.List;

/**
* @author Maxim Yunusov
* @version 1.0 7/15/11
*/
public class StubWebApplication extends TaskunApplication {

    private final Mockery context;

    public ViewConfigurator viewConfigurator;

    public ApplicationController controller;

    private String role;

    public StubWebApplication(Mockery context) {
        this.context = context;
    }


    @Override
    protected void springInjection() {
        AnnotApplicationContextMock mockContext = new AnnotApplicationContextMock();
        viewConfigurator = context.mock(ViewConfigurator.class);
        mockContext.putBean("viewConfigurator", viewConfigurator);
        mockContext.putBean("validator", context.mock(Validator.class));
        mockContext.putBean("authenticationManager", context.mock(AuthenticationManager.class));
        controller = context.mock(ApplicationController.class);
        mockContext.putBean("applicationController", controller);
        addComponentInstantiationListener(new SpringComponentInjector(this, mockContext, false));
        context.checking(new Expectations() {{
            allowing(viewConfigurator).menuItems(); will(returnValue(new MenuItems(1)));
        }});
        setHomePage(BasePage.class);
    }

    @Override
    public Session newSession(final Request request, final Response response) {
        return new UserSession(request) {
            @Override
            public List<MenuItem> getMenuItems() {
                return createMenuItems();
            }

            @Override
            public Roles getRoles() {
                final Roles roles = new Roles();
                if (role != null) {
                    roles.add(role);
                }
                return roles;
            }
        };
    }

    private MenuItems createMenuItems() {
        final MenuItems result = new MenuItems(3);
        result.add("menu.item.main", EmployeePage.class, true);
        result.add("menu.item.task", ExamplePage.class);
        result.add("menu.item.self", ExamplePage.class);
        return result;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
