package org.maxur.taskun.view.pages;

import org.apache.wicket.Request;
import org.apache.wicket.Response;
import org.apache.wicket.Session;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.spring.injection.annot.test.AnnotApplicationContextMock;
import org.maxur.taskun.view.TaskunApplication;
import org.maxur.taskun.view.model.MenuItems;
import org.maxur.taskun.view.model.UserSession;

/**
* @author Maxim Yunusov
* @version 1.0 7/15/11
*/
class StubWebApplication extends TaskunApplication {

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
        return new UserSession(request, createMenuItems());
    }

    private MenuItems createMenuItems() {
        final MenuItems result = new MenuItems(3);
        result.add("Главная", HomePage.class, true);
        result.add("Блог", ExamplePage.class);
        result.add("О программе", ExamplePage.class);
        return result;
    }
}
