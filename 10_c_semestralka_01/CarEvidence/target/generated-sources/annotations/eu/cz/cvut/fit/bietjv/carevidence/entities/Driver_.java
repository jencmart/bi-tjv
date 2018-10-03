package eu.cz.cvut.fit.bietjv.carevidence.entities;

import eu.cz.cvut.fit.bietjv.carevidence.entities.Car;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-29T17:17:37")
@StaticMetamodel(Driver.class)
public class Driver_ { 

    public static volatile ListAttribute<Driver, Car> car;
    public static volatile SingularAttribute<Driver, String> surname;
    public static volatile SingularAttribute<Driver, String> name;
    public static volatile SingularAttribute<Driver, Long> id;

}