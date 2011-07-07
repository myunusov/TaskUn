package org.maxur.taskun.datasource.hibernate;

import org.hibernate.SessionFactory;
import org.maxur.taskun.domain.EmployeeRepository;
import org.maxur.taskun.domain.Employee;
import org.maxur.taskun.utils.Benchmark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The Employee Repository (hibernate implementation).
 *
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>04.07.11</pre>
 */
@Repository("employeeDao")
public class EmployeeRepositoryImpl implements EmployeeRepository {

    /**
     * Instance of Helper class that simplifies Hibernate data access code.
     * @see HibernateTemplate
     */
    private HibernateTemplate hibernateTemplate;

    /**
     * Setter for the SessionFactory.
     * @param sessionFactory The SessionFactory
     */
    @Autowired
    public final void setSessionFactory(final SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }

    /**
     * @see org.maxur.taskun.domain.EmployeeRepository#save(Employee)
     * @param employee The Employee for saving.
     */
    @Override
    @Benchmark
    public void save(final Employee employee) {
        hibernateTemplate.saveOrUpdate(employee);
    }

    /**
     * @see org.maxur.taskun.domain.EmployeeRepository#getAll()
     * @return The List of Employees.
     */
    @Override
    @Benchmark
    @SuppressWarnings("unchecked")
    public List<Employee> getAll() {
        return (List<Employee>) hibernateTemplate.find(
                "from org.maxur.taskun.datasource.hibernate.EmployeeImpl"
        );

    }

    /**
     * @see org.maxur.taskun.domain.EmployeeRepository#get(String)
     * @param id The Employees identifier.
     * @return The selected Employee.
     */
    @Override
    @Benchmark
    public Employee get(final String id) {
        return hibernateTemplate.get(EmployeeImpl.class, id);
    }

    /**
     * @see org.maxur.taskun.domain.EmployeeRepository#delete(org.maxur.taskun.domain.Employee)
     * @param employee The deleted Employee.
     */
    @Override
    @Benchmark
    public void delete(final Employee employee) {
        hibernateTemplate.delete(employee);
    }
}
