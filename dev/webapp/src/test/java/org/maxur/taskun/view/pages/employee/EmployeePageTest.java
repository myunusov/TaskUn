package org.maxur.taskun.view.pages.employee;

import org.apache.wicket.markup.html.WebPage;
import org.junit.Test;
import org.maxur.taskun.services.ControllerExpectationBuilder;
import org.maxur.taskun.view.model.employee.EmployeeBuilder;
import org.maxur.taskun.view.pages.AbstractPageTest;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */

public class EmployeePageTest extends AbstractPageTest {

    @Override
    protected Class<? extends WebPage> getPageClass() {
        return EmployeePage.class;
    }

    @Override
    protected void start() {
    }

    @Test
    public void testPageBasicRender() {
        context.checking(new ControllerExpectationBuilder(controller).build());
        super.start();
        super.testPageBasicRender();
    }


    @Test
    public void testHomePageBasicRender() {
        context.checking(new ControllerExpectationBuilder(controller).build());
        super.start();
        tester.assertRenderedPage(EmployeePage.class);
        context.assertIsSatisfied();
    }

    @Test
    public void testHomePageBasicRenderWithEmployee() {
        context.checking(new ControllerExpectationBuilder(controller).count(1).build());
        super.start();
        tester.assertRenderedPage(EmployeePage.class);
        context.assertIsSatisfied();
    }

    @Test
    public void testHomePageBasicRenderWithEmployeeMale() {
        context.checking(
                new ControllerExpectationBuilder(controller)
                        .fromEmployee(new EmployeeBuilder().asMale().build())
                        .count(1)
                        .build());
        super.start();
        tester.assertRenderedPage(EmployeePage.class);
        context.assertIsSatisfied();
    }

    @Test
    public void testHomePageBasicRenderWithEmployeeFemale() {
        context.checking(
                new ControllerExpectationBuilder(controller)
                        .fromEmployee(new EmployeeBuilder().asFemale().build())
                        .count(1)
                        .build());
        super.start();
        tester.assertRenderedPage(EmployeePage.class);
        context.assertIsSatisfied();
    }


}
