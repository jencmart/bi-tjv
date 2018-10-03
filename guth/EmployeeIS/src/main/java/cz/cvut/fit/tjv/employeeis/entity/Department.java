package cz.cvut.fit.tjv.employeeis.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * JPA entita.
 * @author guthondr
 */

@Entity
@IdClass(DepartmentPK.class)
@NamedQuery(name = "Department.SelectAll", query = "SELECT d FROM Department d")
public class Department implements Serializable {

    //Cast slozeneho primarni klic
    @Id
    private String title;
    
    //Cast slozeneho primarni klic
    @Id
    private String site;

    @OneToMany(mappedBy = "department")
    private List<Employee> employees;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    
    

}
