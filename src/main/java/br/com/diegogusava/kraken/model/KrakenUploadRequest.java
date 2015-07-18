package br.com.diegogusava.kraken.model;

import org.immutables.value.Value;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.Optional;

@Value.Immutable
public interface KrakenUploadRequest {

    KrakenCredential auth();

    String callbackUrl();

    Optional<Boolean> lossy();

    Optional<Boolean> webp();

    Optional<Integer> quality();

    Optional<KrakenResize> resize();

    Optional<KrakenConversion> conversion();

    Optional<String> imageUrl();

    Optional<Boolean> dev();

    default JsonObject toJson() {

        JsonObjectBuilder builder = Json.createObjectBuilder();

        builder.add("auth", auth().toJson());

        if (!callbackUrl().isEmpty()) {
            builder.add("callback_url", callbackUrl());
        }

        if (lossy().isPresent()) {
            builder.add("lossy", lossy().get());
        }

        if (webp().isPresent()) {
            builder.add("webp", lossy().get());
        }

        if (quality().isPresent()) {
            builder.add("quality", quality().get());
        }

        if (resize().isPresent()) {
            builder.add("resize", resize().get().toJson());
        }

        if (conversion().isPresent()) {
            builder.add("convert", conversion().get().toJson());
        }

        if (imageUrl().isPresent()) {
            builder.add("url", imageUrl().get());
        }

        if (dev().isPresent()) {
            builder.add("dev", dev().get());
        }

        return builder.build();
    }


}
