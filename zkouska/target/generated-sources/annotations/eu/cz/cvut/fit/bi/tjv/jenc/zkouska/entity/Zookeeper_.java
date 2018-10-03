package eu.cz.cvut.fit.bi.tjv.jenc.zkouska.entity;

import eu.cz.cvut.fit.bi.tjv.jenc.zkouska.entity.Address;
import eu.cz.cvut.fit.bi.tjv.jenc.zkouska.entity.Animal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-09T13:54:25")
@StaticMetamodel(Zookeeper.class)
public class Zookeeper_ { 

    public static volatile SingularAttribute<Zookeeper, Long> zookeeperId;
    public static volatile SingularAttribute<Zookeeper, String> surname;
    public static volatile SingularAttribute<Zookeeper, String> name;
    public static volatile CollectionAttribute<Zookeeper, Animal> animalCollection;
    public static volatile SingularAttribute<Zookeeper, Address> addressId;

}