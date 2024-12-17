package dat.routes;


import dat.security.controllers.UserController;
import dat.security.enums.Role;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class UserRoute {

    private final UserController userController  = new UserController ();

    protected EndpointGroup getRoutes() {

        return () -> {
            get("/{username}", userController::getAllFavoritesByUser, Role.USER);
            put("/{username}/add_pokemon/{id}", userController::addPokemonToFavorite, Role.USER);
            put("/{username}/remove_pokemon/{id}", userController::removePokemonFromFavorite, Role.USER);
        };
    }
}
