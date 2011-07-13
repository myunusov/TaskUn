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
     *
     * @see HibernateTemplate
     */
    private HibernateTemplate hibernateTemplate;

    /**
     * Constructs the instance of EmployeeRepository class.
     * It Needs for CGLIB Proxy.
     */
    public EmployeeRepositoryImpl() {
        //TODO MY exclude after set aop dynamic proxy
    }


    /**
     * Constructs the instance of EmployeeRepository class.
     * @param template The Hibernate Template bean.
     */
    @Autowired
    public EmployeeRepositoryImpl(final HibernateTemplate template) {
        this.hibernateTemplate = template;
    }

    /**
     * Setter for the SessionFactory.
     *
     * @param template The SessionFactory
     */
    @Autowired
    public void setHibernateTemplate(final HibernateTemplate template) {
        this.hibernateTemplate = template;
    }

    /**
     * @param employee The Employee for saving.
     * @see org.maxur.taskun.domain.EmployeeRepository#save(Employee)
     */
    @Override
    @Benchmark
    public void save(final Employee employee) {
        hibernateTemplate.saveOrUpdate(employee);
    }

    /**
     * @return The List of Employees.
     * @see org.maxur.taskun.domain.EmployeeRepository#getAll()
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
     * @param id The Employees identifier.
     * @return The selected Employee.
     * @see org.maxur.taskun.domain.EmployeeRepository#get(String)
     */
    @Override
    @Benchmark
    public Employee get(final String id) {
        return hibernateTemplate.get(EmployeeImpl.class, id);
    }

    /**
     * @param employee The deleted Employee.
     * @see org.maxur.taskun.domain.EmployeeRepository#delete(org.maxur.taskun.domain.Employee)
     */
    @Override
    @Benchmark
    public void delete(final Employee employee) {
        hibernateTemplate.delete(employee);
    }
}
