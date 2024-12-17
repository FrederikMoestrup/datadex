package dat.routes;


import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class Routes {

    private final PokemonRoute pokemonRoute = new PokemonRoute();
    private final PokedexEntryRoute pokedexEntryRoute = new PokedexEntryRoute();
    private final UserRoute userRoute = new UserRoute();

    public EndpointGroup getRoutes() {
        return () -> {
            path("/pokemon", pokemonRoute.getRoutes());
            path("/pokedex", pokedexEntryRoute.getRoutes());
            path("/user", userRoute.getRoutes());

        };
    }
}
