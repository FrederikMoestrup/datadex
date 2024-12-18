package dat.routes;


import dat.controllers.UserController;
import dat.security.enums.Role;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class UserRoute {

    private final UserController userController  = new UserController ();

    protected EndpointGroup getRoutes() {

        return () -> {
            get("/{username}/favorites", userController::getAllFavoritesByUser, Role.USER);
            put("/{username}/favorites/add/{id}", userController::addPokemonToFavorite, Role.USER);
            put("/{username}/favorites/remove/{id}", userController::removePokemonFromFavorite, Role.USER);
        };
    }
}
