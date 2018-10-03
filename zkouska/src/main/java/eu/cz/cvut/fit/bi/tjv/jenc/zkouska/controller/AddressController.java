/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bi.tjv.jenc.zkouska.controller;

import eu.cz.cvut.fit.bi.tjv.jenc.zkouska.entity.Address;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
public class AddressController {

    @PersistenceContext
    private EntityManager em;

    public void create(Address entity) {
        this.em.merge(entity);
    }

    public void remove(Long id) {
        Address p = this.find(id);
        if(p != null)
            this.em.remove(p);
    }

    public Address find(Long id) {
        return this.em.find(Address.class, id);
    }

    public Collection<Address> findAll() {
        return this.em.createNativeQuery("SELECT * FROM ADDRESS", Address.class).getResultList();
    }
    
    public Collection<Address> findByTown(String s)
    {
     Query q =  this.em.createNativeQuery("SELECT * FROM ADDRESS WHERE TOWN LIKE ? ", Address.class);
     q.setParameter(1,"%"+ s +"%");
     return q.getResultList();
    }
}
