package dat.security.controllers;

import dat.config.HibernateConfig;


import dat.dtos.PokemonDTO;
import dat.dtos.PokedexEntryDTO;
import dat.exceptions.ApiException;
import dat.security.daos.UserDAO;
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class UserController {

    private UserDAO userDAO;

    public UserController() {
        if (HibernateConfig.getTest()) {
            EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryForTest();
            this.userDAO = UserDAO.getInstance(emf);
        } else {
            EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("datadex");
            this.userDAO = UserDAO.getInstance(emf);
        }

    }

    public void getAllFavoritesByUser(Context ctx) throws ApiException{
        try {
            String username = ctx.pathParam("username");
            List<PokedexEntryDTO> pokedexEntryDTOS = userDAO.getAllFavoritesByUser(username);
            ctx.res().setStatus(200);
            ctx.json(pokedexEntryDTOS, PokedexEntryDTO.class);
        } catch (NumberFormatException e) {
            throw new ApiException(400, "Missing required parameter: id");
        } catch (ApiException e) {
            throw new ApiException(404, "Pokemon not found");
        }
    }

    public void addPokemonToFavorite(Context ctx) throws ApiException{
        try {
            String username = ctx.pathParam("username");
            int pokemonId = Integer.parseInt(ctx.pathParam("id"));
            PokemonDTO pokemonDTO = userDAO.addPokemonToFavorite(username, pokemonId);
            ctx.res().setStatus(200);
            ctx.json(pokemonDTO, PokemonDTO.class);
        } catch (NumberFormatException e) {
            throw new ApiException(400, "Missing required parameter: id");
        }
    }

    public void removePokemonFromFavorite(Context ctx) throws ApiException{
        try {
            String username = ctx.pathParam("username");
            int pokemonId = Integer.parseInt(ctx.pathParam("id"));
            PokemonDTO pokemonDTO = userDAO.removePokemonFromFavorite(username, pokemonId);
            ctx.res().setStatus(200);
            ctx.json(pokemonDTO, PokemonDTO.class);
        } catch (NumberFormatException e) {
            throw new ApiException(400, "Missing required parameter: id");
        }
    }




}