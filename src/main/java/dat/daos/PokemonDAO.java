package dat.daos;

import dat.dtos.PokemonDTO;
import dat.entities.Pokemon;
import dat.dtos.PokedexEntryDTO;
import dat.exceptions.ApiException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;


public class PokemonDAO implements IDAO<PokemonDTO, Integer> {

    private static PokemonDAO instance;
    private static EntityManagerFactory emf;

    public static PokemonDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PokemonDAO();
        }
        return instance;
    }

    @Override
    public PokemonDTO getById(Integer integer) throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            Pokemon pokemon = em.find(Pokemon.class, integer);
            if(pokemon == null) {
                throw new ApiException(404, "Pokemon not found");
            }
            return new PokemonDTO(pokemon);
        }
    }

    @Override
    public List<PokemonDTO> getAll() {
        try (EntityManager em = emf.createEntityManager()) {
            List<PokemonDTO> pokemonDTOS = em.createQuery("SELECT new dat.dtos.PokemonDTO(p) FROM Pokemon p order by p.id", PokemonDTO.class).getResultList();
            return pokemonDTOS;
        }
    }


    public List<PokedexEntryDTO> getPokedexEntries() {
        try (EntityManager em = emf.createEntityManager()) {
            List<PokedexEntryDTO> pokedexEntryDTOS = em.createQuery("SELECT new dat.dtos.PokedexEntryDTO(p) FROM Pokemon p order by p.id", PokedexEntryDTO.class).getResultList();
            return pokedexEntryDTOS;
        }
    }


    @Override
    public PokemonDTO create(PokemonDTO pokemonDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Pokemon pokemon = new Pokemon(pokemonDTO);
            em.persist(pokemon);
            em.getTransaction().commit();
            return new PokemonDTO(pokemon);
        }
    }

    @Override
    public PokemonDTO update(Integer id, PokemonDTO pokemonDTO){
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Pokemon pokemon = em.find(Pokemon.class, id);
            if (pokemon != null) {
                pokemon.setName(pokemonDTO.getName());
                pokemon.setHeight(pokemonDTO.getHeight());
                pokemon.setWeight(pokemonDTO.getWeight());
                pokemon.setTypes(pokemonDTO.getTypes());
                pokemon.setSpriteDefault(pokemonDTO.getSpriteDefault());
                pokemon.setSpriteShiny(pokemonDTO.getSpriteShiny());
                pokemon.setColor(pokemonDTO.getColor());
                pokemon.setEggGroups(pokemonDTO.getEggGroups());
                pokemon.setEvolutionChain(pokemonDTO.getEvolutionChain());
                pokemon.setFlavorTextEntries(pokemonDTO.getFlavorTextEntries());
                pokemon.setLegendary(pokemonDTO.isLegendary());
                pokemon.setMythical(pokemonDTO.isMythical());
                pokemon.setHabitat(pokemonDTO.getHabitat());
                pokemon.setOfficialArtDefault(pokemonDTO.getOfficialArtDefault());
                pokemon.setOfficialArtShiny(pokemonDTO.getOfficialArtShiny());
            }
            em.getTransaction().commit();
            return new PokemonDTO(pokemon);
        }
    }

    @Override
    public PokemonDTO delete(Integer integer) throws ApiException{
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Pokemon pokemon = em.find(Pokemon.class, integer);
            if (pokemon == null) {
                throw new ApiException(404, "Pokemon not found");
            }
            em.remove(pokemon);
            em.getTransaction().commit();
            return new PokemonDTO(pokemon);
        }
    }

    public boolean validatePrimaryKey(Integer id) {
        try (EntityManager em = emf.createEntityManager()) {
            Pokemon pokemon = em.find(Pokemon.class, id);
            return pokemon != null;
        }
    }

    public List<PokemonDTO> getByEvolutionChain(int evolutionChain) throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            List<PokemonDTO> pokemonDTOS = em.createQuery("SELECT new dat.dtos.PokemonDTO(p) FROM Pokemon p WHERE p.evolutionChain = :evolutionChain order by p.id", PokemonDTO.class)
                    .setParameter("evolutionChain", evolutionChain)
                    .getResultList();
            if (pokemonDTOS.isEmpty()) {
                throw new ApiException(404, "Evolution chain not found");
            }
            return pokemonDTOS;
        }
    }

    public List<PokemonDTO> getLegendary() throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            List<PokemonDTO> pokemonDTOS = em.createQuery("SELECT new dat.dtos.PokemonDTO(p) FROM Pokemon p WHERE p.isLegendary = true order by p.id", PokemonDTO.class)
                    .getResultList();
            if (pokemonDTOS.isEmpty()) {
                throw new ApiException(404, "No legendaries found");
            }
            return pokemonDTOS;
        }
    }

    public List<PokemonDTO> getMythical() throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            List<PokemonDTO> pokemonDTOS = em.createQuery("SELECT new dat.dtos.PokemonDTO(p) FROM Pokemon p WHERE p.isMythical = true order by p.id", PokemonDTO.class)
                    .getResultList();
            if (pokemonDTOS.isEmpty()) {
                throw new ApiException(404, "No mythical pokemon found");
            }
            return pokemonDTOS;
        }
    }

    public List<PokemonDTO> getCommon() throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            List<PokemonDTO> pokemonDTOS = em.createQuery("SELECT new dat.dtos.PokemonDTO(p) FROM Pokemon p WHERE p.isLegendary = false AND p.isMythical = false order by p.id", PokemonDTO.class)
                    .getResultList();
            if (pokemonDTOS.isEmpty()) {
                throw new ApiException(404, "No common pokemon found");
            }
            return pokemonDTOS;
        }
    }

    public List<PokemonDTO> getByHabitat(String habitat) throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            List<PokemonDTO> pokemonDTOS = em.createQuery("SELECT new dat.dtos.PokemonDTO(p) FROM Pokemon p WHERE p.habitat = :habitat order by p.id", PokemonDTO.class)
                    .setParameter("habitat", habitat)
                    .getResultList();
            if (pokemonDTOS.isEmpty()) {
                throw new ApiException(404, "No pokemon found in that habitat");
            }
            return pokemonDTOS;
        }
    }

    public List<PokemonDTO> getByType(String type) throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            List<PokemonDTO> pokemonDTOS = em.createQuery("SELECT new dat.dtos.PokemonDTO(p) FROM Pokemon p JOIN p.types t WHERE t = :type order by p.id", PokemonDTO.class)
                    .setParameter("type", type)
                    .getResultList();
            if (pokemonDTOS.isEmpty()) {
                throw new ApiException(404, "No pokemon found with that type");
            }
            return pokemonDTOS;
        }
    }

    public List<PokemonDTO> getByEggGroup(String eggGroup) throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            List<PokemonDTO> pokemonDTOS = em.createQuery("SELECT new dat.dtos.PokemonDTO(p) FROM Pokemon p JOIN p.eggGroups e WHERE e = :eggGroup order by p.id", PokemonDTO.class)
                    .setParameter("eggGroup", eggGroup)
                    .getResultList();
            if (pokemonDTOS.isEmpty()) {
                throw new ApiException(404, "No pokemon found from that egg group");
            }
            return pokemonDTOS;
        }
    }
    public List<PokemonDTO> getByColor(String color) throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            List<PokemonDTO> pokemonDTOS = em.createQuery("SELECT new dat.dtos.PokemonDTO(p) FROM Pokemon p WHERE p.color = :color order by p.id", PokemonDTO.class)
                    .setParameter("color", color)
                    .getResultList();
            if (pokemonDTOS.isEmpty()) {
                throw new ApiException(404, "No pokemon found with that color");
            }
            return pokemonDTOS;
        }
    }

}
