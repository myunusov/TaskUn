package org.maxur.taskun.view.model.employee;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.util.tester.WicketTester;
import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.maxur.taskun.domain.Gender;
import org.maxur.taskun.domain.employee.Employee;
import org.maxur.taskun.view.pages.StubWebApplication;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/28/11
 */
public class EmployeeBeanTest {

    private AjaxRequestTarget target;

    private JUnit4Mockery context;

    private EmployeesGroup group;

    private EmployeeBean bean;

    private Employee employee;

    private StubWebApplication application;

    @Before
    public void setUp() throws Exception {
        context = new JUnit4Mockery() {{
            setImposteriser(ClassImposteriser.INSTANCE);
        }};
        application = new StubWebApplication(context);
        new WicketTester(application);
        group = context.mock(EmployeesGroup.class);
        employee = context.mock(Employee.class);
        target = context.mock(AjaxRequestTarget.class);
    }

    @Test
    public void testCreateNew() throws Exception {
        context.checking(new Expectations() {{
            oneOf(application.controller).createEmployee();
        }});
        bean = new EmployeeBean(group);
    }

    @Test
    public void testGetExisting() throws Exception {
        context.checking(new Expectations() {{
        }});
        bean = new EmployeeBean(group, employee);
        context.assertIsSatisfied();
    }


    @Test
    public void testGetIdentifier() throws Exception {
        bean = new EmployeeBean(group, employee);
        context.checking(new Expectations() {{
            oneOf(employee).getIdentifier();
        }});
        bean.getIdentifier();
        context.assertIsSatisfied();
    }

    @Test
    public void testGetFirstName() throws Exception {
        bean = new EmployeeBean(group, employee);
        context.checking(new Expectations() {{
            oneOf(employee).getFirstName();
        }});
        bean.getFirstName();
        context.assertIsSatisfied();
    }

    @Test
    public void testGetLastName() throws Exception {
        bean = new EmployeeBean(group, employee);
        context.checking(new Expectations() {{
            oneOf(employee).getLastName();
        }});
        bean.getLastName();
        context.assertIsSatisfied();
    }

    @Test
    public void testGetMiddleName() throws Exception {
        bean = new EmployeeBean(group, employee);
        context.checking(new Expectations() {{
            oneOf(employee).getMiddleName();
        }});
        bean.getMiddleName();
        context.assertIsSatisfied();
    }

    @Test
    public void testGetTitle() throws Exception {
        bean = new EmployeeBean(group, employee);
        context.checking(new Expectations() {{
            oneOf(employee).getTitle();
        }});
        bean.getTitle();
        context.assertIsSatisfied();
    }

    @Test
    public void testSetFirstName() throws Exception {
        bean = new EmployeeBean(group, employee);
        context.checking(new Expectations() {{
            oneOf(employee).setFirstName("1");
        }});
        bean.setFirstName("1");
        context.assertIsSatisfied();
    }

    @Test
    public void testSetLastName() throws Exception {
        bean = new EmployeeBean(group, employee);
        context.checking(new Expectations() {{
            oneOf(employee).setLastName("1");
        }});
        bean.setLastName("1");
        context.assertIsSatisfied();
    }

    @Test
    public void testSetMiddleName() throws Exception {
        bean = new EmployeeBean(group, employee);
        context.checking(new Expectations() {{
            oneOf(employee).setMiddleName("1");
        }});
        bean.setMiddleName("1");
        context.assertIsSatisfied();
    }


    @Test
    public void testGetGender() throws Exception {
        bean = new EmployeeBean(group, employee);
        context.checking(new Expectations() {{
            oneOf(employee).getGender();
        }});
        bean.getGender();
        context.assertIsSatisfied();
    }

    @Test
    public void testSetGender() throws Exception {
        bean = new EmployeeBean(group, employee);
        context.checking(new Expectations() {{
            oneOf(employee).setGender(Gender.UNKNOWN);
        }});
        bean.setGender(Gender.UNKNOWN);
        context.assertIsSatisfied();
    }


/*    @Test
    public void testGetImageName() throws Exception {
        bean = new EmployeeBean(group, employee);
        context.checking(new Expectations() {{
            oneOf(employee).getIdentifier();
            oneOf(employee).getGender();
        }});
        bean.getImageName();
        context.assertIsSatisfied();
    }*/

    @Test
    public void testIsNewCheckNew() throws Exception {
        bean = new EmployeeBean(group, employee);
        context.checking(new Expectations() {{
            oneOf(employee).getIdentifier();
            will(returnValue(null));
        }});
        Assert.assertTrue(bean.isNew());
        context.assertIsSatisfied();
    }

    @Test
    public void testIsNewCheckOld() throws Exception {
        bean = new EmployeeBean(group, employee);
        context.checking(new Expectations() {{
            oneOf(employee).getIdentifier();
            will(returnValue("1"));
        }});
        Assert.assertFalse(bean.isNew());
        context.assertIsSatisfied();
    }

    @Test
    public void testIsSelected() throws Exception {
        bean = new EmployeeBean(group, employee);
        context.checking(new Expectations() {{
            oneOf(group).isSelected(bean);
        }});
        bean.isSelected();
        context.assertIsSatisfied();
    }

    @Test
    public void testSelect() throws Exception {
        bean = new EmployeeBean(group, employee);
        context.checking(new Expectations() {{
            oneOf(group).select(target, bean);
        }});
        bean.select(target);
        context.assertIsSatisfied();
    }

    @Test
    public void testDelete() throws Exception {
        bean = new EmployeeBean(group, employee);
        context.checking(new Expectations() {{
            oneOf(application.controller).deleteEmployee(employee);
        }});
        bean.delete();
        context.assertIsSatisfied();
    }

    @Test
    public void testSaveOld() throws Exception {
        bean = new EmployeeBean(group, employee);
        context.checking(new Expectations() {{
            oneOf(employee).getIdentifier();
            oneOf(application.controller).saveEmployee(employee);
        }});
        bean.save(target);
        context.assertIsSatisfied();
    }

    @Test
    public void testSaveNew() throws Exception {
        bean = new EmployeeBean(group, employee);
        context.checking(new Expectations() {{
            oneOf(employee).getIdentifier();
            will(returnValue(null));
            oneOf(application.controller).saveEmployee(employee);
            oneOf(group).addEmployee(target, bean);
        }});
        bean.save(target);
        context.assertIsSatisfied();
    }


    @Test
    public void testGetObject() throws Exception {
        bean = new EmployeeBean(group, employee);
        Assert.assertEquals(employee, bean.getObject());
    }

    @Test
    public void testDetach() throws Exception {
        bean = new EmployeeBean(group, employee);
        bean.detach();
    }

    @Test
    public void testEquals() throws Exception {
        bean = new EmployeeBean(group, employee);
        Assert.assertEquals(new EmployeeBean(group, employee), bean);
    }

    @Test
    public void testHashCode() throws Exception {
        bean = new EmployeeBean(group, employee);
        Assert.assertNotNull(bean.hashCode());
    }

    @Test
    public void testToString() throws Exception {
        bean = new EmployeeBean(group, employee);
        Assert.assertNotNull(bean.toString());
    }
}
