/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.controller;

import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity.Customer;
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
public class CustomerController extends AbstractController<Customer>{

    @PersistenceContext
    private EntityManager em;

    
    public void create(Customer entity) {
        this.em.merge(entity);
    }

    
    public void remove(BigDecimal id) {
        Customer t = this.find(id);
        if(t != null)
            this.em.remove(t);     
    }
    
    public Customer find(BigDecimal id) {
        return this.em.find(Customer.class, id);
    }

    
    public Collection<Customer> findAll() {
        System.out.println("AAAAAAAAAAAAA");
        return this.em.createNativeQuery("SELECT * FROM CUSTOMER", Customer.class).getResultList();
    }

    
    public Collection<Customer> findByName(String s){
     Query q =  this.em.createNativeQuery("SELECT * FROM CUSTOMER WHERE JMENO LIKE ? ", Customer.class);
     q.setParameter(1,"%"+ s +"%");
     return q.getResultList();
    }
 
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
