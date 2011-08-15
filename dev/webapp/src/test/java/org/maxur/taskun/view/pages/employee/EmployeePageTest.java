package org.maxur.taskun.view.pages.employee;

import org.apache.wicket.markup.html.WebPage;
import org.jmock.Expectations;
import org.junit.Test;
import org.maxur.taskun.domain.Gender;
import org.maxur.taskun.domain.Specification;
import org.maxur.taskun.domain.employee.AbstractEmployee;
import org.maxur.taskun.domain.employee.Employee;
import org.maxur.taskun.view.pages.AbstractPageTest;

import java.util.Collections;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>08.07.11</pre>
 */

public class EmployeePageTest extends AbstractPageTest {

    static private Employee dummy = new AbstractEmployee() {
        private static final long serialVersionUID = 3908424889025108375L;
    };

    @Override
    protected Class<? extends WebPage> getPageClass() {
        return EmployeePage.class;
    }

    @Override
    protected void start() {
    }

    private void startPage(final int numberOfItems, final Employee employee) {
        context.checking(new Expectations() {{
            oneOf(controller).getAllEmployee(with(any(Specification.class)));
            will(returnValue(Collections.nCopies(numberOfItems, employee)));
        }});
        super.start();
    }

    @Test
    public void testPageBasicRender() {
       startPage(0, dummy);
       super.testPageBasicRender();
    }


    @Test
    public void testHomePageBasicRender() {
        startPage(0, dummy);
        tester.assertRenderedPage(EmployeePage.class);
        context.assertIsSatisfied();
    }

    @Test
    public void testHomePageBasicRenderWithEmployee() {
        startPage(1, dummy);
        tester.assertRenderedPage(EmployeePage.class);
        context.assertIsSatisfied();
    }

    @Test
    public void testHomePageBasicRenderWithEmployeeMale() {
        final Employee male = new AbstractEmployee() {
        };
        male.setGender(Gender.MALE);
        startPage(1, male);
        tester.assertRenderedPage(EmployeePage.class);
        context.assertIsSatisfied();
    }

    @Test
    public void testHomePageBasicRenderWithEmployeeFemale() {
        final Employee female = new AbstractEmployee() {
        };
        female.setGender(Gender.FEMALE);
        startPage(1, female);
        tester.assertRenderedPage(EmployeePage.class);
        context.assertIsSatisfied();
    }


}
