/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bi.tjv.jenc.trenink.service;

import eu.cz.cvut.fit.bi.tjv.jenc.trenink.controller.CustomerController;
import eu.cz.cvut.fit.bi.tjv.jenc.trenink.entity.Customer;
import java.util.Collection;
import java.util.List;
import javax.ejb.EJB;
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
 * @author jencmart
 */
@Stateless
@Path("customer")
public class CustomerFacadeREST {

    @EJB
    private CustomerController controller;
    
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Customer entity) {
        controller.create(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        controller.remove(id);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Customer find(@PathParam("id") Long id) {
        return controller.find(id);
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection<Customer> findAll() {
        return controller.findAll();
    }
    
     @GET
    @Path("findName/{s}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection<Customer> findByName(@PathParam("s") String x, @PathParam("s") String s) {
          return controller.findByName(s);
    }
}
