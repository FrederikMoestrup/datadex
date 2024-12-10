package dat.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unrecognized fields gracefully
public class PokemonSpeciesPartialData {
    @JsonProperty("egg_groups") // Map the "egg_groups" field
    private List<EggGroup> eggGroups;

    private Color color;
    private List<FlavorTextEntry> flavorTextEntries;
    private boolean legendary;
    private boolean mythical;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class EggGroup {
        private String name;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FlavorTextEntry {
        private String flavorText;
        private Language language;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Language {
            private String name;
        }
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Color {
        private String name;
    }
}
