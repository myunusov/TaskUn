package org.maxur.taskun.datasource.hibernate;

import org.hibernate.SessionFactory;
import org.maxur.commons.domain.Specification;
import org.maxur.taskun.domain.employee.Employee;
import org.maxur.commons.annotation.Benchmark;
import org.springframework.beans.factory.annotation.Autowired;
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
@Repository("employeeRepository")
public class EmployeeRepositoryImpl extends RepositoryImpl implements org.maxur.commons.domain.Repository<Employee> {


    /**
     * Constructs the instance of EmployeeRepository class.
     *
     * @param sessionFactory The Hibernate Session Factory.
     */
    @Autowired
    public EmployeeRepositoryImpl(final SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    /**
     * @param entity The Employee for saving.
     * @see org.maxur.commons.domain.Repository#save(org.maxur.commons.domain.Entity)
     */
    @Override
    @Benchmark
    public final void save(final Employee entity) {
        this.getHibernateTemplate().saveOrUpdate(entity);
        // TODO for constraints detected
       // this.getFactory().getCurrentSession().flush();
    }

    /**
     * @return The List of Employees.
     * @see org.maxur.commons.domain.Repository#getAll()
     */
    @Override
    @Benchmark
    @SuppressWarnings("unchecked")
    public final List<Employee> getAll() {
        return (List<Employee>) this.getHibernateTemplate().find(
                "from org.maxur.taskun.datasource.hibernate.EmployeeImpl"
        );

    }

    /**
     * @param specification The some condition for select employees.
     * @return The List of Employees by specification.
     * @see org.maxur.commons.domain.Repository#getAll(org.maxur.commons.domain.Specification)
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
     * @see org.maxur.commons.domain.Repository#get(String)
     */
    @Override
    @Benchmark
    public final Employee get(final String id) {
        return this.getHibernateTemplate().get(EmployeeImpl.class, id);
    }

    /**
     * @param entity The deleted Employee.
     * @see org.maxur.commons.domain.Repository#delete(org.maxur.commons.domain.Entity)
     */
    @Override
    @Benchmark
    public final void delete(final Employee entity) {
        this.getHibernateTemplate().delete(entity);
    }

}
