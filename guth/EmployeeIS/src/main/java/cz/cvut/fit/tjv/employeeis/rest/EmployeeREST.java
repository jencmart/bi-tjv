package cz.cvut.fit.tjv.employeeis.rest;

import cz.cvut.fit.tjv.employeeis.db_controller.EmployeeController;
import cz.cvut.fit.tjv.employeeis.dto.EmployeeDTO;
import cz.cvut.fit.tjv.employeeis.entity.Employee;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * RESTful zdroj pro entitu Employee.
 * @author guthondr
 */

@Stateless
@Path("employee")
public class EmployeeREST {
    @EJB
    private EmployeeController controller;
    
    @DELETE
    @Path("/{eid}")
    public void delete(@PathParam("eid") long id) {
        controller.delete(id);
    }
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void createOrUpdate(EmployeeDTO e) {
        controller.createOrUpdate(EmployeeEntityDtoConverter.dtoToEntity(e));
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Path("/{eid}")
    public EmployeeDTO retrieveById(@PathParam("eid") long id) {
        return EmployeeEntityDtoConverter.entityToDto(controller.selectById(id));
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection<EmployeeDTO> retrieveAll() {
        Collection<EmployeeDTO> result = new ArrayList<>();
        
        for (Employee e : controller.selectAll()) {
            result.add(EmployeeEntityDtoConverter.entityToDto(e));
        }
        
        return result;
    }
}
