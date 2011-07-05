package org.maxur.taskun.view;

import org.apache.wicket.protocol.http.WebApplication;
import org.maxur.taskun.view.pages.IndexPage;
import org.springframework.stereotype.Component;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/5/11
 */
@Component(value = "wicketApplication")
public class TaskunApplication extends WebApplication {

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class getHomePage() {
        return IndexPage.class;
    }
}
