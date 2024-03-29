/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.fit.cvut.tjv.RoomEvidence.services.rest;

import eu.cz.fit.cvut.tjv.RoomEvidence.bean.BuildingController;
import eu.cz.fit.cvut.tjv.RoomEvidence.entity.Building;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author catherine
 */
@Stateless
@Path("building")
public class BuildingREST {

    @EJB
    private BuildingController controller;
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void createOrUpdate(Building entity) {
        controller.createOrUpdate(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        controller.remove(id);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Building select(@PathParam("id") Long id) {
        return controller.select(id);
    }    
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})   
    public Collection<Building> selectAll() {
        return controller.selectAll();
    } 
}
