package dat.controllers;
import dat.config.HibernateConfig;


import dat.daos.PokemonDAO;
import dat.dtos.PokemonDTO;
import dat.exceptions.ApiException;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class PokemonController {

    private PokemonDAO pokemonDAO;

    public PokemonController() {
        if (HibernateConfig.getTest()) {
            EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryForTest();
            this.pokemonDAO = PokemonDAO.getInstance(emf);
        } else {
            EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("datadex");
            this.pokemonDAO = PokemonDAO.getInstance(emf);
        }

    }

    public void getAll(Context ctx) {
        List<PokemonDTO> pokemonDTOS = pokemonDAO.getAll();
        ctx.res().setStatus(200);
        ctx.json(pokemonDTOS, PokemonDTO.class);
    }

    public void getById(Context ctx) throws ApiException {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            PokemonDTO pokemonDTO = pokemonDAO.getById(id);
            ctx.res().setStatus(200);
            ctx.json(pokemonDTO, PokemonDTO.class);
        } catch (NumberFormatException e) {
            throw new ApiException(400, "Missing required parameter: id");
        } catch (ApiException e) {
            throw new ApiException(404, "Pokemon not found");
        }
    }

    public void create(Context ctx) {
        PokemonDTO pokemonDTO = ctx.bodyAsClass(PokemonDTO.class);
        PokemonDTO createdPokemonDTO = pokemonDAO.create(pokemonDTO);
        ctx.res().setStatus(201);
        ctx.json(createdPokemonDTO, PokemonDTO.class);
    }

    public void update(Context ctx) {
        int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey, "Not a valid id").get();

        PokemonDTO pokemonDTO = pokemonDAO.update(id, validateEntity(ctx));
        ctx.res().setStatus(200);
        ctx.json(pokemonDTO, PokemonDTO.class);
    }



    public void delete(Context ctx) throws ApiException {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            PokemonDTO pokemonDTO = pokemonDAO.delete(id);
            ctx.res().setStatus(200);
            ctx.json(pokemonDTO, PokemonDTO.class);
        } catch (NumberFormatException e) {
            throw new ApiException(400, "Missing required parameter: id");
        } catch (ApiException e) {
            throw new ApiException(404, "Pokemon not found");
        }
    }

    public PokemonDTO validateEntity(Context ctx) {
        return ctx.bodyValidator(PokemonDTO.class)
                .check(d -> d.getName() != null && !d.getName().isEmpty(), "Pokemon name must be set")
                .check(d -> d.getHeight() > 0, "Pokemon height must be greater than 0")
                .check(d -> d.getWeight() > 0, "Pokemon weight must be greater than 0")
                .check(d -> d.getTypes() != null && !d.getTypes().isEmpty(), "Pokemon types must be set")
                .check(d -> d.getSpriteDefault() != null && !d.getSpriteDefault().isEmpty(), "Default sprite must be set")
                .check(d -> d.getSpriteShiny() != null && !d.getSpriteShiny().isEmpty(), "Shiny sprite must be set")
                .check(d -> d.getColor() != null && !d.getColor().isEmpty(), "Pokemon color must be set")
                .check(d -> d.getEggGroups() != null && !d.getEggGroups().isEmpty(), "Egg groups must be set")
                .check(d -> d.getEvolutionChain() >= 0, "Evolution chain ID must be non-negative")
                .check(d -> d.getFlavorTextEntries() != null && !d.getFlavorTextEntries().isEmpty(), "Flavor text entries must be set")
                .check(d -> d.getHabitat() != null && !d.getHabitat().isEmpty(), "Pokemon habitat must be set")
                .get();
    }
    public boolean validatePrimaryKey(Integer integer) {
        return pokemonDAO.validatePrimaryKey(integer);
    }


    public void getByEvolutionChain(Context ctx) throws ApiException {
        try {
            int evolutionChain = Integer.parseInt(ctx.pathParam("id"));
            List<PokemonDTO> pokemonDTOS = pokemonDAO.getByEvolutionChain(evolutionChain);
            ctx.res().setStatus(200);
            ctx.json(pokemonDTOS, PokemonDTO.class);
        } catch (NumberFormatException e) {
            throw new ApiException(400, "Missing required parameter: Evolution chain id");
        } catch (ApiException e) {
            throw new ApiException(404, "Evolution chain not found");
        }
    }

    public void getLegendary(Context ctx) throws ApiException {
        try {
            List<PokemonDTO> pokemonDTOS = pokemonDAO.getLegendary();
            ctx.res().setStatus(200);
            ctx.json(pokemonDTOS, PokemonDTO.class);
        }
        catch (ApiException e) {
            throw new ApiException(404, "No legendaries found");
        }
    }

    public void getMythical(Context ctx) throws ApiException{
        try {
            List<PokemonDTO> pokemonDTOS = pokemonDAO.getMythical();
            ctx.res().setStatus(200);
            ctx.json(pokemonDTOS, PokemonDTO.class);
        }
        catch (ApiException e) {
            throw new ApiException(404, "No mythical pokemon found");
        }
    }

    public void getCommon(Context ctx) throws ApiException{
        try {
            List<PokemonDTO> pokemonDTOS = pokemonDAO.getCommon();
            ctx.res().setStatus(200);
            ctx.json(pokemonDTOS, PokemonDTO.class);
        }
        catch (ApiException e) {
            throw new ApiException(404, "No common pokemon found");
        }
    }

    public void getByHabitat(Context ctx) throws ApiException {
        try {
            String habitat = ctx.pathParam("habitat");
            List<PokemonDTO> pokemonDTOS = pokemonDAO.getByHabitat(habitat);
            ctx.res().setStatus(200);
            ctx.json(pokemonDTOS, PokemonDTO.class);
        } catch (ApiException e) {
            throw new ApiException(404, "No pokemon found in that habitat");
        }
    }

    public void getByType(Context ctx) throws ApiException {
        try {
            String type = ctx.pathParam("type");
            List<PokemonDTO> pokemonDTOS = pokemonDAO.getByType(type);
            ctx.res().setStatus(200);
            ctx.json(pokemonDTOS, PokemonDTO.class);
        } catch (ApiException e) {
            throw new ApiException(404, "No pokemon found with that type");
        }
    }

    public void getByEggGroup(Context ctx) throws ApiException {
        try {
            String eggGroup = ctx.pathParam("group");
            List<PokemonDTO> pokemonDTOS = pokemonDAO.getByEggGroup(eggGroup);
            ctx.res().setStatus(200);
            ctx.json(pokemonDTOS, PokemonDTO.class);
        } catch (ApiException e) {
            throw new ApiException(404, "No pokemon found from that egg group");
        }
    }
    public void getByColor(Context ctx) throws ApiException {
        try {
            String color = ctx.pathParam("color");
            List<PokemonDTO> pokemonDTOS = pokemonDAO.getByColor(color);
            ctx.res().setStatus(200);
            ctx.json(pokemonDTOS, PokemonDTO.class);
        } catch (ApiException e) {
            throw new ApiException(404, "No pokemon found with that color");
        }
    }


}
