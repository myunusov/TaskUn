package org.maxur.taskun.services;

import junit.framework.Assert;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.commons.domain.AllSpecification;
import org.maxur.commons.domain.Factory;
import org.maxur.commons.domain.Repository;
import org.maxur.commons.domain.Specification;
import org.maxur.taskun.domain.employee.BaseEmployee;
import org.maxur.taskun.domain.employee.Employee;
import org.maxur.taskun.services.Impl.ApplicationControllerImpl;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/9/11
 */
@RunWith(JMock.class)
public class ApplicationControllerImplTest {

    private ApplicationController controller;

    private Mockery context;

    static private Employee dummy = new BaseEmployee("") {};

    @Before
    public void setUp() throws Exception {
        context = new JUnit4Mockery();
    }

    @Test
    public void testCreateEmployee() throws Exception {
        final Factory<Employee> factory = context.mock(Factory.class, "factory");
        controller = new ApplicationControllerImpl(factory, null);
        context.checking(new Expectations() {{
            oneOf(factory).create();
            will(returnValue(dummy));
        }});
        final Employee employee = controller.createEmployee();
        Assert.assertNotNull(employee);
    }

    @Test
    public void testGetAllEmployee() throws Exception {
        final Repository<Employee> repository = context.mock(Repository.class, "repository");
        controller = new ApplicationControllerImpl(null, repository);
        context.checking(new Expectations() {{
            oneOf(repository).getAll();
            will(returnValue(Collections.nCopies(3, dummy)));
        }});
        final Collection<Employee> employees = controller.getAllEmployee();
        Assert.assertEquals(3, employees.size());
    }

    @Test
    public void testGetAllByAllSpecificationEmployee() throws Exception {
        final Repository<Employee> repository = context.mock(Repository.class, "repository");
        controller = new ApplicationControllerImpl(null, repository);
        context.checking(new Expectations() {{
            oneOf(repository).getAll(with(any(Specification.class)));
            will(returnValue(Collections.nCopies(3, dummy)));
        }});
        final Collection<Employee> employees = controller.getAllEmployee(new AllSpecification<Employee>());
        Assert.assertEquals(3, employees.size());
    }

    @Test
    public void testGetEmployee() throws Exception {
        final Repository<Employee> repository = context.mock(Repository.class, "repository");
        controller = new ApplicationControllerImpl(null, repository);
        context.checking(new Expectations() {{
            oneOf(repository).get("1");
            will(returnValue(dummy));
        }});
        final Employee employee = controller.getEmployee("1");
        Assert.assertNotNull(employee);
    }

    @Test
    public void testSaveEmployee() throws Exception {
        final Repository<Employee> repository = context.mock(Repository.class, "repository");
        controller = new ApplicationControllerImpl(null, repository);
        context.checking(new Expectations() {{
            oneOf(repository).save(dummy);
        }});
        controller.saveEmployee(dummy);
        context.assertIsSatisfied();
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        final Repository<Employee> repository = context.mock(Repository.class, "repository");
        controller = new ApplicationControllerImpl(null, repository);
        context.checking(new Expectations() {{
            oneOf(repository).delete(dummy);
        }});
        controller.deleteEmployee(dummy);
        context.assertIsSatisfied();
    }
}
