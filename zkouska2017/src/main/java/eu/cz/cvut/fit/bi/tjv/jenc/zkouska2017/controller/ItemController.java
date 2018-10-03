/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.controller;

import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity.Item;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jencmart
 */
@Stateless
public class ItemController  {
    
    @PersistenceContext
    private EntityManager em;

    
    public void create(Item entity) {
        this.em.merge(entity);
    }

    public void remove( BigDecimal id) {
        Item t = this.find(id);
        if(t != null)
            this.em.remove(t);     
    }
 
    public Item find(BigDecimal id) {
        return this.em.find(Item.class, id);
    }

    public Collection<Item> findAll() {
        System.out.println("BBBBBBBB");
        return this.em.createNativeQuery("SELECT * FROM ITEM", Item.class).getResultList();
    }
       
    public Collection<Item> findByName(String s){
     Query q =  this.em.createNativeQuery("SELECT * FROM ITEM WHERE JMENO LIKE ? ", Item.class);
     q.setParameter(1,"%"+ s +"%");
     return q.getResultList();
    }
   
    protected EntityManager getEntityManager() {
        return em;
    } 
}
