package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.WebPage;
import org.junit.Test;

/**
 * @author Maxim Yunusov
 * @version 1.0 8/15/11
 */
public abstract class AbstractPageTest extends AbstractWicketTest {

    @Override
    protected void start() {
        tester.startPage(getPageClass());
    }

    protected abstract Class<? extends WebPage> getPageClass();

    @Test
    public void testPageBasicRender() {
        tester.assertRenderedPage(getPageClass());
    }
}
