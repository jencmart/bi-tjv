package eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity;

import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity.ItemCustomer;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-06T22:08:47")
@StaticMetamodel(Customer.class)
public class Customer_ { 

    public static volatile CollectionAttribute<Customer, ItemCustomer> itemCustomerCollection;
    public static volatile SingularAttribute<Customer, BigDecimal> customerId;
    public static volatile SingularAttribute<Customer, String> jmeno;

}