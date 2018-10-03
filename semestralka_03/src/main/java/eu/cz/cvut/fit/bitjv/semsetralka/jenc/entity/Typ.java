/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bitjv.semsetralka.jenc.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jencmart
 */
@Entity(name = "TYP")
@Table(name = "TYP")
@XmlRootElement
public class Typ implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   
    @Column(name = "TYP_ID")
    private Long typId;
   
    @Column(name = "POPIS")
    private String popis;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typId")
    private Collection<Uzivatel> uzivatelCollection;

    public Typ() {
    }

    public Typ(Long typId) {
        this.typId = typId;
    }

    public Typ(Long typId, String popis) {
        this.typId = typId;
        this.popis = popis;
    }

    public Long getTypId() {
        return typId;
    }

    public void setTypId(Long typId) {
        this.typId = typId;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    @XmlTransient
    public Collection<Uzivatel> getUzivatelCollection() {
        return uzivatelCollection;
   }

    public void setUzivatelCollection(Collection<Uzivatel> uzivatelCollection) {
        this.uzivatelCollection = uzivatelCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typId != null ? typId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Typ)) {
            return false;
        }
        Typ other = (Typ) object;
        if ((this.typId == null && other.typId != null) || (this.typId != null && !this.typId.equals(other.typId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Typ[ typId=" + typId + " ]";
    }
    
}
