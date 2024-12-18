package dat.config;

import dat.entities.Pokemon;
import dat.entities.Trainer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PopulateTrainers {

    public static void populate() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory("datadex");

        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();

            // Create trainers for Kanto
            List<Trainer> kantoTrainers = getKantoTrainers(em);

            // Create trainers for Johto (to be implemented)
            // List<Trainer> johtoTrainers = getJohtoTrainers(em);

            // Create trainers for Hoenn (to be implemented)
            // List<Trainer> hoennTrainers = getHoennTrainers(em);

            // Persist all trainers
            kantoTrainers.forEach(em::persist);
            // johtoTrainers.forEach(em::persist);
            // hoennTrainers.forEach(em::persist);

            em.getTransaction().commit();
        }
    }

    @NotNull
    private static List<Trainer> getKantoTrainers(EntityManager em) {
        List<Trainer> trainers = new ArrayList<>();
        trainers.add(new Trainer("Brock", "Kanto", "Pewter City", "https://archives.bulbagarden.net/media/upload/c/cf/Pewter_City_FRLG.png", "https://archives.bulbagarden.net/media/upload/7/7c/Spr_FRLG_Brock.png", "https://archives.bulbagarden.net/media/upload/d/dd/Boulder_Badge.png", "Rock", 1, false, true, false, getPokemonForTrainer(em, 74, 95)));
        trainers.add(new Trainer("Misty", "Kanto", "Cerulean City", "https://archives.bulbagarden.net/media/upload/0/0b/Cerulean_City_FRLG.png", "https://archives.bulbagarden.net/media/upload/2/2c/Spr_FRLG_Misty.png", "https://archives.bulbagarden.net/media/upload/9/9c/Cascade_Badge.png", "Water", 2, false, true, false, getPokemonForTrainer(em, 120, 121)));
        trainers.add(new Trainer("Lt. Surge", "Kanto", "Vermilion City", "https://archives.bulbagarden.net/media/upload/8/8d/Vermilion_City_FRLG.png", "https://archives.bulbagarden.net/media/upload/5/5c/Spr_FRLG_Lt_Surge.png", "https://archives.bulbagarden.net/media/upload/a/a6/Thunder_Badge.png", "Electric", 3, false, true, false, getPokemonForTrainer(em, 100, 25, 26)));
        trainers.add(new Trainer("Erika", "Kanto", "Celadon City", "https://archives.bulbagarden.net/media/upload/e/ee/Celadon_City_FRLG.png", "https://archives.bulbagarden.net/media/upload/c/c9/Spr_FRLG_Erika.png", "https://archives.bulbagarden.net/media/upload/b/b5/Rainbow_Badge.png", "Grass", 4, false, true, false, getPokemonForTrainer(em, 71, 114, 45)));
        trainers.add(new Trainer("Koga", "Kanto", "Fuchsia City", "https://archives.bulbagarden.net/media/upload/d/d0/Fuchsia_City_FRLG.png", "https://archives.bulbagarden.net/media/upload/0/02/Spr_FRLG_Koga.png", "https://archives.bulbagarden.net/media/upload/7/7d/Soul_Badge.png", "Poison", 5, false, true, false, getPokemonForTrainer(em, 109, 89, 109, 110)));
        trainers.add(new Trainer("Sabrina", "Kanto", "Saffron City", "https://archives.bulbagarden.net/media/upload/5/53/Saffron_City_FRLG.png", "https://archives.bulbagarden.net/media/upload/d/dd/Spr_FRLG_Sabrina.png", "https://archives.bulbagarden.net/media/upload/6/6b/Marsh_Badge.png", "Psychic", 6, false, true, false, getPokemonForTrainer(em, 64, 122, 49, 65)));
        trainers.add(new Trainer("Blaine", "Kanto", "Cinnabar Island", "https://archives.bulbagarden.net/media/upload/6/65/Cinnabar_Island_FRLG.png", "https://archives.bulbagarden.net/media/upload/6/6d/Spr_FRLG_Blaine.png", "https://archives.bulbagarden.net/media/upload/1/12/Volcano_Badge.png", "Fire", 7, false, true, false, getPokemonForTrainer(em, 58, 77, 78, 59)));
        trainers.add(new Trainer("Giovanni", "Kanto", "Viridian City", "https://archives.bulbagarden.net/media/upload/9/9f/Viridian_City_FRLG.png", "https://archives.bulbagarden.net/media/upload/4/41/Spr_FRLG_Giovanni.png", "https://archives.bulbagarden.net/media/upload/7/78/Earth_Badge.png", "Ground", 8, false, true, false, getPokemonForTrainer(em, 111, 51, 31, 34, 111)));

        trainers.add(new Trainer("Lorelei", "Kanto", "Four Island", "https://archives.bulbagarden.net/media/upload/9/94/Four_Island_FRLG.png", "https://archives.bulbagarden.net/media/upload/d/db/Spr_FRLG_Lorelei.png", null, "Ice", 9, true, false, false, getPokemonForTrainer(em, 87, 91, 80, 124, 131)));
        trainers.add(new Trainer("Bruno", "Kanto", "Unknown", null, "https://archives.bulbagarden.net/media/upload/f/f7/Spr_FRLG_Bruno.png", null, "Fighting", 10, true, false, false, getPokemonForTrainer(em, 95, 107, 106, 95, 68 )));
        trainers.add(new Trainer("Agatha", "Kanto", "Unknown", null, "https://archives.bulbagarden.net/media/upload/5/56/Spr_FRLG_Agatha.png", null, "Ghost", 11, true, false, false, getPokemonForTrainer(em, 94, 42, 93, 24, 94)));
        trainers.add(new Trainer("Lance", "Kanto", "Blackthorn City", "https://archives.bulbagarden.net/media/upload/9/96/Blackthorn_City_HGSS.png", "https://archives.bulbagarden.net/media/upload/f/fb/Spr_FRLG_Lance.png", null, "Dragon", 12, true, false, false, getPokemonForTrainer(em, 130, 148, 148, 142, 149)));

        trainers.add(new Trainer("Blue", "Kanto", "Pallet Town", "https://archives.bulbagarden.net/media/upload/7/77/Pallet_Town_FRLG.png", "https://archives.bulbagarden.net/media/upload/1/10/Spr_FRLG_Blue_3.png", null, null, 13, false, false, true, getPokemonForTrainer(em, 18, 65, 112, 59, 103, 9)));


        return trainers;
    }

    @NotNull
    private static List<Trainer> getJohtoTrainers(EntityManager em) {
        List<Trainer> trainers = new ArrayList<>();
        trainers.add(new Trainer("Falkner", "Johto", "Violet City", "https://archives.bulbagarden.net/media/upload/5/55/Violet_City_HGSS.png", "https://archives.bulbagarden.net/media/upload/2/2b/Spr_HGSS_Falkner.png", "https://archives.bulbagarden.net/media/upload/4/4a/Zephyr_Badge.png", "Flying", 14, false, true, false, getPokemonForTrainer(em, 16, 17)));
        trainers.add(new Trainer("Bugsy", "Johto", "Azalea Town", "https://archives.bulbagarden.net/media/upload/0/0f/Azalea_Town_HGSS.png", "https://archives.bulbagarden.net/media/upload/b/b6/Spr_HGSS_Bugsy.png", "https://archives.bulbagarden.net/media/upload/0/08/Hive_Badge.png", "Bug", 15, false, true, false, getPokemonForTrainer(em, 11, 14, 123)));
        trainers.add(new Trainer("Whitney", "Johto", "Goldenrod City", "https://archives.bulbagarden.net/media/upload/6/65/Goldenrod_City_HGSS.png", "https://archives.bulbagarden.net/media/upload/f/fc/Spr_HGSS_Whitney.png", "https://archives.bulbagarden.net/media/upload/a/a7/Plain_Badge.png", "Normal", 16, false, true, false, getPokemonForTrainer(em, 35, 241)));
        trainers.add(new Trainer("Morty", "Johto", "Ecruteak City", "https://archives.bulbagarden.net/media/upload/7/77/Ecruteak_City_HGSS.png", "https://archives.bulbagarden.net/media/upload/archive/c/ca/20240316190058%21Spr_HGSS_Morty.png", "https://archives.bulbagarden.net/media/upload/4/48/Fog_Badge.png", "Ghost", 17, false, true, false, getPokemonForTrainer(em, 92, 93, 93, 94)));
        trainers.add(new Trainer("Chuck", "Johto", "Cianwood City", "https://archives.bulbagarden.net/media/upload/8/8a/Cianwood_City_HGSS.png", "https://archives.bulbagarden.net/media/upload/f/fd/Spr_HGSS_Chuck.png", "https://archives.bulbagarden.net/media/upload/b/b9/Storm_Badge.png", "Fighting", 18, false, true, false, getPokemonForTrainer(em, 57, 62)));
        trainers.add(new Trainer("Jasmine", "Johto", "Olivine City", "https://archives.bulbagarden.net/media/upload/f/f6/Olivine_City_HGSS.png", "https://archives.bulbagarden.net/media/upload/archive/4/44/20240316190008%21Spr_HGSS_Jasmine.png", "https://archives.bulbagarden.net/media/upload/7/7b/Mineral_Badge.png", "Steel", 19, false, true, false, getPokemonForTrainer(em, 81, 81, 208)));
        trainers.add(new Trainer("Pryce", "Johto", "Mahogany Town", "https://archives.bulbagarden.net/media/upload/9/9f/Mahogany_Town_HGSS.png", "https://archives.bulbagarden.net/media/upload/4/43/Spr_HGSS_Pryce.png", "https://archives.bulbagarden.net/media/upload/e/e6/Glacier_Badge.png", "Ice", 20, false, true, false, getPokemonForTrainer(em, 86, 87, 221)));
        trainers.add(new Trainer("Clair", "Johto", "Blackthorn City", "https://archives.bulbagarden.net/media/upload/9/96/Blackthorn_City_HGSS.png", "https://archives.bulbagarden.net/media/upload/d/d7/Spr_HGSS_Clair.png", "https://archives.bulbagarden.net/media/upload/5/58/Rising_Badge.png", "Dragon", 21, false, true, false, getPokemonForTrainer(em, 148, 148, 148, 230)));

        trainers.add(new Trainer("Will", "Johto", "Unknown", null, "https://archives.bulbagarden.net/media/upload/f/fd/Spr_HGSS_Will.png", null, "Psychic", 22, true, false, false, getPokemonForTrainer(em, 178, 124, 103, 80, 178)));
        trainers.add(new Trainer("Koga", "Johto", "Fuchsia City", "https://archives.bulbagarden.net/media/upload/d/d0/Fuchsia_City_FRLG.png", "https://archives.bulbagarden.net/media/upload/1/18/Spr_HGSS_Koga.png", null, "Poison", 23, true, false, false, getPokemonForTrainer(em, 168, 49, 205, 89, 169)));
        trainers.add(new Trainer("Bruno", "Johto", "Unknown", null, "https://archives.bulbagarden.net/media/upload/e/ee/Spr_HGSS_Bruno.png", null, "Fighting", 24, true, false, false, getPokemonForTrainer(em, 237, 106, 107, 95, 68)));
        trainers.add(new Trainer("Karen", "Johto", "Unknown", null, "https://archives.bulbagarden.net/media/upload/a/a2/Spr_HGSS_Karen.png", null, "Dark", 25, true, false, false, getPokemonForTrainer(em, 197, 45, 94, 198, 229)));

        trainers.add(new Trainer("Lance", "Johto", "Blackthorn City", "https://archives.bulbagarden.net/media/upload/9/96/Blackthorn_City_HGSS.png", "https://archives.bulbagarden.net/media/upload/1/1f/Spr_HGSS_Lance.png", null, "Dragon", 26, false, false, true, getPokemonForTrainer(em, 130, 149, 149, 142, 6, 149)));

        return trainers;
    }

    @NotNull
    private static List<Trainer> getHoennTrainers(EntityManager em) {
        List<Trainer> trainers = new ArrayList<>();
        trainers.add(new Trainer("Roxanne", "Hoenn", "Rustboro City", "https://archives.bulbagarden.net/media/upload/7/72/Rustboro_City_RS.png", "https://archives.bulbagarden.net/media/upload/e/ef/Spr_RS_Roxanne.png", "https://archives.bulbagarden.net/media/upload/6/63/Stone_Badge.png", "Rock", 27, false, true, false, getPokemonForTrainer(em, 74, 299)));
        trainers.add(new Trainer("Brawly", "Hoenn", "Dewford Town", "https://archives.bulbagarden.net/media/upload/1/1c/Dewford_Town_RS.png", "https://archives.bulbagarden.net/media/upload/9/92/Spr_RS_Brawly.png", "https://archives.bulbagarden.net/media/upload/9/97/Knuckle_Badge.png", "Fighting", 28, false, true, false, getPokemonForTrainer(em, 66, 296)));
        trainers.add(new Trainer("Wattson", "Hoenn", "Mauville City", "https://archives.bulbagarden.net/media/upload/d/d6/Mauville_City_RS.png", "https://bulbapedia.bulbagarden.net/wiki/File:Spr_RS_Wattson.png", "https://archives.bulbagarden.net/media/upload/3/34/Dynamo_Badge.png", "Electric", 29, false, true, false, getPokemonForTrainer(em, 81, 100, 82)));
        trainers.add(new Trainer("Flannery", "Hoenn", "Lavaridge Town", "https://archives.bulbagarden.net/media/upload/f/f7/Lavaridge_Town_RS.png", "https://archives.bulbagarden.net/media/upload/b/be/Spr_RS_Flannery.png", "https://archives.bulbagarden.net/media/upload/c/c4/Heat_Badge.png", "Fire", 30, false, true, false, getPokemonForTrainer(em, 218, 218, 324)));
        trainers.add(new Trainer("Norman", "Hoenn", "Petalburg City", "https://archives.bulbagarden.net/media/upload/e/ed/Petalburg_City_RS.png", "https://archives.bulbagarden.net/media/upload/7/75/Spr_RS_Norman.png", "https://archives.bulbagarden.net/media/upload/6/63/Balance_Badge.png", "Normal", 31, false, true, false, getPokemonForTrainer(em, 289, 288, 289)));
        trainers.add(new Trainer("Winona", "Hoenn", "Fortree City", "https://archives.bulbagarden.net/media/upload/7/7c/Fortree_City_RS.png", "https://bulbapedia.bulbagarden.net/wiki/File:Spr_RS_Winona.png", "https://archives.bulbagarden.net/media/upload/6/62/Feather_Badge.png", "Flying", 32, false, true, false, getPokemonForTrainer(em, 277, 279, 227, 334)));
        trainers.add(new Trainer("Tate and Liza", "Hoenn", "Mossdeep City", "https://archives.bulbagarden.net/media/upload/3/38/Mossdeep_City_RS.png", "https://bulbapedia.bulbagarden.net/wiki/File:Spr_RS_Tate_and_Liza.png", "https://archives.bulbagarden.net/media/upload/c/cc/Mind_Badge.png", "Psychic", 33, false, true, false, getPokemonForTrainer(em, 338, 337)));
        trainers.add(new Trainer("Wallace", "Hoenn", "Sootopolis City", "https://archives.bulbagarden.net/media/upload/c/cf/Sootopolis_City_RS.png", "https://bulbapedia.bulbagarden.net/wiki/File:Spr_RS_Wallace.png", "https://archives.bulbagarden.net/media/upload/9/9b/Rain_Badge.png", "Water", 34, false, true, false, getPokemonForTrainer(em, 370, 340, 364, 119, 350)));

        trainers.add(new Trainer("Sidney", "Hoenn", "Unknown", null, "https://archives.bulbagarden.net/media/upload/8/86/Spr_RS_Sidney.png", null, "Dark", 35, true, false, false, getPokemonForTrainer(em, 262, 275, 332, 319, 359)));
        trainers.add(new Trainer("Phoebe", "Hoenn", "Unknown", null, "https://archives.bulbagarden.net/media/upload/e/e6/Spr_RS_Phoebe.png", null, "Ghost", 36, true, false, false, getPokemonForTrainer(em, 356, 354, 302, 354, 356)));
        trainers.add(new Trainer("Glacia", "Hoenn", "Unknown", null, "https://archives.bulbagarden.net/media/upload/7/71/Spr_RS_Glacia.png", null, "Ice", 37, true, false, false, getPokemonForTrainer(em, 362, 364, 364, 362, 365)));
        trainers.add(new Trainer("Drake", "Hoenn", "Unknown", null, "https://archives.bulbagarden.net/media/upload/0/04/Spr_RS_Drake.png", null, "Dragon", 38, true, false, false, getPokemonForTrainer(em, 372, 334, 330, 330, 373)));

        trainers.add(new Trainer("Steven", "Hoenn", "Mossdeep City", "https://archives.bulbagarden.net/media/upload/3/38/Mossdeep_City_RS.png", "https://bulbapedia.bulbagarden.net/wiki/File:Spr_RS_Steven.png", null, "Steel", 39, false, false, true, getPokemonForTrainer(em, 227, 344, 306, 346, 348, 376)));

        return trainers;
    }

    @NotNull
    private static List<Pokemon> getPokemonForTrainer(EntityManager em, int... ids) {
        List<Pokemon> pokemonList = new ArrayList<>();
        for (int id : ids) {
            Pokemon pokemon = em.find(Pokemon.class, id);
            if (pokemon != null) {
                pokemonList.add(pokemon);
            }
        }
        return pokemonList;
    }
}
