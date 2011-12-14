package org.maxur.taskun.view.pages;

import org.maxur.taskun.view.TaskunApplication;

/**
 * The Example Page Controller.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/5/11
 */
public class ExamplePage extends BasePage {

     /**
     *  The Serial Version UID.
     */
    private static final long serialVersionUID = -4434224282615026997L;


    static {
        TaskunApplication.get().mountBookmarkablePage("/example", ExamplePage.class);
    }
}
