package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.test.AnnotApplicationContextMock;
import org.apache.wicket.util.tester.DummyPanelPage;
import org.apache.wicket.util.tester.ITestPanelSource;
import org.apache.wicket.util.tester.WicketTester;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.maxur.taskun.domain.Specification;
import org.maxur.taskun.services.ApplicationController;
import org.maxur.taskun.view.pages.employee.EmployeePage;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/18/11
 */
public class MenuPanelTest {

    private WicketTester tester;

    private Mockery context;

    private ApplicationController controller;

    @Before
    public void setUp() {
        context = new JUnit4Mockery() {{
            setImposteriser(ClassImposteriser.INSTANCE);
        }};
        tester = new WicketTester(new StubWebApplication());
        AnnotApplicationContextMock mockContext =
                ((StubWebApplication) tester.getApplication()).getMockContext();
        controller = context.mock(ApplicationController.class);
        mockContext.putBean("applicationController", controller);
    }

    private void startPanel() {
        tester.startPanel(new ITestPanelSource() {
            @Override
            public Panel getTestPanel(String panelId) {
                return new MenuPanel(panelId);
            }
        });
    }

    @Test
    public void testMenuPanel() throws Exception {
        startPanel();
        tester.assertNoErrorMessage();
        tester.assertNoInfoMessage();
    }

    @Test
    public void testMenuBasicRender() {
        startPanel();
        tester.assertRenderedPage(DummyPanelPage.class);
    }

    @Test
    public void testBasePageComponents() {
        startPanel();
        tester.assertComponent("panel:menu_items", MenuPanel.MenuItemsView.class);
        tester.assertComponent("panel:menu_items:0:menu_item", MenuPanel.MenuItemsView.MenuItemLink.class);
        tester.assertComponent("panel:menu_items:0:menu_item:menu_item_link", WebMarkupContainer.class);
        tester.assertComponent("panel:menu_items:0:menu_item:menu_item_link:menu_item_name", Label.class);
    }

    @Test
    public void testOnClickAction() {
        startPanel();
        context.checking(new Expectations() {{
            oneOf(controller).getAllEmployee(with(any(Specification.class)));
        }});
        tester.clickLink("panel:menu_items:0:menu_item");
        tester.assertRenderedPage(EmployeePage.class);
    }



}
