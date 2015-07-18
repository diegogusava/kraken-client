package br.com.diegogusava.kraken.controller;

import javax.enterprise.inject.Produces;

public class KrakenControllerProducer {

    private static final String KRAKEN_UPLOAD_BY_IMAGE_URL = "https://api.kraken.io/v1/url";
    private static final String KRAKEN_UPLOAD_BY_IMAGE = "https://api.kraken.io/v1/upload";

    @Produces
    public KrakenController getKrakenController() {
        return new KrakenController(KRAKEN_UPLOAD_BY_IMAGE, KRAKEN_UPLOAD_BY_IMAGE_URL);
    }

}
