package dat.routes;


import dat.controllers.TrainerController;
import dat.security.enums.Role;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class TrainerRoute {

    private final TrainerController trainerController  = new TrainerController ();

    protected EndpointGroup getRoutes() {

        return () -> {
            get("/", trainerController::getAll, Role.USER);
        };
    }
}
