/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.cz.ctu.ksi.bitjv.service;

import eu.cz.ctu.ksi.bitjv.entities.Pizza;
import eu.cz.ctu.ksi.bitjv.services.PizzaService;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * REST Web Service
 *
 * @author pavlijo5
 */
@Path("pizza")
public class PizzaResource {

    @Context
    private UriInfo context;
    @EJB
    private PizzaService service;

    /**
     * Creates a new instance of PizzaResource
     */
    public PizzaResource() {
    }

    private PizzaService getService() {
        if (service == null) {
            try {
                InitialContext ic = new InitialContext();
                service = (PizzaService) ic.lookup("java:global/CV10/CV10-ejb/PizzaServiceBean");
            } catch (NamingException ex) {
                Logger.getLogger(PizzaResource.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return service;
    }

    /**
     * Retrieves representation of an instance of
     * eu.cz.ctu.ksi.bitjv.service.PizzaResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("getList")
    @Produces(MediaType.TEXT_PLAIN)
    public String getList() {
        System.out.println("GetPizzaList");
        String response = "";
        for (Pizza p : getService().getPizzaList()) {
            response += p.getName() + " " + p.getPrice().toString() + " id = "+p.getId()+"\n";
        }
        return response;
    }

    @GET
    @Path("getByName/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Pizza getPizzaByName(@PathParam("name") String name) {
        System.out.println(" result " + getService().getPizzaByName(name));

        return getService().getPizzaByName(name);
    }

    /**
     * PUT method for updating or creating an instance of PizzaResource
     *
     * @param pizza
     * @param content representation for the resource
     */
    @POST
    @Path("addPizza")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPizza(Pizza pizza) {
        System.out.println("Add Pizza " + pizza.toString());

        if (pizza == null) {
            return Response.status(400).entity("Please add pizza details !!").build();
        }
        if (pizza.getName() == null) {
            return Response.status(400).entity("Please provide the pizza name !!").build();
        }
        getService().addPizza(pizza);
        System.out.println("Add pizza");
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePizzaById(@PathParam("id") Integer id, Pizza pizza) {
        System.out.println(" ID = "+id.toString());
        getService().updatePizza(pizza);
        return Response.ok().entity(pizza).build();
    }
}
