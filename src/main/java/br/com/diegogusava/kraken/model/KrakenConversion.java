package br.com.diegogusava.kraken.model;

import org.immutables.value.Value;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.Optional;

@Value.Immutable
public interface KrakenConversion {

    enum Format {
        JPEG, PNG, GIF
    }

    Optional<Format> format();

    Optional<Boolean> keepExtension();

    Optional<String> background();

    default JsonObject toJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();

        if (format().isPresent()) {
            builder.add("format", format().get().name().toLowerCase());
        }

        if (keepExtension().isPresent()) {
            builder.add("keep_extension", keepExtension().get());
        }

        if (background().isPresent()) {
            builder.add("background", background().get());
        }

        return builder.build();
    }

}
