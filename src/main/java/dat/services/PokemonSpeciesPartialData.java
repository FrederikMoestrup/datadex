package dat.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.intellij.lang.annotations.Language;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unrecognized fields gracefully
public class PokemonSpeciesPartialData {
    @JsonProperty("egg_groups") // Map the "egg_groups" field
    private List<EggGroup> eggGroups;
    private String name;
    private Color color;
    @JsonProperty("is_legendary")
    private boolean is_legendary;
    @JsonProperty("is_mythical")
    private boolean is_mythical;
    @JsonProperty("flavor_text_entries")
    private List<FlavorTextEntry> flavorTextEntries;
    private EvolutionChain evolution_chain;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class EvolutionChain {
        private String url;
    }

    @JsonProperty("pal_park_encounters")
    private List<PalParkEncounter> palParkEncounters;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class EggGroup {
        private String name;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FlavorTextEntry {
        @JsonProperty("flavor_text")
        private String flavorText;
        @JsonProperty("language")
        private Language language;
        @JsonProperty("version")
        private Version version;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Language {
            private String name;
        }
        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Version {
            private String name;
        }

    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Color {
        private String name;
    }
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PalParkEncounter {
        private Area area; // Map to the JSON's "area"

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Area {
            private String name;
        }
    }
}
