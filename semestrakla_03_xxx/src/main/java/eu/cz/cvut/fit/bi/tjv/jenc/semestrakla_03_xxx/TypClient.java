/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bi.tjv.jenc.semestrakla_03_xxx;

import eu.cz.cvut.fit.bitjv.semsetralka.jenc.entity.Typ;
import java.util.ArrayList;
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
class TypClient {
   private Client client = ClientBuilder.newClient();
    private WebTarget clientTarget = client.target("http://localhost:8080/semestralka_03/endp/typ");
    
    public void remove(Long id) {
        clientTarget.path("" + id).request().delete();
    }
    
        public void createOrUpdate(Typ typ) {
        clientTarget.request().post(Entity.entity(typ, MediaType.APPLICATION_JSON));
    }
        
    public Collection<Typ> selectAll() {
        Typ[] typy = clientTarget.request(MediaType.APPLICATION_XML).get(Typ[].class);
        List<Typ> l = new ArrayList();
        List<Typ> result = Arrays.asList(typy);
        return result;
    } 
    
    public Collection<Typ> selectConstrain(String s) {
        Typ[] typy = clientTarget.path("findC/" + s).request(MediaType.APPLICATION_JSON).get(Typ[].class);
        List<Typ> l = new ArrayList();
        List<Typ> result = Arrays.asList(typy);
        return result;
    } 
    
    public Typ select(long id)
    {
        return clientTarget.path("" + id).request(MediaType.APPLICATION_JSON).get(Typ.class);
    }
       
}
