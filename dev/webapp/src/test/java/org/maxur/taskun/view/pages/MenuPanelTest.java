package org.maxur.taskun.view.pages;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.spring.injection.annot.test.AnnotApplicationContextMock;
import org.apache.wicket.util.tester.ITestPanelSource;
import org.apache.wicket.util.tester.WicketTester;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;
import org.maxur.taskun.services.ApplicationController;

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
    public void testWicketPanel() throws Exception {
        startPanel();
        tester.assertNoErrorMessage();
        tester.assertNoInfoMessage();
    }
}
