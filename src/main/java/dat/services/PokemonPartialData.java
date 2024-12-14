package dat.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // Ignore unknown properties at the root level
public class PokemonPartialData {
    private int id;
    private double height;
    private double weight;
    private List<TypeWrapper> types;

    @JsonProperty("sprites") // Map the JSON sprites field
    private Sprites sprites;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Sprites {

        @JsonProperty("other") // Map the JSON key "versions"
        private Other other;
        @JsonProperty("versions") // Map the JSON key "versions"
        private Versions versions;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Versions {
            @JsonProperty("generation-iii") // Map the JSON key "generation-iii"
            private GenerationIII generationIII;

            @Data
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class GenerationIII {
                @JsonProperty("emerald") // Map the JSON key "emerald"
                private Emerald emerald;

                @Data
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class Emerald {
                    @JsonProperty("front_default") // Map the JSON key "front_default"
                    private String frontDefault;

                    @JsonProperty("front_shiny") // Map the JSON key "front_shiny"
                    private String frontShiny;
                }
            }
        }
        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Other {
            @JsonProperty("official-artwork") // Map the JSON key "generation-iii"
            private OfficialArtwork officialArtwork;

            @Data
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class OfficialArtwork {
                @JsonProperty("front_default") // Map the JSON key "front_default"
                private String frontDefault;
                @JsonProperty("front_shiny") // Map the JSON key "front_shiny"
                private String frontShiny;

            }
        }
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true) // Handle unexpected properties in TypeWrapper
    public static class TypeWrapper {
        @JsonProperty("type") // Map the JSON field "type" into TypeWrapper
        private Type type;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Type {
            @JsonProperty("name") // Map the JSON field "name"
            private String name;
        }
    }
}
