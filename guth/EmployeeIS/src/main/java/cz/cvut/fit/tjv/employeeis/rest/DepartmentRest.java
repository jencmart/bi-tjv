package cz.cvut.fit.tjv.employeeis.rest;

import cz.cvut.fit.tjv.employeeis.db_controller.DepartmentController;
import cz.cvut.fit.tjv.employeeis.dto.DepartmentDTO;
import cz.cvut.fit.tjv.employeeis.entity.Department;
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
 * RESTful zdroj Department.
 * @author guthondr
 */

@Stateless
@Path("dept")
public class DepartmentRest {

    @EJB
    private DepartmentController controller;

    @DELETE
    @Path("/{site}-{title}")
    public void delete(@PathParam("title") String title, @PathParam("site") String site) {
        controller.delete(title, site);
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public void createOrUpdate(DepartmentDTO d) {
        controller.createOrUpdate(DepartmentEntityDtoConverter.dtoToEntity(d));
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{site}-{title}")
    public DepartmentDTO retrieveById(@PathParam("title") String title, @PathParam("site") String site) {
        return DepartmentEntityDtoConverter.entityToDto(controller.selectById(title, site));
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<DepartmentDTO> retrieveAll() {
        Collection<DepartmentDTO> result = new ArrayList<>();
        
        for (Department d : controller.selectAll()) {
            result.add(DepartmentEntityDtoConverter.entityToDto(d));
        }
        
        return result;
    }
}
