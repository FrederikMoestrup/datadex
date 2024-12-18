package dat.entities;

import dat.dtos.TrainerDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "trainer")
public class Trainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Setter
    @Column(name = "region", nullable = false)
    private String region;

    @Setter
    @Column(name = "town", nullable = false)
    private String town;

    @Setter
    @Column(name = "town_map")
    private String townMap;

    @Setter
    @Column(name = "sprite", nullable = false)
    private String sprite;

    @Setter
    @Column(name = "badge")
    private String badge;

    @Setter
    @Column(name = "type")
    private String type;

    @Setter
    @Column(name = "encounter_number", nullable = false)
    private int encounterNumber;

    @Setter
    @Column(name = "is_Elite4", nullable = false)
    private boolean isElite4;

    @Setter
    @Column(name = "is_Gym_leader", nullable = false)
    private boolean isGymLeader;

    @Setter
    @Column(name = "is_champion", nullable = false)
    private boolean isChampion;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "trainer_pokemon", // Name of the join table
            joinColumns = @JoinColumn(name = "trainer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pokemon_id", referencedColumnName = "id")
    )
    @OrderColumn(name = "list_order")
    private List<Pokemon> trainerPokemon = new ArrayList<>();


    public void addPokemon(Pokemon pokemon) {
        if (pokemon != null && !trainerPokemon.contains(pokemon)) {
            trainerPokemon.add(pokemon);
        }
    }

    public void removePokemon(Pokemon pokemon) {
        trainerPokemon.remove(pokemon);
    }

    public Trainer(String name, String region, String town, String townMap, String sprite,
                   String badge, String type, int encounterNumber, boolean isElite4, boolean isGymLeader, boolean isChampion, List<Pokemon> trainerPokemon) {
        this.name = name;
        this.region = region;
        this.town = town;
        this.townMap = townMap;
        this.sprite = sprite;
        this.badge = badge;
        this.type = type;
        this.encounterNumber = encounterNumber;
        this.isElite4 = isElite4;
        this.isGymLeader = isGymLeader;
        this.isChampion = isChampion;
        this.trainerPokemon = trainerPokemon;
    }

    public Trainer(TrainerDTO trainerDTO) {
        this.name = trainerDTO.getName();
        this.region = trainerDTO.getRegion();
        this.town = trainerDTO.getTown();
        this.townMap = trainerDTO.getTownMap();
        this.sprite = trainerDTO.getSprite();
        this.badge = trainerDTO.getBadge();
        this.type = trainerDTO.getType();
        this.encounterNumber = trainerDTO.getEncounterNumber();
        this.isElite4 = trainerDTO.isElite4();
        this.isGymLeader = trainerDTO.isGymLeader();
        this.isChampion = trainerDTO.isChampion();
        this.trainerPokemon = trainerDTO.getTrainerPokemon().stream()
                .map(Pokemon::new)
                .collect(Collectors.toList());
    }
}
