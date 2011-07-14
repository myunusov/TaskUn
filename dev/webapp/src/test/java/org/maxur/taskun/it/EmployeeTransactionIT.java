package org.maxur.taskun.it;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.maxur.taskun.domain.AbstractEmployee;
import org.maxur.taskun.domain.EmployeeFactory;
import org.maxur.taskun.domain.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Nullable;
import java.util.Collection;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>11.07.11</pre>
 */
@ContextConfiguration(locations={"/spring/applicationContext-test.xml"})
@TransactionConfiguration(transactionManager="txMan2", defaultRollback=false)
public class EmployeeTransactionIT extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private EmployeeFactory factory;

    @Autowired
    private EmployeeRepository repository;


    @BeforeTransaction
    public void verifyInitialDatabaseState() {
        final Collection<AbstractEmployee> employees = repository.getAll();
        Assert.assertEquals(0, employees.size());
    }

    @Before
    public void setUpTestDataWithinTransaction() {
    }

    @Test
    @Rollback(true)
    public void saveEmployee() {
        repository.save(createEmployee("Иван", "Иванов", "Иванович"));
        final Collection<AbstractEmployee> employees = repository.getAll();
        Assert.assertEquals(1, employees.size());
    }

    @Test
    @Rollback(true)
    public void deleteEmployee() {
        final AbstractEmployee employee = createEmployee("Иван", "Иванов", "Иванович");
        repository.save(employee);
        repository.delete(employee);
        final Collection<AbstractEmployee> employees = repository.getAll();
        Assert.assertEquals(0, employees.size());
    }

    @Test(expected = ConstraintViolationException.class)
    @Rollback(true)
    public void saveDuplicateEmployee() {
        repository.save(createEmployee("Иван", "Иванов", "Иванович"));
        repository.save(createEmployee("Иван", "Иванов", "Иванович"));
    }

    @Test(expected = ConstraintViolationException.class)
    @Rollback(true)
    public void saveDuplicateEmployeeWithNulltMiddleName() {
        repository.save(createEmployee("Иван", "Иванов", null));
        repository.save(createEmployee("Иван", "Иванов", null));
    }

    @Test(expected = ConstraintViolationException.class)
    @Rollback(true)
    public void saveDuplicateEmployeeWithoutMiddleName() {
        repository.save(createEmployee("Иван", "Иванов"));
        repository.save(createEmployee("Иван", "Иванов"));
    }

    @After
    public void tearDownWithinTransaction() {
        // execute "tear down" logic within the transaction
    }

    @AfterTransaction
    public void verifyFinalDatabaseState() {
        final Collection<AbstractEmployee> employees = repository.getAll();
        Assert.assertEquals(0, employees.size());
    }

    private AbstractEmployee createEmployee(final String firstName, final String lastName, @Nullable final String middleName) {
        AbstractEmployee result = createEmployee(firstName, lastName);
        result.setMiddleName(middleName);
        return result;
    }

    private AbstractEmployee createEmployee(final String firstName, final String lastName) {
        AbstractEmployee result = factory.create();
        result.setFirstName(firstName);
        result.setLastName(lastName);
        return result;
    }

}
