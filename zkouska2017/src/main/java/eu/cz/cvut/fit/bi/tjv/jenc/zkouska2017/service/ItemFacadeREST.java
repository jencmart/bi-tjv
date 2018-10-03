/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.service;

import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.controller.AbstractController;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.controller.CustomerController;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity.Item;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity.Item;
import java.math.BigDecimal;
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
@Path("item")
public class ItemFacadeREST extends AbstractFacade<Item> {
    @EJB
    private CustomerController em;

    public ItemFacadeREST() {
           super(Item.class);
    }
   

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Item entity) {
        super.create(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") BigDecimal id) {
        super.remove(id);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Override
    public Item find(@PathParam("id") BigDecimal id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection<Item> findAll() {
        return super.findAll();
    }
    
    
    @GET
    @Override
    @Path("name/{s}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection<Item> findByName(@PathParam("s") String s) {
        return super.findByName(s);
    }

    @Override
    protected AbstractController getEntityManager() {
        return em;
    }
}
