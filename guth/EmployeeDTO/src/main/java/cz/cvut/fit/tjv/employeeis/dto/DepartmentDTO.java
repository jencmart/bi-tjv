package cz.cvut.fit.tjv.employeeis.dto;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Data transition object. Prenosovy objekt pro webovou sluzbu (mozno vyuzit i v klientu).
 * @author guthondr
 */

@XmlRootElement
public class DepartmentDTO implements Serializable {
    private String title;
    private String site;
    
    /**
     * Tento atribut nechceme serializovat - bug v JAXB i JETTY-JSON
     */
    @XmlTransient
    private transient List<EmployeeDTO> employees;

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

}
