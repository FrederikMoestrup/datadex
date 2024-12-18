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

    public void getAllKanto(Context ctx) {
        List<TrainerDTO> trainerDTOS = trainerDAO.getAllKanto();
        ctx.res().setStatus(200);
        ctx.json(trainerDTOS, PokemonDTO.class);
    }

    public void getAllJohto(Context ctx) {
        List<TrainerDTO> trainerDTOS = trainerDAO.getAllJohto();
        ctx.res().setStatus(200);
        ctx.json(trainerDTOS, PokemonDTO.class);
    }

    public void getAllHoenn(Context ctx) {
        List<TrainerDTO> trainerDTOS = trainerDAO.getAllHoenn();
        ctx.res().setStatus(200);
        ctx.json(trainerDTOS, PokemonDTO.class);
    }



}
