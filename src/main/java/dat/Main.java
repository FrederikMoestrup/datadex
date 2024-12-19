package dat;

import dat.config.*;
import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("datadex");
        //PopulateNationaldex.populate();
        //PopulateTrainers.populate();
        ApplicationConfig.startServer(7070);

    }
}

