package ejb;

import entity.Game;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

/**
 * Created by s7impi on 24.05.15.
 */

@Singleton
@Startup
public class ConfigBean {

    @PersistenceContext
    private EntityManager em;
    private static final Logger looger = Logger.getLogger("ejb.ConfigBean");

    @PostConstruct
    public void init() {

        Game neuroshima = new Game();
        neuroshima.setId("neuroshima-hex-3.0");
        neuroshima.setName("Neuroshima Hex 3.0");
        neuroshima.setSnippet("Neuroshima Hex!");
        neuroshima.setDesigner("Michał Oracz");
        neuroshima.setPublisher("Portal Games");
        neuroshima.setYear_published("2006");
        neuroshima.setNumber_of_players("2-4");
        neuroshima.setDescription("Neuroshima Hex! is a strategy game set in the post-apocalyptic world of Neuroshima, a Polish role-playing game.");
        neuroshima.setPlaying_time("30 minutes");
        neuroshima.setCategory("Science-fiction");
        neuroshima.setSubdomain("Strategy game");
        neuroshima.setSuggested_age("10 and up");
        em.persist(neuroshima);
    }
}
