package org.maxur.taskun.datasource.hibernate;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.taskun.domain.Employee;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.Collection;
import java.util.Collections;

/**
 * @author Maxim Yunusov
 * @version 1.0 7/9/11
 */
@RunWith(JMock.class)
public class EmployeeRepositoryImplTest {

    private Mockery context;

    private EmployeeRepositoryImpl repository;

    Employee dummy = new Employee() {
    };

    @Before
    public void setUp() throws Exception {
        context = new JUnit4Mockery() {{
        setImposteriser(ClassImposteriser.INSTANCE);
        }};
        repository = new EmployeeRepositoryImpl();
    }

    @Test
    public void testSave() throws Exception {
        final HibernateTemplate template = context.mock(HibernateTemplate.class);
        repository.setHibernateTemplate(template);
        context.checking(new Expectations() {{
            oneOf(template).saveOrUpdate(dummy);
        }});
        repository.save(dummy);
        context.assertIsSatisfied();
    }

    @Test
    public void testGetAll() throws Exception {
        final HibernateTemplate template = context.mock(HibernateTemplate.class);
        repository.setHibernateTemplate(template);
        context.checking(new Expectations() {{
            oneOf(template).find("from org.maxur.taskun.datasource.hibernate.EmployeeImpl");
        }});
        repository.getAll();
        context.assertIsSatisfied();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetAllCheckUnmodifiableResult() throws Exception {
        final HibernateTemplate template = context.mock(HibernateTemplate.class);
        repository.setHibernateTemplate(template);
        context.checking(new Expectations() {{
            oneOf(template).find(with(any(String.class)));
            will(returnValue(Collections.nCopies(3, dummy)));
        }});
        final Collection<Employee> all = repository.getAll();
        all.add(dummy);
        context.assertIsSatisfied();
    }

    @Test
    public void testGet() throws Exception {
        final HibernateTemplate template = context.mock(HibernateTemplate.class);
        repository.setHibernateTemplate(template);
        context.checking(new Expectations() {{
            oneOf(template).get(EmployeeImpl.class, "1");
            will(returnValue(dummy));
        }});
        Employee result = repository.get("1");
        context.assertIsSatisfied();
        Assert.assertEquals(dummy, result);
    }

    @Test
    public void testDelete() throws Exception {
        final HibernateTemplate template = context.mock(HibernateTemplate.class);
        repository.setHibernateTemplate(template);
        context.checking(new Expectations() {{
            oneOf(template).delete(dummy);
        }});
        repository.delete(dummy);
        context.assertIsSatisfied();
    }
}
