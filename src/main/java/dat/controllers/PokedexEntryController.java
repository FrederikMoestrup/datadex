package dat.controllers;
import dat.config.HibernateConfig;


import dat.daos.PokedexEntryDAO;
import dat.dtos.PokedexEntryDTO;
import dat.exceptions.ApiException;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class PokedexEntryController {

    private PokedexEntryDAO pokedexEntryDAO;

    public PokedexEntryController() {
        if (HibernateConfig.getTest()) {
            EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryForTest();
            this.pokedexEntryDAO = pokedexEntryDAO.getInstance(emf);
        } else {
            EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("datadex");
            this.pokedexEntryDAO = pokedexEntryDAO.getInstance(emf);
        }

    }

    public void getAll(Context ctx) {
        List<PokedexEntryDTO> pokedexEntryDTOS = pokedexEntryDAO.getAll();
        ctx.res().setStatus(200);
        ctx.json(pokedexEntryDTOS, PokedexEntryDTO.class);
    }

    public void getById(Context ctx) throws ApiException {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            PokedexEntryDTO pokedexEntryDTO = pokedexEntryDAO.getById(id);
            ctx.res().setStatus(200);
            ctx.json(pokedexEntryDTO, PokedexEntryDTO.class);
        } catch (NumberFormatException e) {
            throw new ApiException(400, "Missing required parameter: id");
        } catch (ApiException e) {
            throw new ApiException(404, "Pokemon not found");
        }
    }

    public void getByEvolutionChain(Context ctx) throws ApiException {
        try {
            int evolutionChain = Integer.parseInt(ctx.pathParam("id"));
            List<PokedexEntryDTO> pokedexEntryDTOS = pokedexEntryDAO.getByEvolutionChain(evolutionChain);
            ctx.res().setStatus(200);
            ctx.json(pokedexEntryDTOS, PokedexEntryDTO.class);
        } catch (NumberFormatException e) {
            throw new ApiException(400, "Missing required parameter: Evolution chain id");
        } catch (ApiException e) {
            throw new ApiException(404, "Evolution chain not found");
        }
    }

    public void getLegendary(Context ctx) throws ApiException {
        try {
            List<PokedexEntryDTO> pokedexEntryDTOS = pokedexEntryDAO.getLegendary();
            ctx.res().setStatus(200);
            ctx.json(pokedexEntryDTOS, PokedexEntryDTO.class);
        }
        catch (ApiException e) {
            throw new ApiException(404, "No legendaries found");
        }
    }

    public void getMythical(Context ctx) throws ApiException {
        try {
            List<PokedexEntryDTO> pokedexEntryDTOS = pokedexEntryDAO.getMythical();
            ctx.res().setStatus(200);
            ctx.json(pokedexEntryDTOS, PokedexEntryDTO.class);
        } catch (ApiException e) {
            throw new ApiException(404, "No mythical pokemon found");
        }
    }

    public void getCommon(Context ctx) throws ApiException {
        try {
            List<PokedexEntryDTO> pokedexEntryDTOS = pokedexEntryDAO.getCommon();
            ctx.res().setStatus(200);
            ctx.json(pokedexEntryDTOS, PokedexEntryDTO.class);
        } catch (ApiException e) {
            throw new ApiException(404, "No common pokemon found");
        }
    }

    public void getByHabitat(Context ctx) throws ApiException {
        try {
            String habitat = ctx.pathParam("habitat");
            List<PokedexEntryDTO> pokedexEntryDTOS = pokedexEntryDAO.getByHabitat(habitat);
            ctx.res().setStatus(200);
            ctx.json(pokedexEntryDTOS, PokedexEntryDTO.class);
        } catch (ApiException e) {
            throw new ApiException(404, "No pokemon found in that habitat");
        }
    }

    public void getByType(Context ctx) throws ApiException {
        try {
            String type = ctx.pathParam("type");
            List<PokedexEntryDTO> pokedexEntryDTOS = pokedexEntryDAO.getByType(type);
            ctx.res().setStatus(200);
            ctx.json(pokedexEntryDTOS, PokedexEntryDTO.class);
        } catch (ApiException e) {
            throw new ApiException(404, "No pokemon found with that type");
        }
    }

    public void getByEggGroup(Context ctx) throws ApiException {
        try {
            String eggGroup = ctx.pathParam("group");
            List<PokedexEntryDTO> pokedexEntryDTOS = pokedexEntryDAO.getByEggGroup(eggGroup);
            ctx.res().setStatus(200);
            ctx.json(pokedexEntryDTOS, PokedexEntryDTO.class);
        } catch (ApiException e) {
            throw new ApiException(404, "No pokemon found from that egg group");
        }
    }

    public void getByColor(Context ctx) throws ApiException {
        try {
            String color = ctx.pathParam("color");
            List<PokedexEntryDTO> pokedexEntryDTOS = pokedexEntryDAO.getByColor(color);
            ctx.res().setStatus(200);
            ctx.json(pokedexEntryDTOS, PokedexEntryDTO.class);
        } catch (ApiException e) {
            throw new ApiException(404, "No pokemon found with that color");
        }
    }



}
