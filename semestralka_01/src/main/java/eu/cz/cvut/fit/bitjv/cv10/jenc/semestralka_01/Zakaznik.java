/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bitjv.cv10.jenc.semestralka_01;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Entity
@Table(name = "ZAKAZNIK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zakaznik.findAll", query = "SELECT z FROM Zakaznik z")
    , @NamedQuery(name = "Zakaznik.findByZakaznikId", query = "SELECT z FROM Zakaznik z WHERE z.zakaznikId = :zakaznikId")
    , @NamedQuery(name = "Zakaznik.findByMail", query = "SELECT z FROM Zakaznik z WHERE z.mail = :mail")
    , @NamedQuery(name = "Zakaznik.findByTelCislo", query = "SELECT z FROM Zakaznik z WHERE z.telCislo = :telCislo")
    , @NamedQuery(name = "Zakaznik.findByJmeno", query = "SELECT z FROM Zakaznik z WHERE z.jmeno = :jmeno")
    , @NamedQuery(name = "Zakaznik.findByPrijmeni", query = "SELECT z FROM Zakaznik z WHERE z.prijmeni = :prijmeni")})
public class Zakaznik implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ZAKAZNIK_ID")
    private BigDecimal zakaznikId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "MAIL")
    private String mail;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TEL_CISLO")
    private BigInteger telCislo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "JMENO")
    private String jmeno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PRIJMENI")
    private String prijmeni;

    public Zakaznik() {
    }

    public Zakaznik(BigDecimal zakaznikId) {
        this.zakaznikId = zakaznikId;
    }

    public Zakaznik(BigDecimal zakaznikId, String mail, BigInteger telCislo, String jmeno, String prijmeni) {
        this.zakaznikId = zakaznikId;
        this.mail = mail;
        this.telCislo = telCislo;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
    }

    public BigDecimal getZakaznikId() {
        return zakaznikId;
    }

    public void setZakaznikId(BigDecimal zakaznikId) {
        this.zakaznikId = zakaznikId;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public BigInteger getTelCislo() {
        return telCislo;
    }

    public void setTelCislo(BigInteger telCislo) {
        this.telCislo = telCislo;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zakaznikId != null ? zakaznikId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zakaznik)) {
            return false;
        }
        Zakaznik other = (Zakaznik) object;
        if ((this.zakaznikId == null && other.zakaznikId != null) || (this.zakaznikId != null && !this.zakaznikId.equals(other.zakaznikId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.cz.cvut.fit.bitjv.cv10.jenc.semestralka_01.Zakaznik[ zakaznikId=" + zakaznikId + " ]";
    }
    
}
