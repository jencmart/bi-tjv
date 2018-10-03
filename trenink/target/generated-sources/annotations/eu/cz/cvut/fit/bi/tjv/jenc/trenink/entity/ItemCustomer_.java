package eu.cz.cvut.fit.bi.tjv.jenc.trenink.entity;

import eu.cz.cvut.fit.bi.tjv.jenc.trenink.entity.Customer;
import eu.cz.cvut.fit.bi.tjv.jenc.trenink.entity.Item;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-09T01:40:34")
@StaticMetamodel(ItemCustomer.class)
public class ItemCustomer_ { 

    public static volatile SingularAttribute<ItemCustomer, Item> itemId;
    public static volatile SingularAttribute<ItemCustomer, Long> itemCustomerId;
    public static volatile SingularAttribute<ItemCustomer, Customer> customerId;
    public static volatile SingularAttribute<ItemCustomer, String> jmeno;

}