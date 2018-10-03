/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.fit.cvut.tjv.RoomEvidence.services.rest;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author catherine
 */

@ApplicationPath("ep")
public class restEndpoint extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> result = new HashSet<>();
        
        result.add(BuildingREST.class);
        result.add(PersonREST.class);      
        result.add(RoomREST.class);
        
        return result;
    }
    
}
