package dat.dtos;

import dat.entities.Pokemon;
import dat.entities.Trainer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class TrainerDTO {

    private int id;
    private String name;
    private String region;
    private String town;
    private String townMap;
    private String sprite;
    private String badge;
    private String type;
    private int encounterNumber;
    private boolean isElite4;
    private boolean isGymLeader;
    private boolean isChampion;
    private List<PokemonDTO> trainerPokemon;

    public TrainerDTO(int id, String name, String region, String town, String townMap, String sprite,
                      String badge, String type, int encounterNumber, boolean isElite4,
                      boolean isGymLeader, boolean isChampion, List<PokemonDTO> trainerPokemon) {
        this.id = id;
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

    public TrainerDTO(Trainer trainer) {
        this.id = trainer.getId();
        this.name = trainer.getName();
        this.region = trainer.getRegion();
        this.town = trainer.getTown();
        this.townMap = trainer.getTownMap();
        this.sprite = trainer.getSprite();
        this.badge = trainer.getBadge();
        this.type = trainer.getType();
        this.encounterNumber = trainer.getEncounterNumber();
        this.isElite4 = trainer.isElite4();
        this.isGymLeader = trainer.isGymLeader();
        this.isChampion = trainer.isChampion();
        this.trainerPokemon = trainer.getTrainerPokemon().stream()
                .map(PokemonDTO::new)
                .collect(Collectors.toList()); // Changed to List
    }

}
