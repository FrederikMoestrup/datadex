package dat.daos;

import dat.dtos.PokemonDTO;
import dat.dtos.PokedexEntryDTO;
import dat.dtos.TrainerDTO;
import dat.entities.Pokemon;
import dat.exceptions.ApiException;
import dat.security.entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;


public class TrainerDAO {

    private static TrainerDAO instance;
    private static EntityManagerFactory emf;

    public static TrainerDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new TrainerDAO();
        }
        return instance;
    }


    public List<TrainerDTO> getAll() {
        try (EntityManager em = emf.createEntityManager()) {
            List<TrainerDTO> trainerDTOS = em.createQuery("SELECT new dat.dtos.TrainerDTO(t) FROM Trainer t order by t.encounterNumber", TrainerDTO.class).getResultList();
            return trainerDTOS;
        }
    }

    public List<TrainerDTO> getAllKanto() {
        try (EntityManager em = emf.createEntityManager()) {
            List<TrainerDTO> trainerDTOS = em.createQuery("SELECT new dat.dtos.TrainerDTO(t) FROM Trainer t where t.region = 'Kanto' order by t.encounterNumber", TrainerDTO.class).getResultList();
            return trainerDTOS;
        }
    }

    public List<TrainerDTO> getAllJohto() {
        try (EntityManager em = emf.createEntityManager()) {
            List<TrainerDTO> trainerDTOS = em.createQuery("SELECT new dat.dtos.TrainerDTO(t) FROM Trainer t where t.region = 'Johto' order by t.encounterNumber", TrainerDTO.class).getResultList();
            return trainerDTOS;
        }
    }

    public List<TrainerDTO> getAllHoenn() {
        try (EntityManager em = emf.createEntityManager()) {
            List<TrainerDTO> trainerDTOS = em.createQuery("SELECT new dat.dtos.TrainerDTO(t) FROM Trainer t where t.region = 'Hoenn' order by t.encounterNumber", TrainerDTO.class).getResultList();
            return trainerDTOS;
        }
    }



}
