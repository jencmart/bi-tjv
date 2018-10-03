/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.ctu.ksi.bitjv.services;

import eu.cz.ctu.ksi.bitjv.entities.Order;
import eu.cz.ctu.ksi.bitjv.entities.Pizza;
import eu.cz.ctu.ksi.bitjv.entities.PizzaMenu;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author pavlijo5
 */
@Stateless
public class PizzaServiceBean implements PizzaService {

    PizzaMenu menu = new PizzaMenu();
    Order order = new Order();

    @Override
    public List<Pizza> getPizzaList() {
        menu.setPizzaMenu();
        return menu.getPizzaMenu();
    }

    @Override
    public Pizza getPizzaByName(String name) {
        // now implements persistence 
        menu.setPizzaMenu();
        for (Pizza p : menu.getPizzaMenu()) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void addPizza(Pizza pizza) {
        menu.getPizzaMenu().add(pizza);
    }

    @Override
    public List<Order> getOrderedList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updatePizza(Pizza pizza) {
        menu.setPizzaMenu();
        for (int i =0;i<menu.getPizzaMenu().size();i++) {
             Pizza p = menu.getPizzaMenu().get(i);
            if (i == pizza.getId().intValue()) {
                 menu.getPizzaMenu().remove(i);
                 menu.getPizzaMenu().add(i,pizza );
                break;
            }
        }
        
    }

}
