package org.maxur.taskun.it;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.maxur.taskun.domain.Employee;
import org.maxur.taskun.domain.EmployeeFactory;
import org.maxur.taskun.domain.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>11.07.11</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/spring/applicationContext-test.xml"})
@TransactionConfiguration(transactionManager="txMan1", defaultRollback=false)
@Transactional
public class EmployeeTransactionIT {

    @Autowired
    private EmployeeFactory factory;

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private SessionFactory sessionFactory;

    private Employee employee;

    @BeforeTransaction
    public void verifyInitialDatabaseState() {
        final Collection<Employee> employees = repository.getAll();
        Assert.assertEquals(0, employees.size());
    }

    @Before
    public void setUpTestDataWithinTransaction() {
        employee = factory.create();
		employee.setFirstName("Иван");
        employee.setLastName("Иванов");
        employee.setMiddleName("Иванович");
    }

    @Test
    // overrides the class-level defaultRollback setting
    @Rollback(true)
    public void saveEmployee() {
        repository.save(employee);
        sessionFactory.getCurrentSession().flush();
        final Collection<Employee> employees = repository.getAll();
        Assert.assertEquals(1, employees.size());
    }

    @Test
    @Rollback(true)
    public void deleteEmployee() {
        repository.save(employee);
        sessionFactory.getCurrentSession().flush();
        repository.delete(employee);
        sessionFactory.getCurrentSession().flush();
        final Collection<Employee> employees = repository.getAll();
        Assert.assertEquals(0, employees.size());
    }

    @After
    public void tearDownWithinTransaction() {
        // execute "tear down" logic within the transaction
    }

    @AfterTransaction
    public void verifyFinalDatabaseState() {
        final Collection<Employee> employees = repository.getAll();
        Assert.assertEquals(0, employees.size());
    }


}
