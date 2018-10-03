package eu.cz.cvut.fit.bietjv.carevidence.entities;

import eu.cz.cvut.fit.bietjv.carevidence.entities.Driver;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-29T17:17:37")
@StaticMetamodel(Car.class)
public class Car_ { 

    public static volatile ListAttribute<Car, Driver> driver;
    public static volatile SingularAttribute<Car, String> name;
    public static volatile SingularAttribute<Car, Long> id;

}