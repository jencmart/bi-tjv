/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.fit.cvut.tjv.RoomEvidence.bean;

import eu.cz.fit.cvut.tjv.RoomEvidence.entity.Person;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author catherine
 */
@Stateless
public class PersonController {

    @PersistenceContext
    private EntityManager entityManager;
    
    public Person select(final Long personId) {
        return this.entityManager.find(Person.class, personId);
    }
    
    public void createOrUpdate(Person person) {
        this.entityManager.merge(person);
    }
    
    public Collection<Person> selectAll () {        
        return this.entityManager
                .createNativeQuery("SELECT * FROM PERSON", Person.class)
                .getResultList();
    }
    
    public void remove(final long personId) {
        Person person = this.select(personId);
        if (person != null)
            this.entityManager.remove(person);
    }
}
