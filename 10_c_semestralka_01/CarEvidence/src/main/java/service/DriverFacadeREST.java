/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import eu.cz.cvut.fit.bietjv.carevidence.entities.Driver;
import eu.cz.cvut.fit.bietjv.carevidence.entities.Drivers;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author pavlijo5
 */
@Stateless
@Path("eu.cz.cvut.fit.bietjv.carevidence.entities.driver")
public class DriverFacadeREST extends AbstractFacade<Driver> {

    @PersistenceContext(unitName = "eu.cz.cvut.fit.bietjv_CarEvidence_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public DriverFacadeREST() {
        super(Driver.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Driver entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Long id, Driver entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Driver find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Drivers findAllDrivers() {
    Drivers ds = new Drivers();
    ds.setDrivers(super.findAll());
        return ds;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Drivers findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
      Drivers ds = new Drivers();
      ds.setDrivers(super.findRange(new int[]{from, to}));
      return ds;
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
