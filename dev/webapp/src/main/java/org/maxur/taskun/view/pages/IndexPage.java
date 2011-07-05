package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/5/11
 */
public class IndexPage extends WebPage {

    public IndexPage() {
        add(new Label("message", "Hello World!"));
    }
}
