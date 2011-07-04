package org.maxur.taskun.datasource.hibernate;

import org.hibernate.SessionFactory;
import org.maxur.taskun.datasource.EmployeeDAO;
import org.maxur.taskun.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Maxim Yunusov
 * @version 1.0
 * @since <pre>04.07.11</pre>
 */
@Repository("employeeDao")
@Transactional
public class EmployeeDAOImpl implements EmployeeDAO {


    private HibernateTemplate hibernateTemplate;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }


    @Override
    @Transactional(readOnly = false)
    public void save(Employee employee) {
        hibernateTemplate.saveOrUpdate(employee);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> getAll() {
        return (List<Employee>) hibernateTemplate.find("from " + Employee.class.getName());

    }

    @Override
    public Employee get(String id) {
        return hibernateTemplate.get(Employee.class, id);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(Employee employee) {
        hibernateTemplate.delete(employee);
    }
}
