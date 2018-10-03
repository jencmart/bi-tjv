package eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity;

import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity.Customer;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity.Item;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-06T22:08:47")
@StaticMetamodel(ItemCustomer.class)
public class ItemCustomer_ { 

    public static volatile SingularAttribute<ItemCustomer, Item> itemId;
    public static volatile SingularAttribute<ItemCustomer, BigDecimal> itemCustomerId;
    public static volatile SingularAttribute<ItemCustomer, Customer> customerId;
    public static volatile SingularAttribute<ItemCustomer, String> jmeno;

}