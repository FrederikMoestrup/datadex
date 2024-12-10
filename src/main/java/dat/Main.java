package dat;

import dat.config.ApplicationConfig;
import dat.config.HibernateConfig;
import dat.config.Populate;
import dat.config.PopulateHoenn;
import dat.dtos.PokemonDTO;
import dat.services.PokemonService;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("datadex");
        //PopulateHoenn.populate();
        ApplicationConfig.startServer(7070);

    }
}

