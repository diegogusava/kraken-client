package br.com.diegogusava.kraken.model;


import org.immutables.value.Value;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.Optional;

@Value.Immutable
public interface KrakenResize {

    enum Strategy {
        EXACT, PORTRAIT, LANDSCAPE, AUTO, FIT, CROP, SQUARE, FILL
    }

    Optional<Integer> quality();

    Optional<Integer> width();

    Optional<Integer> height();

    Optional<Integer> size();

    Optional<Strategy> strategy();

    Optional<String> background();

    default JsonObject toJson() {
        JsonObjectBuilder builder = Json.createObjectBuilder();

        if (quality().isPresent()) {
            builder.add("quality", quality().get());
        }

        if (width().isPresent()) {
            builder.add("width", width().get());
        }

        if (height().isPresent()) {
            builder.add("height", height().get());
        }

        if (size().isPresent()) {
            builder.add("size", size().get());
        }

        if (strategy().isPresent()) {
            builder.add("strategy", strategy().get().name().toLowerCase());
        }

        return builder.build();
    }

}
