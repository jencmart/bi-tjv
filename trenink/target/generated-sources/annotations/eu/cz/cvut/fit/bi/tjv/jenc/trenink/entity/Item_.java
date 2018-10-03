package eu.cz.cvut.fit.bi.tjv.jenc.trenink.entity;

import eu.cz.cvut.fit.bi.tjv.jenc.trenink.entity.ItemCustomer;
import eu.cz.cvut.fit.bi.tjv.jenc.trenink.entity.Producer;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-09T01:40:34")
@StaticMetamodel(Item.class)
public class Item_ { 

    public static volatile SingularAttribute<Item, Long> itemId;
    public static volatile CollectionAttribute<Item, ItemCustomer> itemCustomerCollection;
    public static volatile SingularAttribute<Item, String> popis;
    public static volatile SingularAttribute<Item, Producer> producerId;

}