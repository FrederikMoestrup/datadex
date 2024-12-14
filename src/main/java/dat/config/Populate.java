package dat.config;

import dat.entities.Pokemon;
import jakarta.persistence.EntityManagerFactory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Populate {
/*
    public static void populate() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("datadex");

        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();

            // Create Pokémon entities
            List<Pokemon> pokemons = getPokemons();

            // Persist Pokémon entities
            for (Pokemon pokemon : pokemons) {
                em.persist(pokemon);
            }

            em.getTransaction().commit();
        }
    }

    @NotNull
    private static List<Pokemon> getPokemons() {
        Pokemon p1 = new Pokemon(
                25,
                "Pikachu",
                0.4,
                6.0,
                List.of("Electric"),
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/emerald/25.png",
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/emerald/shiny/25.png",
                "Yellow",
                List.of("Field", "Fairy"),
                1,
                "Pikachu generates electricity in its cheeks.",
                false,
                false,
                "Viridian Forest"
        );

        Pokemon p2 = new Pokemon(
                6,
                "Charizard",
                1.7,
                90.5,
                List.of("Fire", "Flying"),
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/emerald/6.png",
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/emerald/shiny/6.png",
                "Red",
                List.of("Monster", "Dragon"),
                6,
                "Charizard breathes fire of such great heat it melts anything.",
                false,
                false,
                "Sky"
        );

        Pokemon p3 = new Pokemon(
                1,
                "Bulbasaur",
                0.7,
                6.9,
                List.of("Grass", "Poison"),
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/emerald/1.png",
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/emerald/shiny/1.png",
                "Green",
                List.of("Monster", "Grass"),
                1,
                "Bulbasaur grows by soaking up the sun's rays.",
                false,
                false,
                "Grasslands"
        );

        Pokemon p4 = new Pokemon(
                150,
                "Mewtwo",
                2.0,
                122.0,
                List.of("Psychic"),
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/emerald/150.png",
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-iii/emerald/shiny/150.png",
                "Purple",
                List.of("Undiscovered"),
                150,
                "Mewtwo is a genetically engineered Pokémon created by cloning Mew.",
                true,
                false,
                "Unknown"
        );

        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(p1);
        pokemons.add(p2);
        pokemons.add(p3);
        pokemons.add(p4);

        return pokemons;
    }

 */
}
