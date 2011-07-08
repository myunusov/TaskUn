package org.maxur.taskun.view;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.maxur.taskun.view.pages.HomePage;
import org.springframework.stereotype.Component;

/**
 * This class is intended to be subclassed by framework
 * clients to define a web application.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/5/11
 */
@Component(value = "wicketApplication")
public class TaskunApplication extends WebApplication {

    /**
     * @see org.apache.wicket.Application#getHomePage()
     * @return Home page class for this application
     */
    @Override
    public Class<? extends Page> getHomePage() {
//        mountBookmarkablePage("/example", ExamplePage.class);
        return HomePage.class;
    }
}
