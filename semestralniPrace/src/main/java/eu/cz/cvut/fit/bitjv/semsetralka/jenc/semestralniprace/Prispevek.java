/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bitjv.semsetralka.jenc.semestralniprace;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jencmart
 */
@Entity
@Table(name = "PRISPEVEK")
public class Prispevek implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(name = "NADPIS")
    private String title;
    
    @Column(name = "TELO")
    private String body;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "driver_car",
       joinColumns = @JoinColumn(name = "car_id"),
       inverseJoinColumns = @JoinColumn(name = "driver_id")
    )
     
    private List<Prispevek> prispevek;

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
    
    @XmlTransient
    public List<Prispevek> getPrispevek() {
        return prispevek;
    }

    public void setPrispevek(List<Prispevek> driver) {
        this.prispevek = driver;
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
        if (!(object instanceof Prispevek)) {
            return false;
        }
        Prispevek other = (Prispevek) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.cz.cvut.fit.bitjv.semsetralka.jenc.semestralniprace.Prispevek[ id=" + id + " ]";
    }
}