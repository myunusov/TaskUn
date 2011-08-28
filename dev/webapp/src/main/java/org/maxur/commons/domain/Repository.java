package org.maxur.commons.domain;

import org.maxur.taskun.domain.employee.Employee;

import javax.annotation.Nullable;
import java.util.List;

/**
 * The common Entity Repository interface.
 *
 * @author Maxim Yunusov
 * @version 1.0 7/25/11
 */
public interface Repository<T extends Entity> {

    /**
     * Get all Entities.
     * @return The List of Entities.
     */
    List<T> getAll();

    /**
     * Get all Entities by Specification.
     * @param specification The some criteria for select entities.
     *
     * @return The List of Selected Entities.
     */
    List<T> getAll(Specification<T> specification);


    /**
     * Save the Entity.
     * @param entity The Entity to saveEmployee.
     */
    void save(T entity);

    /**
     * Get Entity by it's identifier.
     * @param id The Entity identifier.
     * @return The selected Entity.
     */
    T get(String id);

    /**
     * Delete the Entity.
     * @param entity The Entity to delete.
     */
    void delete(T entity);

    Employee getByKey(String firstName, String lastName, @Nullable String middleName);
}
