package cz.cvut.fit.tjv.employeeis.db_controller;

import cz.cvut.fit.tjv.employeeis.entity.Department;
import cz.cvut.fit.tjv.employeeis.entity.DepartmentPK;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * JPA kontroler.
 * @author guthondr
 */

@Stateless
public class DepartmentController {

    @PersistenceContext
    private EntityManager entityManager;

    public void createOrUpdate(Department d) {
        entityManager.merge(d);
    }

    public Department selectById(DepartmentPK id) {
        return entityManager.find(Department.class, id);
    }
    
    public Department selectById(String title, String site) {
        return selectById(new DepartmentPK(title, site));
    }
    
    public Collection<Department> selectAll() {
        return entityManager
                .createNamedQuery("Department.SelectAll", Department.class)
                .getResultList();
    }

    public void delete(DepartmentPK id) {
        Department d = selectById(id);
        if (d != null) {
            entityManager.remove(d);
        }
    }

    public void delete(String title, String site) {
        delete(new DepartmentPK(title, site));
    }

}
