package eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity;

import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity.ItemCustomer;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.entity.Producer;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-06T22:08:47")
@StaticMetamodel(Item.class)
public class Item_ { 

    public static volatile SingularAttribute<Item, BigDecimal> itemId;
    public static volatile CollectionAttribute<Item, ItemCustomer> itemCustomerCollection;
    public static volatile SingularAttribute<Item, String> popis;
    public static volatile SingularAttribute<Item, Producer> producerId;

}