package eu.cz.cvut.fit.bitjv.semsetralka.jenc.entity;

import eu.cz.cvut.fit.bitjv.semsetralka.jenc.entity.Typ;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-09T13:42:43")
@StaticMetamodel(Uzivatel.class)
public class Uzivatel_ { 

    public static volatile SingularAttribute<Uzivatel, Long> uzivatelId;
    public static volatile SingularAttribute<Uzivatel, String> nickname;
    public static volatile SingularAttribute<Uzivatel, String> jmeno;
    public static volatile SingularAttribute<Uzivatel, Typ> typId;
    public static volatile SingularAttribute<Uzivatel, String> email;

}