package dat.routes;

import dat.controllers.PokemonController;
import dat.security.enums.Role;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class PokemonRoute {

    private final PokemonController pokemonController = new PokemonController();

    protected EndpointGroup getRoutes() {

        return () -> {
            get("/", pokemonController::getAll, Role.USER);
            get("/{id}", pokemonController::getById, Role.USER);
            post("/", pokemonController::create, Role.ADMIN);
            put("/{id}", pokemonController::update, Role.ADMIN);
            delete("/{id}", pokemonController::delete, Role.ADMIN);
            get("/evolution_chain/{id}", pokemonController::getByEvolutionChain, Role.USER);
            get("/rarity/legendary", pokemonController::getLegendary, Role.USER);
            get("/rarity/mythical", pokemonController::getMythical, Role.USER);
            get("/rarity/common", pokemonController::getCommon, Role.USER);
            get("/habitat/{habitat}", pokemonController::getByHabitat, Role.USER);
            get("/type/{type}", pokemonController::getByType, Role.USER);
            get("/egg_group/{group}", pokemonController::getByEggGroup, Role.USER);
            get("/color/{color}", pokemonController::getByColor, Role.USER);
        };
    }
}
