package dat.entities;

import dat.dtos.PokemonDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "pokemon")
public class Pokemon {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Setter
    @Column(name = "name", nullable = false)
    private String name;

    @Setter
    @Column(name = "height", nullable = false)
    private double height;

    @Setter
    @Column(name = "weight", nullable = false)
    private double weight;

    @Setter
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "pokemon_types", joinColumns = @JoinColumn(name = "pokemon_id"))
    @Column(name = "type")
    private List<String> types;

    @Setter
    @Column(name = "sprite_default", nullable = false)
    private String spriteDefault;

    @Setter
    @Column(name = "sprite_shiny", nullable = false)
    private String spriteShiny;

    @Setter
    @Column(name = "color", nullable = false)
    private String color;

    @Setter
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "pokemon_egg_groups", joinColumns = @JoinColumn(name = "pokemon_id"))
    @Column(name = "egg_group")
    private List<String> eggGroups;

    @Setter
    @Column(name = "evolution_chain")
    private int evolutionChain;

    @Setter
    @Column(name = "flavor_text_entries", nullable = false)
    private String flavorTextEntries;

    @Setter
    @Column(name = "is_legendary", nullable = false)
    private boolean isLegendary;

    @Setter
    @Column(name = "is_mythical", nullable = false)
    private boolean isMythical;

    @Setter
    @Column(name = "habitat", nullable = false)
    private String habitat;

    public Pokemon(int id, String name, double height, double weight, List<String> types, String spriteDefault, String spriteShiny,
                   String color, List<String> eggGroups, int evolutionChain, String flavorTextEntries, boolean isLegendary,
                   boolean isMythical, String habitat) {
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
        this.habitat = habitat;
    }

    public Pokemon(PokemonDTO pokemonDTO) {
        this.id = pokemonDTO.getId();
        this.name = pokemonDTO.getName();
        this.height = pokemonDTO.getHeight();
        this.weight = pokemonDTO.getWeight();
        this.types = pokemonDTO.getTypes();
        this.spriteDefault = pokemonDTO.getSpriteDefault();
        this.spriteShiny = pokemonDTO.getSpriteShiny();
        this.color = pokemonDTO.getColor();
        this.eggGroups = pokemonDTO.getEggGroups();
        this.evolutionChain = pokemonDTO.getEvolutionChain();
        this.flavorTextEntries = pokemonDTO.getFlavorTextEntries();
        this.isLegendary = pokemonDTO.isLegendary();
        this.isMythical = pokemonDTO.isMythical();
        this.habitat = pokemonDTO.getHabitat();
    }

}
