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
@Table(name = "POLOZKA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Polozka.findAll", query = "SELECT p FROM Polozka p")
    , @NamedQuery(name = "Polozka.findByPolozkaId", query = "SELECT p FROM Polozka p WHERE p.polozkaId = :polozkaId")
    , @NamedQuery(name = "Polozka.findByNazev", query = "SELECT p FROM Polozka p WHERE p.nazev = :nazev")
    , @NamedQuery(name = "Polozka.findByHmotnost", query = "SELECT p FROM Polozka p WHERE p.hmotnost = :hmotnost")
    , @NamedQuery(name = "Polozka.findByCena", query = "SELECT p FROM Polozka p WHERE p.cena = :cena")})
public class Polozka implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "POLOZKA_ID")
    private BigDecimal polozkaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "NAZEV")
    private String nazev;
    @Column(name = "HMOTNOST")
    private BigInteger hmotnost;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CENA")
    private BigInteger cena;

    public Polozka() {
    }

    public Polozka(BigDecimal polozkaId) {
        this.polozkaId = polozkaId;
    }

    public Polozka(BigDecimal polozkaId, String nazev, BigInteger cena) {
        this.polozkaId = polozkaId;
        this.nazev = nazev;
        this.cena = cena;
    }

    public BigDecimal getPolozkaId() {
        return polozkaId;
    }

    public void setPolozkaId(BigDecimal polozkaId) {
        this.polozkaId = polozkaId;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public BigInteger getHmotnost() {
        return hmotnost;
    }

    public void setHmotnost(BigInteger hmotnost) {
        this.hmotnost = hmotnost;
    }

    public BigInteger getCena() {
        return cena;
    }

    public void setCena(BigInteger cena) {
        this.cena = cena;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (polozkaId != null ? polozkaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Polozka)) {
            return false;
        }
        Polozka other = (Polozka) object;
        if ((this.polozkaId == null && other.polozkaId != null) || (this.polozkaId != null && !this.polozkaId.equals(other.polozkaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.cz.cvut.fit.bitjv.cv10.jenc.semestralka_01.Polozka[ polozkaId=" + polozkaId + " ]";
    }
    
}
