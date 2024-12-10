package dat.dtos;

import dat.entities.Pokemon;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PokemonDTO {

    private int id;
    private String name;
    private double height;
    private double weight;
    private List<String> types;
    private String spriteDefault;
    private String spriteShiny;
    private String color;
    private List<String> eggGroups;
    private int evolutionChain;
    private String flavorTextEntries;
    private boolean isLegendary;
    private boolean isMythical;
    private String area;

    // Constructor to create DTO from the entity
    public PokemonDTO(Pokemon pokemon) {
        this.id = pokemon.getId();
        this.name = pokemon.getName();
        this.height = pokemon.getHeight();
        this.weight = pokemon.getWeight();
        this.types = pokemon.getTypes();
        this.spriteDefault = pokemon.getSpriteDefault();
        this.spriteShiny = pokemon.getSpriteShiny();
        this.color = pokemon.getColor();
        this.eggGroups = pokemon.getEggGroups();
        this.evolutionChain = pokemon.getEvolutionChain();
        this.flavorTextEntries = pokemon.getFlavorTextEntries();
        this.isLegendary = pokemon.isLegendary();
        this.isMythical = pokemon.isMythical();
        this.area = pokemon.getArea();
    }

    // Constructor for creating a DTO from raw data (optional)
    public PokemonDTO(int id, String name, double height, double weight, List<String> types, String spriteDefault,
                      String spriteShiny, String color, List<String> eggGroups, int evolutionChain,
                      String flavorTextEntries, boolean isLegendary, boolean isMythical, String area) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.types = types;
        this.spriteDefault = spriteDefault;
        this.spriteShiny = spriteShiny;
        this.color = color;
        this.eggGroups = eggGroups;
        this.evolutionChain = evolutionChain;
        this.flavorTextEntries = flavorTextEntries;
        this.isLegendary = isLegendary;
        this.isMythical = isMythical;
        this.area = area;
    }
}
