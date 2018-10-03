/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bitjv.cv10.jenc.semestralka_01;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jencmart
 */
@Entity
@Table(name = "POSLICEK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Poslicek.findAll", query = "SELECT p FROM Poslicek p")
    , @NamedQuery(name = "Poslicek.findByPoslicekId", query = "SELECT p FROM Poslicek p WHERE p.poslicekId = :poslicekId")
    , @NamedQuery(name = "Poslicek.findByJmeno", query = "SELECT p FROM Poslicek p WHERE p.jmeno = :jmeno")
    , @NamedQuery(name = "Poslicek.findByPrijmeni", query = "SELECT p FROM Poslicek p WHERE p.prijmeni = :prijmeni")
    , @NamedQuery(name = "Poslicek.findByRodneCislo", query = "SELECT p FROM Poslicek p WHERE p.rodneCislo = :rodneCislo")
    , @NamedQuery(name = "Poslicek.findByDatumNastupu", query = "SELECT p FROM Poslicek p WHERE p.datumNastupu = :datumNastupu")
    , @NamedQuery(name = "Poslicek.findByPlat", query = "SELECT p FROM Poslicek p WHERE p.plat = :plat")})
public class Poslicek implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "POSLICEK_ID")
    private BigDecimal poslicekId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "JMENO")
    private String jmeno;
    @Size(max = 50)
    @Column(name = "PRIJMENI")
    private String prijmeni;
    @Column(name = "RODNE_CISLO")
    private BigInteger rodneCislo;
    @Column(name = "DATUM_NASTUPU")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datumNastupu;
    @Column(name = "PLAT")
    private BigInteger plat;

    public Poslicek() {
    }

    public Poslicek(BigDecimal poslicekId) {
        this.poslicekId = poslicekId;
    }

    public Poslicek(BigDecimal poslicekId, String jmeno) {
        this.poslicekId = poslicekId;
        this.jmeno = jmeno;
    }

    public BigDecimal getPoslicekId() {
        return poslicekId;
    }

    public void setPoslicekId(BigDecimal poslicekId) {
        this.poslicekId = poslicekId;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public BigInteger getRodneCislo() {
        return rodneCislo;
    }

    public void setRodneCislo(BigInteger rodneCislo) {
        this.rodneCislo = rodneCislo;
    }

    public Date getDatumNastupu() {
        return datumNastupu;
    }

    public void setDatumNastupu(Date datumNastupu) {
        this.datumNastupu = datumNastupu;
    }

    public BigInteger getPlat() {
        return plat;
    }

    public void setPlat(BigInteger plat) {
        this.plat = plat;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (poslicekId != null ? poslicekId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Poslicek)) {
            return false;
        }
        Poslicek other = (Poslicek) object;
        if ((this.poslicekId == null && other.poslicekId != null) || (this.poslicekId != null && !this.poslicekId.equals(other.poslicekId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.cz.cvut.fit.bitjv.cv10.jenc.semestralka_01.Poslicek[ poslicekId=" + poslicekId + " ]";
    }
    
}
