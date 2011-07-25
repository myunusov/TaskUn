package org.maxur.taskun.datasource.hibernate;

import org.hibernate.SessionFactory;
import org.maxur.taskun.domain.Specification;
import org.maxur.taskun.domain.employee.Employee;
import org.maxur.taskun.utils.Benchmark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
public class EmployeeRepositoryImpl implements org.maxur.taskun.domain.Repository<Employee> {

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
    protected final void setHibernateTemplate(final HibernateTemplate template) {
        this.hibernateTemplate = template;
    }

    /**
     * Constructs the instance of EmployeeRepository class.
     *
     * @param sessionFactory The Hibernate Session Factory.
     */
    @Autowired
    public EmployeeRepositoryImpl(final SessionFactory sessionFactory) {
        this.factory = sessionFactory;
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }


    /**
     * @param entity The Employee for saving.
     * @see org.maxur.taskun.domain.Repository#save(org.maxur.taskun.domain.Entity)
     */
    @Override
    @Benchmark
    public final void save(final Employee entity) {
        this.hibernateTemplate.saveOrUpdate(entity);
        // TODO for constraints detected
        this.factory.getCurrentSession().flush();
    }

    /**
     * @return The List of Employees.
     * @see org.maxur.taskun.domain.Repository#getAll()
     */
    @Override
    @Benchmark
    @SuppressWarnings("unchecked")
    public final List<Employee> getAll() {
        return (List<Employee>) this.hibernateTemplate.find(
                "from org.maxur.taskun.datasource.hibernate.EmployeeImpl"
        );

    }

    /**
     * @param specification The some condition for select employees.
     * @return The List of Employees by specification.
     * @see org.maxur.taskun.domain.Repository#getAll(org.maxur.taskun.domain.Specification)
     */
    @Override
    @Benchmark
    public final List<Employee> getAll(final Specification<Employee> specification) {
        //Todo performance issue
        final List<Employee> list = new ArrayList<Employee>();
        for (final Employee employee : getAll()) {
            if (specification.isSatisfiedBy(employee)) {
                list.add(employee);
            }
        }
        return Collections.unmodifiableList(list);
    }


    /**
     * @param id The Employees identifier.
     * @return The selected Employee.
     * @see org.maxur.taskun.domain.Repository#get(String)
     */
    @Override
    @Benchmark
    public final Employee get(final String id) {
        return this.hibernateTemplate.get(EmployeeImpl.class, id);
    }

    /**
     * @param entity The deleted Employee.
     * @see org.maxur.taskun.domain.Repository#delete(org.maxur.taskun.domain.Entity)
     */
    @Override
    @Benchmark
    public final void delete(final Employee entity) {
        this.hibernateTemplate.delete(entity);
    }
}
