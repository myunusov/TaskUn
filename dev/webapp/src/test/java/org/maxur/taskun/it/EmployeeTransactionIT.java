package org.maxur.taskun.it;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.maxur.commons.domain.Repository;
import org.maxur.taskun.domain.employee.Employee;
import org.maxur.taskun.domain.employee.EmployeeBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Nullable;
import javax.validation.ConstraintViolationException;
import java.util.Collection;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>11.07.11</pre>
 */
@ContextConfiguration(locations={"/spring/applicationContext-test.xml"})
@TransactionConfiguration(transactionManager="txMan2", defaultRollback=false)
public class EmployeeTransactionIT extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired()
    @Qualifier(value = "employeeRepository")
    private Repository<Employee> repository;

    @Autowired()
    private EmployeeBuilder<Employee> builder;

    @Autowired
    private SessionFactory sessionFactory;


    @BeforeTransaction
    public void verifyInitialDatabaseState() {
        final Collection<Employee> employees = repository.getAll();
        Assert.assertEquals(0, employees.size());
    }

    @Before
    public void setUpTestDataWithinTransaction() {
    }

    @Test
    @Rollback(true)
    public void saveEmployee() {
        repository.save(createEmployee("Иван", "Иванов", "Иванович"));
        final Collection<Employee> employees = repository.getAll();
        Assert.assertEquals(1, employees.size());
    }

    @Test
    @Rollback(true)
    public void deleteEmployee() {
        final Employee employee = createEmployee("Иван", "Иванов", "Иванович");
        repository.save(employee);
        final Employee employee1 = repository.getByNames("Иван", "Иванов", "Иванович");
        repository.delete(employee1);
        final Collection<Employee> employees = repository.getAll();
        Assert.assertEquals(0, employees.size());
    }

    @Test(expected = ConstraintViolationException.class)
    @Rollback(true)
    public void saveDuplicateEmployee() {
        repository.save(createEmployee("Иван", "Иванов", "Иванович"));
        repository.save(createEmployee("Иван", "Иванов", "Иванович"));
        sessionFactory.getCurrentSession().flush();

    }

    @Test(expected = ConstraintViolationException.class)
    @Rollback(true)
    public void saveDuplicateEmployeeWithNulltMiddleName() {
        repository.save(createEmployee("Иван", "Иванов", null));
        repository.save(createEmployee("Иван", "Иванов", null));
        sessionFactory.getCurrentSession().flush();
    }

    @Test(expected = ConstraintViolationException.class)
    @Rollback(true)
    public void saveDuplicateEmployeeWithoutMiddleName() {
        repository.save(builder.withFirstName("Иван").withLastName("Иванов"));
        repository.save(builder.withFirstName("Иван").withLastName("Иванов"));
        sessionFactory.getCurrentSession().flush();
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

    private Employee createEmployee(final String firstName, final String lastName, @Nullable final String middleName) {
        return builder
                .withFirstName(firstName)
                .withLastName(lastName)
                .withMiddleName(middleName);
    }

}
