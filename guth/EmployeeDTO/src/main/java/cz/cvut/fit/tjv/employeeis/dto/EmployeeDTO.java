package cz.cvut.fit.tjv.employeeis.dto;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Data transition object. Prenosovy objekt pro webovou sluzbu (mozno vyuzit i v klientu).
 * @author guthondr
 */

@XmlRootElement
public class EmployeeDTO implements Serializable {
    private Long id;
    private String name;
    private DepartmentDTO department;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DepartmentDTO getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentDTO department) {
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmployeeDTO)) {
            return false;
        }
        EmployeeDTO other = (EmployeeDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cz.cvut.fit.tjv.employeeis.entity.Employee[ id=" + id + " ]";
    }
    
}
