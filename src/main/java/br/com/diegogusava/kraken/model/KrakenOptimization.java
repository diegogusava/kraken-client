package br.com.diegogusava.kraken.model;

import org.immutables.value.Value;

@Value.Immutable
public interface KrakenOptimization {

    Boolean lossy();

    Integer quality();

}
