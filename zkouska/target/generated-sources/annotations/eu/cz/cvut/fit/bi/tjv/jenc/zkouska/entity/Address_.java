package eu.cz.cvut.fit.bi.tjv.jenc.zkouska.entity;

import eu.cz.cvut.fit.bi.tjv.jenc.zkouska.entity.Zookeeper;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-09T13:54:25")
@StaticMetamodel(Address.class)
public class Address_ { 

    public static volatile SingularAttribute<Address, String> town;
    public static volatile SingularAttribute<Address, String> street;
    public static volatile CollectionAttribute<Address, Zookeeper> zookeeperCollection;
    public static volatile SingularAttribute<Address, Long> addressId;

}