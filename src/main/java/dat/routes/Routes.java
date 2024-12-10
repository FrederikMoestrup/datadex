package dat.routes;


import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Routes {

    private final PokemonRoute pokemonRoute = new PokemonRoute();

    public EndpointGroup getRoutes() {
        return () -> {
            path("/pokemon", pokemonRoute.getRoutes());
        };
    }
}
