/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.fit.cvut.tjv.roomevidence.rest_client;

import eu.cz.fit.cvut.tjv.RoomEvidence.entity.Building;
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
 * @author catherine
 */
public class BuildingClient {
    public static final String WEBSERVICE_URL = "http://localhost:8080/RoomEvidence/ep/building";
    private Client client = ClientBuilder.newClient();
    private WebTarget clientTarget = client.target(WEBSERVICE_URL);
    
    public void remove(long id) {
        clientTarget.path("" + id).request().delete();
    }
    
        public void createOrUpdate(Building building) {
        clientTarget.request().put(Entity.entity(building, MediaType.APPLICATION_JSON));
    }
        
    public Collection<Building> selectAll() {
        Building[] buildings = clientTarget.request(MediaType.APPLICATION_JSON).get(Building[].class);
        List<Building> result = Arrays.asList(buildings);
        return result;
    }
    
    public Collection<Building> selectAll(String address) {
        Building[] buildings = clientTarget.request(MediaType.APPLICATION_JSON).get(Building[].class);
        List<Building> b = Arrays.asList(buildings);
        List<Building> result = new ArrayList<>();
        for (Building r: b) {
            if (r.getAddress().compareTo(address) == 0) {
                result.add(r);
            }
        }
        return result;
    }
    
    public Building select(long id) {
        return clientTarget.path("" + id).request(MediaType.APPLICATION_JSON).get(Building.class);
    }
}
