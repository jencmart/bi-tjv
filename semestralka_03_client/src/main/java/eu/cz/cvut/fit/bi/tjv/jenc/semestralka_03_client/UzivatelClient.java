/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bi.tjv.jenc.semestralka_03_client;

import eu.cz.cvut.fit.bitjv.semsetralka.jenc.entity.Uzivatel;
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
public class UzivatelClient {
    
    private Client client = ClientBuilder.newClient();
    private WebTarget clientTarget = client.target("http://localhost:8080/semestralka_03/endp/uzivatel");
    
    public void remove(Long id) {
        clientTarget.path("" + id).request().delete();
    }
    
        public void createOrUpdate(Uzivatel typ) {
        clientTarget.request().post(Entity.entity(typ, MediaType.APPLICATION_JSON));
    }
        
    public Collection<Uzivatel> selectAll() {
        Uzivatel[] uziv = clientTarget.request(MediaType.APPLICATION_JSON).get(Uzivatel[].class);
        List<Uzivatel> result = Arrays.asList(uziv);
        return result;
    }
    
    
    public Collection<Uzivatel> selectConstrain(String s) {
        Uzivatel[] uziv = clientTarget.path("findC/" + s).request(MediaType.APPLICATION_JSON).get(Uzivatel[].class);
        List<Uzivatel> result = Arrays.asList(uziv);
        return result;
    } 
    
    
    public Uzivatel select(long id)
    {
        return clientTarget.path("" + id).request(MediaType.APPLICATION_JSON).get(Uzivatel.class);
    }
}
