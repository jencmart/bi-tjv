/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bi.tjv.jenc.zkouska.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Entity
@Table(name = "ZOOKEEPER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zookeeper.findAll", query = "SELECT z FROM Zookeeper z")})
public class Zookeeper implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ZOOKEEPER_ID")
    private Long zookeeperId;
    @Size(max = 50)
    @Column(name = "NAME")
    private String name;
    @Size(max = 50)
    @Column(name = "SURNAME")
    private String surname;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zookeeperId")
    private Collection<Animal> animalCollection;
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ADDRESS_ID")
    @ManyToOne(optional = false)
    private Address addressId;

    public Zookeeper() {
    }

    public Zookeeper(Long zookeeperId) {
        this.zookeeperId = zookeeperId;
    }

    public Long getZookeeperId() {
        return zookeeperId;
    }

    public void setZookeeperId(Long zookeeperId) {
        this.zookeeperId = zookeeperId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @XmlTransient
    public Collection<Animal> getAnimalCollection() {
        return animalCollection;
    }

    public void setAnimalCollection(Collection<Animal> animalCollection) {
        this.animalCollection = animalCollection;
    }

    public Address getAddressId() {
        return addressId;
    }

    public void setAddressId(Address addressId) {
        this.addressId = addressId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (zookeeperId != null ? zookeeperId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zookeeper)) {
            return false;
        }
        Zookeeper other = (Zookeeper) object;
        if ((this.zookeeperId == null && other.zookeeperId != null) || (this.zookeeperId != null && !this.zookeeperId.equals(other.zookeeperId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.cz.cvut.fit.bi.tjv.jenc.zkouska.entity.Zookeeper[ zookeeperId=" + zookeeperId + " ]";
    }
    
}
