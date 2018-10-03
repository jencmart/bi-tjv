/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.fit.cvut.tjv.RoomEvidence.bean;

import eu.cz.fit.cvut.tjv.RoomEvidence.entity.Building;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author catherine
 */
@Stateless
public class BuildingController {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public Building select(final Long buildingId) {
        return this.entityManager.find(Building.class, buildingId);
    }
    
    public void createOrUpdate(Building building) {
        this.entityManager.merge(building);
    }
    
    public Collection<Building> selectAll () {      
        return this.entityManager
                .createNativeQuery("SELECT * FROM APP.BUILDING", Building.class)
                .getResultList();
    }
    
    public void remove(final long buildingId) {
        Building building = this.select(buildingId);
        if (building != null)
            this.entityManager.remove(building);
    }
}
