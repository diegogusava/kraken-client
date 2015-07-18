package br.com.diegogusava.kraken.model;

import org.immutables.value.Value;

import javax.json.Json;
import javax.json.JsonObject;

@Value.Immutable
public interface KrakenCredential {

    @Value.Parameter
    String apiKey();

    @Value.Parameter
    String apiSecret();

    default JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("api_key", apiKey())
                .add("api_secret", apiSecret())
                .build();
    }
}
