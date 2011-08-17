package org.maxur.taskun.view.pages.self;

import org.apache.wicket.markup.html.WebPage;
import org.maxur.taskun.view.pages.AbstractPageTest;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/17/11
 */
public class SelfPageTest extends AbstractPageTest {
    @Override
    protected Class<? extends WebPage> getPageClass() {
        return SelfPage.class;
    }
}
