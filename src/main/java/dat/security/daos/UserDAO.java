package dat.security.daos;

import dat.dtos.PokemonDTO;
import dat.dtos.PokedexEntryDTO;
import dat.entities.Pokemon;
import dat.exceptions.ApiException;
import dat.security.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;


public class UserDAO {

    private static UserDAO instance;
    private static EntityManagerFactory emf;

    public static UserDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new UserDAO();
        }
        return instance;
    }


    public List<PokedexEntryDTO> getAllFavoritesByUser(String username) throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            // Use JPQL to fetch favorites and map them directly to DTOs
            List<PokedexEntryDTO> pokemonDTOS = em.createQuery("SELECT new dat.dtos.PokedexEntryDTO(p) FROM User u JOIN u.favorites p WHERE u.username = :userId", PokedexEntryDTO.class)
                    .setParameter("userId", username)
                    .getResultList();

            if (pokemonDTOS.isEmpty()) {
                throw new ApiException(404, "User has no favorite Pokémon");
            }

            return pokemonDTOS;
        }
    }


    public PokemonDTO addPokemonToFavorite(String username, Integer pokemonId){
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            User user = em.find(User.class, username);
            Pokemon pokemon = em.find(Pokemon.class, pokemonId);
            if (pokemon != null && user != null) {
                user.addFavorite(pokemon);
            }
            em.getTransaction().commit();
            return new PokemonDTO(pokemon);
        }
    }

    public PokemonDTO removePokemonFromFavorite(String username, Integer pokemonId){
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            User user = em.find(User.class, username);
            Pokemon pokemon = em.find(Pokemon.class, pokemonId);
            if (pokemon != null && user != null) {
                user.removeFavorite(pokemon);
            }
            em.getTransaction().commit();
            return new PokemonDTO(pokemon);
        }
    }


}
