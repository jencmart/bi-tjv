package cz.cvut.fit.tjv.employeeis.ws_endpoint;

import cz.cvut.fit.tjv.employeeis.rest.DepartmentRest;
import cz.cvut.fit.tjv.employeeis.rest.EmployeeREST;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Definice (konfigurace) endpoint RESTful webove sluzby - definuje cast URL.
 * @author guthondr
 */

@ApplicationPath("emps")
public class EmployeesRestEndpoint extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> result = new HashSet<>();
        
        result.add(EmployeeREST.class);
        result.add(DepartmentRest.class);
        
        return result;
    }
    
}
