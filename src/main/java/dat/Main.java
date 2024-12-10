package dat;

import dat.config.ApplicationConfig;
import dat.config.HibernateConfig;
import dat.config.Populate;
import dat.dtos.PokemonDTO;
import dat.services.PokemonService;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {

        PokemonDTO pokemon = PokemonService.getPokemon(258); // Fetch Pokémon with ID 1
        System.out.println(pokemon);

    }
}


//EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("datadex");
//Populate.populate();
//ApplicationConfig.startServer(7070);