/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.fit.cvut.tjv.roomevidence.rest_client;

import eu.cz.fit.cvut.tjv.RoomEvidence.entity.Person;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

/**
 *
 * @author catherine
 */
public class PersonClient {
    public static final String WEBSERVICE_URL = "http://localhost:8080/RoomEvidence/ep/person";
    private Client client = ClientBuilder.newClient();
    private WebTarget clientTarget = client.target(WEBSERVICE_URL);
    
    public void remove(long id) {
        clientTarget.path("" + id).request().delete();
    }
    
        public void createOrUpdate(Person person) {
        clientTarget.request().put(Entity.entity(person, MediaType.APPLICATION_JSON));
    }
        
    public Collection<Person> selectAll() {
        Person[] people = clientTarget.request(MediaType.APPLICATION_JSON).get(Person[].class);
        List<Person> result = Arrays.asList(people);
        return result;
    }
    
    public Collection<Person> selectAll(String name) {
        Person[] buildings = clientTarget.request(MediaType.APPLICATION_JSON).get(Person[].class);
        List<Person> b = Arrays.asList(buildings);
        List<Person> result = new ArrayList<>();        
        for (Person r: b) {
            if (r.getName().compareTo(name) == 0) {
                result.add(r);
            }
        }
        return result;
    }
    
    public Person select(long id) {
        return clientTarget.path("" + id).request(MediaType.APPLICATION_JSON).get(Person.class);
    }
}
