package org.maxur.taskun.datasource.hibernate;

import org.maxur.taskun.domain.Employee;
import org.maxur.taskun.domain.EmployeeRepository;
import org.maxur.taskun.utils.Benchmark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
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

     //TODO MY exclude after set aop dynamic proxy
    public EmployeeRepositoryImpl() {
    }

    @Autowired
    public EmployeeRepositoryImpl(final HibernateTemplate template) {
        this.hibernateTemplate = template;
    }

    /**
     * Setter for the SessionFactory.
     * @param template The SessionFactory
     */
    @Autowired
    public void setHibernateTemplate(final HibernateTemplate template) {
        this.hibernateTemplate = template;
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
        return (List<Employee>) Collections.unmodifiableList(hibernateTemplate.find(
                "from org.maxur.taskun.datasource.hibernate.EmployeeImpl"
        ));

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
