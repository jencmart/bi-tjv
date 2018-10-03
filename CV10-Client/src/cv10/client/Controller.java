/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cv10.client;

import cv10.client.rest.cust.CustomerClient;
import eu.cz.ctu.ksi.bitjv.entities.customer.Customer;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author pavlijo5
 */
public class Controller implements ActionListener{
    private CustomerViewMVC view;
    CustomerClient model;
    
    public Controller(CustomerViewMVC view, CustomerClient model) {
        this.view=view;
        this.model=model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
   //   view.getjTextArea_detail().setText(model.getCustomers((new GenericType<List<Customer>>).class).toString());
    
    }
    
}
