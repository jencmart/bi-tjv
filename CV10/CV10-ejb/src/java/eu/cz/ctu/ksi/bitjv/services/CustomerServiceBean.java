/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.ctu.ksi.bitjv.services;

import eu.cz.ctu.ksi.bitjv.entities.customer.Customer;
import eu.cz.ctu.ksi.bitjv.entities.model.CustomerFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author pavlijo5
 */
@Stateless
//@LocalBean -- rem this annotation
public class CustomerServiceBean implements CustomerService{
   
    @PersistenceContext(unitName = "CV10-ejbPU" )
    private EntityManager em;
    
    @Override
    public List<Customer> getCustomers() {
    CustomerFacade cf = new CustomerFacade(Customer.class);
    cf.setEntityManager(em);
    return cf.findAll();
    }

    
}
