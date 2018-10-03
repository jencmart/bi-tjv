package eu.cz.cvut.fit.bi.tjv.jenc.trenink.entity;

import eu.cz.cvut.fit.bi.tjv.jenc.trenink.entity.Item;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-09T01:40:34")
@StaticMetamodel(Producer.class)
public class Producer_ { 

    public static volatile SingularAttribute<Producer, String> nazev;
    public static volatile CollectionAttribute<Producer, Item> itemCollection;
    public static volatile SingularAttribute<Producer, Long> producerId;

}