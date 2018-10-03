/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.ctu.ksi.bitjv.service;

import eu.cz.ctu.ksi.bitjv.entities.customer.Customer;
import eu.cz.ctu.ksi.bitjv.services.CustomerService;
import eu.cz.ctu.ksi.bitjv.services.CustomerServiceBean;
import eu.cz.ctu.ksi.bitjv.services.PizzaService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author pavlijo5
 */
@Path("customer")
public class CustomerResource {
    
    public CustomerResource(){}
    @EJB
    private CustomerService service; 
    
    private  CustomerService getService() {
        if (service == null) {
            try {
                InitialContext ic = new InitialContext();
                service = (CustomerService) ic.lookup("java:global/CV10/CV10-ejb/CustomerServiceBean");
            } catch (NamingException ex) {
                Logger.getLogger(PizzaResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return service;
    }
    
    @GET
    @Path("/getCustomers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getCustomers() {
       return getService().getCustomers();
    }
    
}
