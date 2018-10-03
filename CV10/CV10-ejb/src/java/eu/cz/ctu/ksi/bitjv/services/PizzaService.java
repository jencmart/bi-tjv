/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.ctu.ksi.bitjv.services;

import eu.cz.ctu.ksi.bitjv.entities.Order;
import eu.cz.ctu.ksi.bitjv.entities.Pizza;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author pavlijo5
 */
@Local
public interface PizzaService {
    
    public List<Pizza> getPizzaList();
    public Pizza getPizzaByName(String name);
    public void addPizza(Pizza pizza);
    public void updatePizza(Pizza pizza);
    public List<Order> getOrderedList();
    
}
