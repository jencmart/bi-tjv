/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bitjv.semsetralka.jenc.services;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author jencmart
 */
@ApplicationPath("endp")
public class endpoint extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> result = new HashSet<>();
        
        result.add(PrispevekFacadeREST.class);
        result.add(TypFacadeREST.class);      
        result.add(UzivatelFacadeREST.class);
        
        return result;
    }
}
