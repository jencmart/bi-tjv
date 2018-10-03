package cz.cvut.fit.tjv.employeeis.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 * Trida reprezentujici slozeny primarni klic v entite Department
 * @author guthondr
 */
@Embeddable
public class DepartmentPK implements Serializable {
    private String title;
    private String site;
    
    public DepartmentPK() {}

    public DepartmentPK(String title, String site) {
        this.title = title;
        this.site = site;
    }
    
    

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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.title);
        hash = 97 * hash + Objects.hashCode(this.site);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DepartmentPK other = (DepartmentPK) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.site, other.site)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
