/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bi.tjv.jenc.trenink.controller;

import eu.cz.cvut.fit.bi.tjv.jenc.trenink.entity.Producer;
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
public class ProducerController {

      @PersistenceContext
    private EntityManager em;

    public void create(Producer entity) {
        this.em.merge(entity);
    }

    public void remove(Long id) {
        Producer p = this.find(id);
        if(p != null)
            this.em.remove(p);
    }

    public Producer find(Long id) {
        return this.em.find(Producer.class, id);
    }

    public Collection<Producer> findAll() {
        return this.em.createNativeQuery("SELECT * FROM PRODUCER", Producer.class).getResultList();
    }
    
    
    public Collection<Producer> findByName(String s)
    {
     Query q =  this.em.createNativeQuery("SELECT * FROM PRODUCER WHERE NAZEV LIKE ? ", Producer.class);
     q.setParameter(1,"%"+ s +"%");
     return q.getResultList();
    }
    
}
