package org.maxur.taskun.view.pages.employee;

import org.apache.wicket.markup.html.WebPage;
import org.junit.Test;
import org.maxur.taskun.view.model.employee.EmployeeHelper;
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
        context.checking(EmployeeHelper.makeExpectations(controller, 0, EmployeeHelper.DUMMY_EMPLOYEE));
        super.start();
        super.testPageBasicRender();
    }


    @Test
    public void testHomePageBasicRender() {
        context.checking(EmployeeHelper.makeExpectations(controller, 0, EmployeeHelper.DUMMY_EMPLOYEE));
        super.start();
        tester.assertRenderedPage(EmployeePage.class);
        context.assertIsSatisfied();
    }

    @Test
    public void testHomePageBasicRenderWithEmployee() {
        context.checking(EmployeeHelper.makeExpectations(controller, 1, EmployeeHelper.DUMMY_EMPLOYEE));
        super.start();
        tester.assertRenderedPage(EmployeePage.class);
        context.assertIsSatisfied();
    }

    @Test
    public void testHomePageBasicRenderWithEmployeeMale() {
        context.checking(EmployeeHelper.makeExpectations(controller, 1, EmployeeHelper.MALE));
        super.start();
        tester.assertRenderedPage(EmployeePage.class);
        context.assertIsSatisfied();
    }

    @Test
    public void testHomePageBasicRenderWithEmployeeFemale() {
        context.checking(EmployeeHelper.makeExpectations(controller, 1, EmployeeHelper.FEMALE));
        super.start();
        tester.assertRenderedPage(EmployeePage.class);
        context.assertIsSatisfied();
    }


}
