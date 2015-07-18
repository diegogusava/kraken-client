package br.com.diegogusava.kraken.model;

import org.immutables.value.Value;

@Value.Immutable
public interface KrakedImage {

    @Value.Parameter
    byte[] image();

}
