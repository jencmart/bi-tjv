/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.service;

import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.controller.AbstractController;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.controller.CustomerController;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity.Producer;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity.Producer;
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
@Path("producer")
public class ProducerFacadeREST extends AbstractFacade<Producer> {


    @EJB
    private CustomerController em;

    public ProducerFacadeREST() {
           super(Producer.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Producer entity) {
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
    public Producer find(@PathParam("id") BigDecimal id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection<Producer> findAll() {
        return super.findAll();
    }
    
    
    @GET
    @Override
    @Path("name/{s}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection<Producer> findByName(@PathParam("s") String s) {
        return super.findByName(s);
    }

    @Override
    protected AbstractController getEntityManager() {
        return em;
    }
}
