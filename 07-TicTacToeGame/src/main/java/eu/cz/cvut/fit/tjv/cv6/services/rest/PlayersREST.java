package eu.cz.cvut.fit.tjv.cv6.services.rest;

import eu.cz.cvut.fit.tjv.cv6.tictactoe_cli.player.Player;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST service for CRUD of Player.
 *
 * @author aubitch
 */
@Stateless
@Path("/player")
public class PlayersREST {

    public PlayersREST() {
    }

    @PUT // nebo @GET
    //@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void create(String name) {
        // TADY BUDE KOD
    }
// todo naimplementujte create, list, ziskani jednoho hrace, count
    
    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    public int count()
    {
        return 2;
    }
    
    @GET
    @Path("/players")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Player> getPlayers()
    {
        List<Player> players = new ArrayList();
        players.add(new Player("Adam"));
        players.add(new Player("Eva"));
        return players;
    }
    
    @GET
    @Path("/player/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Player getPlayer(@QueryParam("id") Integer id)
    {
        List<Player> players = new ArrayList();
        players.add(new Player("Adam"));
        players.add(new Player("Eva"));
        return players.get(id);
    }
}
