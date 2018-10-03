/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bitjv.semsetralka.jenc.controller;

import eu.cz.cvut.fit.bitjv.semsetralka.jenc.entity.Prispevek;
import eu.cz.cvut.fit.bitjv.semsetralka.jenc.entity.Uzivatel;
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
public class UzivatelCntroller {

    @PersistenceContext
    private EntityManager em;

    public void create(Uzivatel entity) {
       this.em.merge(entity);
    }
    
    public void remove( Long id) {
        Uzivatel u = this.find(id);
        if(u != null)
            this.em.remove(u);
    }

    public Uzivatel find(Long id) {
        return this.em.find(Uzivatel.class, id);
    }

    public Collection<Uzivatel> findAll() {
          return this.em.createNativeQuery("SELECT * FROM UZIVATEL", Uzivatel.class).getResultList();
    }    
    
    
      
    public Collection<Uzivatel> findConstrain(String s)
    {
     Query q =  this.em.createNativeQuery("SELECT * FROM UZIVATEL WHERE JMENO LIKE ? ", Uzivatel.class);
     q.setParameter(1,"%"+ s +"%");
     return q.getResultList();
    }
}
