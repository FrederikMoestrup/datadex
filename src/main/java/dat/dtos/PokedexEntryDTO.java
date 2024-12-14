package dat.dtos;

import dat.entities.Pokemon;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class PokedexEntryDTO {

    private int id;
    private String name;
    private List<String> types;
    private String spriteDefault;
    private String spriteShiny;


    // Constructor to create DTO from the entity
    public PokedexEntryDTO(Pokemon pokemon) {
        this.id = pokemon.getId();
        this.name = pokemon.getName();
        this.types = pokemon.getTypes();
        this.spriteDefault = pokemon.getSpriteDefault();
        this.spriteShiny = pokemon.getSpriteShiny();
    }

    // Constructor for creating a DTO from raw data (optional)
    public PokedexEntryDTO(int id, String name, List<String> types, String spriteDefault, String spriteShiny) {
        this.id = id;
        this.name = name;
        this.types = types;
        this.spriteDefault = spriteDefault;
        this.spriteShiny = spriteShiny;
    }
}
