package dat.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dat.dtos.PokemonDTO;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import dat.services.PokemonSpeciesPartialData.EggGroup;
import dat.services.PokemonSpeciesPartialData.FlavorTextEntry;
import dat.services.PokemonSpeciesPartialData.FlavorTextEntry.Language;

public class PokemonService {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static PokemonDTO getPokemon(int id) {
        String pokemonData = getFromApi("https://pokeapi.co/api/v2/pokemon/", String.valueOf(id));
        String speciesData = getFromApi("https://pokeapi.co/api/v2/pokemon-species/", String.valueOf(id));

        if (pokemonData != null && speciesData != null) {
            return combinePokemonData(pokemonData, speciesData);
        }

        return new PokemonDTO();
    }

    private static String getFromApi(String baseUri, String additionalUri) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .header("Accept", "application/json")
                    .uri(new URI(baseUri + additionalUri))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body();
            } else {
                System.out.println("GET request failed. Status code: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static PokemonDTO combinePokemonData(String pokemonDataJson, String speciesDataJson) {
        try {
            // Deserialize JSON responses
            PokemonPartialData pokemonData = objectMapper.readValue(pokemonDataJson, PokemonPartialData.class);
            PokemonSpeciesPartialData speciesData = objectMapper.readValue(speciesDataJson, PokemonSpeciesPartialData.class);


            // Map and combine the fields into the DTO
            PokemonDTO dto = new PokemonDTO();
            dto.setId(pokemonData.getId());
            dto.setName(pokemonData.getName());
            dto.setHeight(pokemonData.getHeight());
            dto.setWeight(pokemonData.getWeight());

            // Map types safely
            List<String> types = pokemonData.getTypes() != null
                    ? pokemonData.getTypes().stream()
                    .map(typeWrapper -> typeWrapper.getType() != null ? typeWrapper.getType().getName() : "unknown")
                    .collect(Collectors.toList())
                    : new ArrayList<>();
            dto.setTypes(types);

            // Map sprites safely with null checks
            dto.setSpriteDefault(
                    pokemonData.getSprites() != null &&
                            pokemonData.getSprites().getVersions() != null &&
                            pokemonData.getSprites().getVersions().getGenerationIII() != null &&
                            pokemonData.getSprites().getVersions().getGenerationIII().getEmerald() != null
                            ? pokemonData.getSprites().getVersions().getGenerationIII().getEmerald().getFrontDefault()
                            : null
            );
            dto.setSpriteShiny(
                    pokemonData.getSprites() != null &&
                            pokemonData.getSprites().getVersions() != null &&
                            pokemonData.getSprites().getVersions().getGenerationIII() != null &&
                            pokemonData.getSprites().getVersions().getGenerationIII().getEmerald() != null
                            ? pokemonData.getSprites().getVersions().getGenerationIII().getEmerald().getFrontShiny()
                            : null
            );

            dto.setColor(speciesData != null && speciesData.getColor() != null
                    ? speciesData.getColor().getName()
                    : null
            );

            List<String> eggGroups = speciesData != null && speciesData.getEggGroups() != null
                    ? speciesData.getEggGroups().stream()
                    .map(EggGroup::getName)
                    .collect(Collectors.toList())
                    : new ArrayList<>();
            dto.setEggGroups(eggGroups);

            String flavorText = speciesData != null && speciesData.getFlavorTextEntries() != null
                    ? speciesData.getFlavorTextEntries().stream()
                    .filter(entry -> entry.getLanguage() != null && "en".equals(entry.getLanguage().getName()))
                    .map(FlavorTextEntry::getFlavorText)
                    .findFirst()
                    .orElse("No flavor text available.")
                    : "No flavor text available.";
            dto.setFlavorTextEntries(flavorText);

            dto.setLegendary(speciesData != null && speciesData.isLegendary());
            dto.setMythical(speciesData != null && speciesData.isMythical());

            return dto;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PokemonDTO();
    }

}
