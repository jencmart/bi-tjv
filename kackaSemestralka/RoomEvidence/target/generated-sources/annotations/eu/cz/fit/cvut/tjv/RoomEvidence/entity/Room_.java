package eu.cz.fit.cvut.tjv.RoomEvidence.entity;

import eu.cz.fit.cvut.tjv.RoomEvidence.entity.Building;
import eu.cz.fit.cvut.tjv.RoomEvidence.entity.Person;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-01-03T09:39:41")
@StaticMetamodel(Room.class)
public class Room_ { 

    public static volatile SingularAttribute<Room, Person> owner;
    public static volatile SingularAttribute<Room, String> number;
    public static volatile SingularAttribute<Room, Long> id;
    public static volatile SingularAttribute<Room, Building> building;

}