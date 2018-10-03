/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bi.tjv.jenc.zkouska_client;

import eu.cz.cvut.fit.bi.tjv.jenc.zkouska.entity.Zookeeper;
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
public class ZookeeperClient {
    
    private Client client = ClientBuilder.newClient();
    private WebTarget clientTarget = client.target("http://localhost:8080/zkouska/webresources/zookeeper");
    
    public void remove(Long id) {
        clientTarget.path("" + id).request().delete();
    }
    
        public void createOrUpdate(Zookeeper typ) {
        clientTarget.request().post(Entity.entity(typ, MediaType.APPLICATION_XML));
    }
        
    public Collection<Zookeeper> findAll() {
        Zookeeper[] prisp = clientTarget.request(MediaType.APPLICATION_XML).get(Zookeeper[].class);
        List<Zookeeper> result = Arrays.asList(prisp);
        return result;
    }
    
    public Collection<Zookeeper> findByName(String s) {
         Zookeeper[] prisp = clientTarget.path("name/" + s).request(MediaType.APPLICATION_XML).get(Zookeeper[].class);
        List<Zookeeper> result = Arrays.asList(prisp);
        return result;
    } 
    
    public Zookeeper select(long id){
        return clientTarget.path("" + id).request(MediaType.APPLICATION_XML).get(Zookeeper.class);
    }
}
