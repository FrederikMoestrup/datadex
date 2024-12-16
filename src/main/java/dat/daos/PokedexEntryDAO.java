package dat.daos;

import dat.dtos. PokedexEntryDTO;
import dat.entities.Pokemon;
import dat.exceptions.ApiException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;


public class PokedexEntryDAO {

    private static  PokedexEntryDAO instance;
    private static EntityManagerFactory emf;

    public static  PokedexEntryDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new  PokedexEntryDAO();
        }
        return instance;
    }

    public  PokedexEntryDTO getById(Integer integer) throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            Pokemon pokemon = em.find(Pokemon.class, integer);
            if(pokemon == null) {
                throw new ApiException(404, "Pokemon not found");
            }
            return new PokedexEntryDTO(pokemon);
        }
    }

    public List<PokedexEntryDTO> getAll() {
        try (EntityManager em = emf.createEntityManager()) {
            List<PokedexEntryDTO> pokemonDTOS = em.createQuery("SELECT new dat.dtos.PokedexEntryDTO(p) FROM Pokemon p order by p.id", PokedexEntryDTO.class).getResultList();
            return pokemonDTOS;
        }
    }


    public List<PokedexEntryDTO> getByEvolutionChain(int evolutionChain) throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            List<PokedexEntryDTO> pokedexEntryDTOS = em.createQuery("SELECT new dat.dtos.PokedexEntryDTO(p) FROM Pokemon p WHERE p.evolutionChain = :evolutionChain order by p.id", PokedexEntryDTO.class)
                    .setParameter("evolutionChain", evolutionChain)
                    .getResultList();
            if (pokedexEntryDTOS.isEmpty()) {
                throw new ApiException(404, "Evolution chain not found");
            }
            return pokedexEntryDTOS;
        }
    }

    public List<PokedexEntryDTO> getLegendary() throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            List<PokedexEntryDTO> pokedexEntryDTOS = em.createQuery("SELECT new dat.dtos.PokedexEntryDTO(p) FROM Pokemon p WHERE p.isLegendary = true order by p.id", PokedexEntryDTO.class)
                    .getResultList();
            if (pokedexEntryDTOS.isEmpty()) {
                throw new ApiException(404, "No legendaries found");
            }
            return pokedexEntryDTOS;
        }
    }

    public List<PokedexEntryDTO> getMythical() throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            List<PokedexEntryDTO> pokedexEntryDTOS = em.createQuery("SELECT new dat.dtos.PokedexEntryDTO(p) FROM Pokemon p WHERE p.isMythical = true order by p.id", PokedexEntryDTO.class)
                    .getResultList();
            if (pokedexEntryDTOS.isEmpty()) {
                throw new ApiException(404, "No mythical pokemon found");
            }
            return pokedexEntryDTOS;
        }
    }

    public List<PokedexEntryDTO> getCommon() throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            List<PokedexEntryDTO> pokedexEntryDTOS = em.createQuery("SELECT new dat.dtos.PokedexEntryDTO(p) FROM Pokemon p WHERE p.isLegendary = false AND p.isMythical = false order by p.id", PokedexEntryDTO.class)
                    .getResultList();
            if (pokedexEntryDTOS.isEmpty()) {
                throw new ApiException(404, "No common pokemon found");
            }
            return pokedexEntryDTOS;
        }
    }

    public List<PokedexEntryDTO> getByHabitat(String habitat) throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            List<PokedexEntryDTO> pokedexEntryDTOS = em.createQuery("SELECT new dat.dtos.PokedexEntryDTO(p) FROM Pokemon p WHERE p.habitat = :habitat order by p.id", PokedexEntryDTO.class)
                    .setParameter("habitat", habitat)
                    .getResultList();
            if (pokedexEntryDTOS.isEmpty()) {
                throw new ApiException(404, "No pokemon found in that habitat");
            }
            return pokedexEntryDTOS;
        }
    }

    public List<PokedexEntryDTO> getByType(String type) throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            List<PokedexEntryDTO> pokedexEntryDTOS = em.createQuery("SELECT new dat.dtos.PokedexEntryDTO(p) FROM Pokemon p JOIN p.types t WHERE t = :type order by p.id", PokedexEntryDTO.class)
                    .setParameter("type", type)
                    .getResultList();
            if (pokedexEntryDTOS.isEmpty()) {
                throw new ApiException(404, "No pokemon found with that type");
            }
            return pokedexEntryDTOS;
        }
    }

    public List<PokedexEntryDTO> getByEggGroup(String eggGroup) throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            List<PokedexEntryDTO> pokedexEntryDTOS = em.createQuery("SELECT new dat.dtos.PokedexEntryDTO(p) FROM Pokemon p JOIN p.eggGroups e WHERE e = :eggGroup order by p.id", PokedexEntryDTO.class)
                    .setParameter("eggGroup", eggGroup)
                    .getResultList();
            if (pokedexEntryDTOS.isEmpty()) {
                throw new ApiException(404, "No pokemon found from that egg group");
            }
            return pokedexEntryDTOS;
        }
    }
    public List<PokedexEntryDTO> getByColor(String color) throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            List<PokedexEntryDTO> pokedexEntryDTOS = em.createQuery("SELECT new dat.dtos.PokedexEntryDTO(p) FROM Pokemon p WHERE p.color = :color order by p.id", PokedexEntryDTO.class)
                    .setParameter("color", color)
                    .getResultList();
            if (pokedexEntryDTOS.isEmpty()) {
                throw new ApiException(404, "No pokemon found with that color");
            }
            return pokedexEntryDTOS;
        }
    }

}
