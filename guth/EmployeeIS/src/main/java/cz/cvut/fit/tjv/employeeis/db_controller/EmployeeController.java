package cz.cvut.fit.tjv.employeeis.db_controller;

import cz.cvut.fit.tjv.employeeis.entity.Employee;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * JPA kontroler.
 * @author guthondr
 */

@Stateless
public class EmployeeController {
    @PersistenceContext
    private EntityManager entityManager;
    
    public void createOrUpdate(Employee e) {
        entityManager.merge(e);
    }
    
    public Employee selectById(long id) {
        return entityManager.find(Employee.class, id);
    }
    
    public Collection<Employee> selectAll() {
        return entityManager
                .createQuery("SELECT e FROM Employee e", Employee.class)
                .getResultList();
    }
    
    public void delete(long id) {
        Employee e = selectById(id);
        if (e != null)
            entityManager.remove(e);
    }
}
