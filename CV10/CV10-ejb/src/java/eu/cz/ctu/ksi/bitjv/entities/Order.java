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
public class Order implements Serializable {
  @Id 
  private Integer id;
  private String  customerName;
  private String  address;
  private Pizza  pizza;
  private Integer  odederedNumber;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Integer getOdederedNumber() {
        return odederedNumber;
    }

    public void setOdederedNumber(Integer odederedNumber) {
        this.odederedNumber = odederedNumber;
    }


    
}
