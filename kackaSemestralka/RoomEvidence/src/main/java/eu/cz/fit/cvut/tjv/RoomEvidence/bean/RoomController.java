/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.fit.cvut.tjv.RoomEvidence.bean;

import eu.cz.fit.cvut.tjv.RoomEvidence.entity.Room;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author catherine
 */
@Stateless
public class RoomController {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public Room select(final long roomId) {        
        return this.entityManager.find(Room.class, roomId);
    }
    
    public void createOrUpdate(Room room) {
        this.entityManager.merge(room);
    }
    
    public Collection<Room> selectAll () {
        return this.entityManager
                .createNativeQuery("SELECT * FROM ROOM", Room.class)
                .getResultList();
    }
    
    public void remove(final long roomId) {
        Room room = this.select(roomId);
        if (room != null)
            this.entityManager.remove(room);
    }
}
