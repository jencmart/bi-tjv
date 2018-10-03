/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.controller;

import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity.Producer;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    
    public void remove( Long id) {
        Producer u = this.find(id);
        if(u != null)
            this.em.remove(u);
    }

    public Producer find(Long id) {
        return this.em.find(Producer.class, id);
    }

    public Collection<Producer> findAll() {
          return this.em.createNativeQuery("SELECT * FROM PRODUCER", Producer.class).getResultList();
    }    
    public Collection<Producer> findConstrain(String s)
    {
     Query q =  this.em.createNativeQuery("SELECT * FROM PRODUCER WHERE JMENO LIKE ? ", Producer.class);
     q.setParameter(1,"%"+ s +"%");
     return q.getResultList();
    }
}
