/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Entity
@Table(name = "ITEM_CUSTOMER")
@XmlRootElement
@NamedQueries({
@NamedQuery(name = "ItemCustomer.findAll", query = "SELECT i FROM ItemCustomer i")})
public class ItemCustomer implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ITEM_CUSTOMER_ID")
    private BigDecimal itemCustomerId;
    @Size(max = 50)
    @Column(name = "JMENO")
    private String jmeno;
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID")
    @ManyToOne(optional = false)
    private Customer customerId;
    @JoinColumn(name = "ITEM_ID", referencedColumnName = "ITEM_ID")
    @ManyToOne(optional = false)
    private Item itemId;

    public ItemCustomer() {
    }

    public ItemCustomer(BigDecimal itemCustomerId) {
        this.itemCustomerId = itemCustomerId;
    }

    public BigDecimal getItemCustomerId() {
        return itemCustomerId;
    }

    public void setItemCustomerId(BigDecimal itemCustomerId) {
        this.itemCustomerId = itemCustomerId;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemCustomerId != null ? itemCustomerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemCustomer)) {
            return false;
        }
        ItemCustomer other = (ItemCustomer) object;
        if ((this.itemCustomerId == null && other.itemCustomerId != null) || (this.itemCustomerId != null && !this.itemCustomerId.equals(other.itemCustomerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity.ItemCustomer[ itemCustomerId=" + itemCustomerId + " ]";
    }
    
}
