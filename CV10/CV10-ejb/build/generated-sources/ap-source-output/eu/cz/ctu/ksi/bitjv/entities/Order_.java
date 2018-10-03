package eu.cz.ctu.ksi.bitjv.entities;

import eu.cz.ctu.ksi.bitjv.entities.Pizza;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-20T16:56:05")
@StaticMetamodel(Order.class)
public class Order_ { 

    public static volatile SingularAttribute<Order, Integer> odederedNumber;
    public static volatile SingularAttribute<Order, String> address;
    public static volatile SingularAttribute<Order, Pizza> pizza;
    public static volatile SingularAttribute<Order, Integer> id;
    public static volatile SingularAttribute<Order, String> customerName;

}