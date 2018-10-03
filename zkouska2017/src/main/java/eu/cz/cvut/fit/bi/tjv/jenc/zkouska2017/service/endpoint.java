/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.cvut.fit.bi.tjv.jenc.zkouska2017.service;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author jencmart
 */
@ApplicationPath("endp")
public class endpoint extends Application  {
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> result = new HashSet<>();
        
        result.add(CustomerFacadeREST.class);
        result.add(ItemFacadeREST.class);      
        result.add(ItemCustomerFacadeREST.class);
        result.add(ProducerFacadeREST.class);
        
        return result;
    }
}
