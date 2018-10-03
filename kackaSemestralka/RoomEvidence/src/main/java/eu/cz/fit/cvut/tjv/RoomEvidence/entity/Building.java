/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.fit.cvut.tjv.RoomEvidence.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author catherine
 */
@Entity(name = "BUILDING")
@XmlRootElement
@Table(name = "BUILDING")
public class Building implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Basic(optional = true)
    private String address;
    /*
    @OneToMany(mappedBy = "building")
    @Basic(optional = true)
    private List<Room> rooms;
    */
    public Building() {}
   /* 
    public Building(String address, List<Room> rooms) {
        this.address = address;
        this.rooms = rooms;
    }
    
    public void addRoom(Room room) {
        this.rooms.add(room);
        if (room.getBuilding() != this)
            room.setBuilding(this);
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @XmlTransient
    public List<Room> getRooms() {
        return rooms;
    }
*/
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
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
        if (!(object instanceof Building)) {
            return false;
        }
        Building other = (Building) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id.toString();
    }
    
}
