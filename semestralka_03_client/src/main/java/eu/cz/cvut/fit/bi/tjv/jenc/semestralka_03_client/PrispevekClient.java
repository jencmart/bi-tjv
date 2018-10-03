/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bi.tjv.jenc.semestralka_03_client;


import eu.cz.cvut.fit.bitjv.semsetralka.jenc.entity.Prispevek;

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
public class PrispevekClient {
        
    private Client client = ClientBuilder.newClient();
    private WebTarget clientTarget = client.target("http://localhost:8080/semestralka_03/endp/prispevek");
    
    public void remove(Long id) {
        clientTarget.path("" + id).request().delete();
    }
    
        public void createOrUpdate(Prispevek typ) {
        clientTarget.request().post(Entity.entity(typ, MediaType.APPLICATION_JSON));
    }
        
    public Collection<Prispevek> selectAll() {
        Prispevek[] prisp = clientTarget.request(MediaType.APPLICATION_JSON).get(Prispevek[].class);
        List<Prispevek> result = Arrays.asList(prisp);
        return result;
    }
    
    public Collection<Prispevek> selectConstrain(String s) {
         Prispevek[] prisp = clientTarget.path("findC/" + s).request(MediaType.APPLICATION_JSON).get(Prispevek[].class);
        List<Prispevek> result = Arrays.asList(prisp);
        return result;
    } 
    
    public Prispevek select(long id)
    {
        return clientTarget.path("" + id).request(MediaType.APPLICATION_JSON).get(Prispevek.class);
    }
}
