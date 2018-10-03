/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.ctu.ksi.bitjv.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author pavlijo5
 */
@Entity
public class PizzaMenu implements Serializable {

    @Id
    private List<Pizza> pizzaMenu = new ArrayList();

    public PizzaMenu() {
    }

    public void setPizzaMenu() {
        if(pizzaMenu.isEmpty()) {
        pizzaMenu.add(0,new Pizza("Margarita", 100,0));
        pizzaMenu.add(1,new Pizza("Salami", 120,1));
        pizzaMenu.add(2,new Pizza("Tuna", 150,2));
        pizzaMenu.add(3,new Pizza("Quatro formagi", 125,3));
        }

    }

    public List<Pizza> getPizzaMenu() {
        return pizzaMenu;
    }

    public void setPizzaMenu(List<Pizza> pizzaMenu) {
        this.pizzaMenu = pizzaMenu;
    }

}
