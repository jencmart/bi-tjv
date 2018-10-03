/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.ctu.ksi.bitjv.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author pavlijo5
 */
@Entity
// It's useful to have pizza as Entity (not necessary)
public class Pizza implements Serializable{
 @Id 
    private Integer id;
    private String name;
    private Integer price;
    
    // Default constructor, for sure, add it into each entity, which will be used for Rest Services. Why will be explained during the lecture
    public Pizza() {
        
    }
    // Attention, if you want to add another constructor than default, it's recommended to add default constructor into the code
    public Pizza(String name, Integer price, Integer id) {
        this.name=name;
        this.price=price;
        this.id=id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Pizza{" + "id=" + id + ", name=" + name + ", price=" + price + '}';
    }

   
    
    
    
}
