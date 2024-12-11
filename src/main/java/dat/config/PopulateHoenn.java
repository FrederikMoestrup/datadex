package dat.config;

import dat.entities.Pokemon;
import dat.services.PokemonService;
import jakarta.persistence.EntityManagerFactory;

public class PopulateHoenn {

    public static void populate() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("datadex");

        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();

            // Fetch Pokémon with IDs from 252 to 386 (Hoenn region Pokémon)
            for (int id = 252; id <= 258; id++) {
                // Fetch Pokémon data using the PokemonService
                var pokemonDTO = PokemonService.getPokemon(id);

                // If the data is valid, persist it
                if (pokemonDTO != null) {
                    Pokemon pokemon = new Pokemon(pokemonDTO);
                    em.persist(pokemon);
                }


            }

            em.getTransaction().commit();
        }
    }
}
