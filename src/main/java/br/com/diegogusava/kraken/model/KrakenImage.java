package br.com.diegogusava.kraken.model;

import org.immutables.value.Value;

@Value.Immutable
public interface KrakenImage {

    @Value.Parameter
    byte[] image();

}
