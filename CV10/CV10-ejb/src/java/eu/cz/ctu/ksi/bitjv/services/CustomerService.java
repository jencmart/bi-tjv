/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.ctu.ksi.bitjv.services;

import eu.cz.ctu.ksi.bitjv.entities.customer.Customer;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Remote;

/**
 *
 * @author pavlijo5
 */
//@Remote
public interface CustomerService {
    public List<Customer> getCustomers();
}
