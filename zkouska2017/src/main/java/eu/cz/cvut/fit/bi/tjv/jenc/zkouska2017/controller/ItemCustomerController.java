/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.controller;

import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity.ItemCustomer;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jencmart
 */
@Stateless
public class ItemCustomerController {

   @PersistenceContext
    private EntityManager em;

    public void create(ItemCustomer entity) {
        this.em.merge(entity);
    }

    public void remove( Long id) {
     ItemCustomer t = this.find(id);
        if(t != null)
            this.em.remove(t);     
    }

    public ItemCustomer find(Long id) {
        return this.em.find(ItemCustomer.class, id);
    }

    public Collection<ItemCustomer> findAll() {
        System.out.println("CCCCCCCC");
         return this.em.createNativeQuery("SELECT * FROM ITEM_CUSTOMER", ItemCustomer.class).getResultList();
    }
    
}
