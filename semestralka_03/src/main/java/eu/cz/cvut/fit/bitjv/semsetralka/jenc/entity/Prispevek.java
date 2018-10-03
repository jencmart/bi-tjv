/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bitjv.semsetralka.jenc.entity;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jencmart
 */
@Entity(name = "PRISPEVEK")
@Table(name = "PRISPEVEK")
@XmlRootElement
public class Prispevek implements Serializable {

    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name = "PRISPEVEK_ID")
    private Long prispevekId;
    
    @Column(name = "NADPIS")
    private String nadpis;
    
    @Column(name = "TELO")
    private String telo;
    
    @JoinColumn(name = "UZIVATEL_ID", referencedColumnName = "UZIVATEL_ID")
    @ManyToOne(optional = false)
    private Uzivatel uzivatelId;

    public Prispevek() {
    }

    public Prispevek(Long prispevekId) {
        this.prispevekId = prispevekId;
    }

    public Long getPrispevekId() {
        return prispevekId;
    }

    public void setPrispevekId(Long prispevekId) {
        this.prispevekId = prispevekId;
    }

    public String getNadpis() {
        return nadpis;
    }

    public void setNadpis(String nadpis) {
        this.nadpis = nadpis;
    }

    public String getTelo() {
        return telo;
    }

    public void setTelo(String telo) {
        this.telo = telo;
    }

    public Uzivatel getUzivatelId() {
        return uzivatelId;
    }

    public void setUzivatelId(Uzivatel uzivatelId) {
        this.uzivatelId = uzivatelId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prispevekId != null ? prispevekId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prispevek)) {
            return false;
        }
        Prispevek other = (Prispevek) object;
        if ((this.prispevekId == null && other.prispevekId != null) || (this.prispevekId != null && !this.prispevekId.equals(other.prispevekId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Prispevek[ prispevekId=" + prispevekId + " ]";
    }
    
}
