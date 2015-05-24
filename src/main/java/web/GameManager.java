package web;

import entity.Game;

import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by s7impi on 24.05.15.
 */

@Named
@SessionScoped
public class GameManager implements Serializable {

    private static final long serialVersionUID = 2020755500196885898L;
    private static final Logger logger = Logger.getLogger(GameManager.class.getName());

    private Client client;
    private final String baseUri = "http://localhost:8080/games/api";

    public GameManager() {
        client = ClientBuilder.newClient();
    }

    @PreDestroy
    private void clean() {
        client.close();
    }

    public List<Game> getGames() {
        List<Game> returnedGames = null;
        try {
            returnedGames = client.target(baseUri)
                    .path("/games")
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<Game>>() {});
            if (returnedGames == null) {
                logger.log(Level.SEVERE, "Returned games are null.");
            }
            else {
                logger.log(Level.INFO, "Games have been returned.");
            }
        }
        catch (WebApplicationException e) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        catch (ResponseProcessingException e) {
            logger.log(Level.SEVERE, "ReponseProcessingException thrown.");
            logger.log(Level.SEVERE, "Error is {0}", e.getMessage());
        }
        catch (ProcessingException e) {
            logger.log(Level.SEVERE, "ProcessingException thrown.");
            logger.log(Level.SEVERE, "Error is {0}", e.getMessage());
        }
        catch (Exception e) {
            logger.log(Level.SEVERE, "Error retrieving all games.");
            logger.log(Level.SEVERE, "base URI is {0}", baseUri);
            logger.log(Level.SEVERE, "path is {0}", "games");
            logger.log(Level.SEVERE, "Exception is {0}", e.getMessage());
        }
        return returnedGames;
    }
}
