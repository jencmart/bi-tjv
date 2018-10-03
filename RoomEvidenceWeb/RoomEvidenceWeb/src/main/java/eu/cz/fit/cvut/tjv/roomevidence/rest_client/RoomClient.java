/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.fit.cvut.tjv.roomevidence.rest_client;

import eu.cz.fit.cvut.tjv.RoomEvidence.entity.Room;
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
public class RoomClient {
    public static final String WEBSERVICE_URL = "http://localhost:8080/RoomEvidence/ep/room";
    private Client client = ClientBuilder.newClient();
    private WebTarget clientTarget = client.target(WEBSERVICE_URL);
    
    public void remove(long id) {
        clientTarget.path("" + id).request().delete();
    }
    
    public void createOrUpdate(Room room) {        
        clientTarget.request().put(Entity.entity(room, MediaType.APPLICATION_JSON));
    }
        
    public Collection<Room> selectAll() {
        Room[] rooms = clientTarget.request(MediaType.APPLICATION_JSON).get(Room[].class);       
        List<Room> result = Arrays.asList(rooms);
        return result;
    }
    
    public Collection<Room> selectAll(String address) {
        Room[] buildings = clientTarget.request(MediaType.APPLICATION_JSON).get(Room[].class);
        List<Room> b = Arrays.asList(buildings);
        List<Room> result = new ArrayList<>();
        for (Room r: b) {
            if (r.getNumber().compareTo(address) == 0) {
                result.add(r);
            }
        }
        return result;
    }
    
    public Room select(long id) {
        return clientTarget.path("" + id).request(MediaType.APPLICATION_JSON).get(Room.class);
    }
}
