package eu.cz.ctu.ksi.bitjv.entities.customer;

import eu.cz.ctu.ksi.bitjv.entities.customer.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-12-20T16:56:05")
@StaticMetamodel(Manufacturer.class)
public class Manufacturer_ { 

    public static volatile SingularAttribute<Manufacturer, String> zip;
    public static volatile CollectionAttribute<Manufacturer, Product> productCollection;
    public static volatile SingularAttribute<Manufacturer, String> city;
    public static volatile SingularAttribute<Manufacturer, String> phone;
    public static volatile SingularAttribute<Manufacturer, Integer> manufacturerId;
    public static volatile SingularAttribute<Manufacturer, String> name;
    public static volatile SingularAttribute<Manufacturer, String> addressline2;
    public static volatile SingularAttribute<Manufacturer, String> addressline1;
    public static volatile SingularAttribute<Manufacturer, String> state;
    public static volatile SingularAttribute<Manufacturer, String> fax;
    public static volatile SingularAttribute<Manufacturer, String> rep;
    public static volatile SingularAttribute<Manufacturer, String> email;

}