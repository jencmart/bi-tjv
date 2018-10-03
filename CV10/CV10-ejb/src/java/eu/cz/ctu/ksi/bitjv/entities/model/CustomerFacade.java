/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.ctu.ksi.bitjv.entities.model;

import eu.cz.ctu.ksi.bitjv.entities.customer.Customer;
import javax.persistence.EntityManager;

/**
 *
 * @author pavlijo5
 */
public class CustomerFacade extends AbstractFacade<Customer>{

    public CustomerFacade(Class<Customer> entityClass) {
        super(entityClass);
    }

   
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
    return em;
    }

    @Override
    public void setEntityManager(EntityManager em) {
    this.em=em;
    }
    
}
