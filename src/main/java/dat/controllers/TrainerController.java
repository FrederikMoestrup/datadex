package dat.controllers;
import dat.config.HibernateConfig;



import dat.daos.TrainerDAO;
import dat.dtos.PokemonDTO;
import dat.dtos.TrainerDTO;
import dat.exceptions.ApiException;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class TrainerController {

    private TrainerDAO trainerDAO;

    public TrainerController() {
        if (HibernateConfig.getTest()) {
            EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryForTest();
            this.trainerDAO = TrainerDAO.getInstance(emf);
        } else {
            EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("datadex");
            this.trainerDAO = TrainerDAO.getInstance(emf);
        }

    }

    public void getAll(Context ctx) {
        List<TrainerDTO> trainerDTOS = trainerDAO.getAll();
        ctx.res().setStatus(200);
        ctx.json(trainerDTOS, PokemonDTO.class);
    }



}
