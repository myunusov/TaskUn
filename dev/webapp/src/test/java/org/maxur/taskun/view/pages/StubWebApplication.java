package org.maxur.taskun.view.pages;

import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.spring.injection.annot.test.AnnotApplicationContextMock;
import org.maxur.taskun.view.TaskunApplication;
import org.maxur.taskun.view.UserSession;
import org.maxur.taskun.view.model.MenuItems;
import org.maxur.taskun.view.pages.employee.EmployeePage;

/**
* @author Maxim Yunusov
* @version 1.0 7/15/11
*/
public class StubWebApplication extends TaskunApplication {

    private AnnotApplicationContextMock mockContext;

    @Override
    protected void springInjection() {
        mockContext = new AnnotApplicationContextMock();
        addComponentInstantiationListener(new SpringComponentInjector(this, mockContext, false));
    }

    public AnnotApplicationContextMock getMockContext() {
        return mockContext;
    }

    @Override
    public Session newSession(final Request request, final Response response) {
        final UserSession userSession = new UserSession(request);
        userSession.setMenuItems(createMenuItems());
        return userSession;
    }

    private MenuItems createMenuItems() {
        final MenuItems result = new MenuItems(3);
        result.add("menu.item.main", EmployeePage.class, true);
        result.add("menu.item.task", ExamplePage.class);
        result.add("menu.item.self", ExamplePage.class);
        return result;
    }
}
