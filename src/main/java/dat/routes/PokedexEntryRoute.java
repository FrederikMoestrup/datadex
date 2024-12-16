package dat.routes;

import dat.controllers.PokedexEntryController;
import dat.security.enums.Role;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class PokedexEntryRoute {

    private final PokedexEntryController pokedexEntryController = new PokedexEntryController();

    protected EndpointGroup getRoutes() {

        return () -> {
            get("/", pokedexEntryController::getAll, Role.USER);
            get("/{id}", pokedexEntryController::getById, Role.USER);
            get("/evolution_chain/{id}", pokedexEntryController::getByEvolutionChain, Role.USER);
            get("/rarity/legendary", pokedexEntryController::getLegendary, Role.USER);
            get("/rarity/mythical", pokedexEntryController::getMythical, Role.USER);
            get("/rarity/common", pokedexEntryController::getCommon, Role.USER);
            get("/habitat/{habitat}", pokedexEntryController::getByHabitat, Role.USER);
            get("/type/{type}", pokedexEntryController::getByType, Role.USER);
            get("/egg_group/{group}", pokedexEntryController::getByEggGroup, Role.USER);
            get("/color/{color}", pokedexEntryController::getByColor, Role.USER);
        };
    }
}
