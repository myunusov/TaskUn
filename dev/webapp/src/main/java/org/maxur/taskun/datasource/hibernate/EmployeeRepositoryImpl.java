package org.maxur.taskun.datasource.hibernate;

import org.hibernate.SessionFactory;
import org.maxur.taskun.domain.AbstractEmployee;
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
     * The Hibernate Session Factory.
     */
    private final SessionFactory factory;


    /**
     * Setter for the HibernateTemplate. Unit Testing needs.
     *
     * @param template The SessionFactory
     */
    public final void setHibernateTemplate(final HibernateTemplate template) {
        this.hibernateTemplate = template;
    }

    /**
     * Constructs the instance of EmployeeRepository class.
     * @param sessionFactory The Hibernate Session Factory.
     */
    @Autowired
    public EmployeeRepositoryImpl(final SessionFactory sessionFactory) {
        this.factory = sessionFactory;
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }


    /**
     * @param employee The Employee for saving.
     * @see org.maxur.taskun.domain.EmployeeRepository#save(org.maxur.taskun.domain.AbstractEmployee)
     */
    @Override
    @Benchmark
    public final void save(final AbstractEmployee employee) {
        hibernateTemplate.saveOrUpdate(employee);
        // TODO for constraints detected
        factory.getCurrentSession().flush();
    }

    /**
     * @return The List of Employees.
     * @see org.maxur.taskun.domain.EmployeeRepository#getAll()
     */
    @Override
    @Benchmark
    @SuppressWarnings("unchecked")
    public final List<AbstractEmployee> getAll() {
        return (List<AbstractEmployee>) Collections.unmodifiableList(hibernateTemplate.find(
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
    public final AbstractEmployee get(final String id) {
        return hibernateTemplate.get(EmployeeImpl.class, id);
    }

    /**
     * @param employee The deleted Employee.
     * @see org.maxur.taskun.domain.EmployeeRepository#delete(org.maxur.taskun.domain.AbstractEmployee)
     */
    @Override
    @Benchmark
    public final void delete(final AbstractEmployee employee) {
        hibernateTemplate.delete(employee);
    }
}
