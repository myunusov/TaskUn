package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.util.tester.DummyPanelPage;
import org.jmock.Expectations;
import org.junit.Test;
import org.maxur.taskun.domain.Specification;
import org.maxur.taskun.view.pages.employee.EmployeePage;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/18/11
 */
public class MenuPanelTest extends AbstractPanelTest {

    @Override
    protected Panel makeTestPanel(String panelId) {
        return new MenuPanel(panelId);
    }


    @Test
    public void testMenuPanel() throws Exception {
        tester.assertNoErrorMessage();
        tester.assertNoInfoMessage();
    }

    @Test
    public void testMenuBasicRender() {
        tester.assertRenderedPage(DummyPanelPage.class);
    }

    @Test
    public void testBasePageComponents() {
        tester.assertComponent("panel:menu_items", MenuPanel.MenuItemsView.class);
        tester.assertComponent("panel:menu_items:0:menu_item", MenuPanel.MenuItemsView.MenuItemLink.class);
        tester.assertComponent("panel:menu_items:0:menu_item:menu_item_link", WebMarkupContainer.class);
        tester.assertComponent("panel:menu_items:0:menu_item:menu_item_link:menu_item_name", Label.class);
    }

    @Test
    public void testOnClickAction() {
        context.checking(new Expectations() {{
            oneOf(controller).getAllEmployee(with(any(Specification.class)));
        }});
        tester.clickLink("panel:menu_items:0:menu_item");
        tester.assertRenderedPage(EmployeePage.class);
    }


}
