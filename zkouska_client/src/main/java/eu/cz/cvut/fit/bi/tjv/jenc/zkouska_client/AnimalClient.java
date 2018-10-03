/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bi.tjv.jenc.zkouska_client;

import eu.cz.cvut.fit.bi.tjv.jenc.zkouska.entity.Animal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author jencmart
 */
public class AnimalClient {
        private Client client = ClientBuilder.newClient();
    private WebTarget clientTarget = client.target("http://localhost:8080/zkouska/webresources/animal");
    
    public void remove(Long id) {
        clientTarget.path("" + id).request().delete();
    }
    
        public void createOrUpdate(Animal typ) {
        clientTarget.request().post(Entity.entity(typ, MediaType.APPLICATION_XML));
    }
        
    public Collection<Animal> findAll() {
         Animal[] prisp = clientTarget.request(MediaType.APPLICATION_XML).get(Animal[].class);
        List<Animal> result = Arrays.asList(prisp);
        return result;
        
    }
    
    public Collection<Animal> findByName(String s) {
         Animal[] prisp = clientTarget.path("name/" + s).request(MediaType.APPLICATION_XML).get(Animal[].class);
        List<Animal> result = Arrays.asList(prisp);
        return result;
    } 
    
    public Animal select(long id){
        return clientTarget.path("" + id).request(MediaType.APPLICATION_XML).get(Animal.class);
    }
    
}


