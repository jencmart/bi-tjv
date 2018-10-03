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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Entity(name = "UZIVATEL")
@Table(name = "UZIVATEL")
@XmlRootElement
public class Uzivatel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "UZIVATEL_ID")
    private Long uzivatelId;
    
    @Column(name = "JMENO")
    private String jmeno;
  
    @Column(name = "NICKNAME")
    private String nickname;
  
    @Column(name = "EMAIL")
    private String email;
   
    @JoinColumn(name = "TYP_ID", referencedColumnName = "TYP_ID")
    @ManyToOne(optional = false)
    private Typ typId;
  //  @OneToMany(cascade = CascadeType.ALL, mappedBy = "uzivatelId")
   // private Collection<Prispevek> prispevekCollection;

    public Uzivatel() {
    }

    public Uzivatel(Long uzivatelId) {
        this.uzivatelId = uzivatelId;
    }

    public Uzivatel(Long uzivatelId, String nickname, String email) {
        this.uzivatelId = uzivatelId;
        this.nickname = nickname;
        this.email = email;
    }

    public Long getUzivatelId() {
        return uzivatelId;
    }

    public void setUzivatelId(Long uzivatelId) {
        this.uzivatelId = uzivatelId;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Typ getTypId() {
        return typId;
    }

    public void setTypId(Typ typId) {
        this.typId = typId;
    }

//    @XmlTransient
//    public Collection<Prispevek> getPrispevekCollection() {
//        return prispevekCollection;
//    }

//    public void setPrispevekCollection(Collection<Prispevek> prispevekCollection) {
//        this.prispevekCollection = prispevekCollection;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uzivatelId != null ? uzivatelId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uzivatel)) {
            return false;
        }
        Uzivatel other = (Uzivatel) object;
        if ((this.uzivatelId == null && other.uzivatelId != null) || (this.uzivatelId != null && !this.uzivatelId.equals(other.uzivatelId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Uzivatel[ uzivatelId=" + uzivatelId + " ]";
    }
    
}
