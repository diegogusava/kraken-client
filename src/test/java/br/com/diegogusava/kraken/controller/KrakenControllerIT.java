package br.com.diegogusava.kraken.controller;

import br.com.diegogusava.kraken.model.ImmutableKrakenCredential;
import br.com.diegogusava.kraken.model.ImmutableKrakenUploadRequest;
import br.com.diegogusava.kraken.model.KrakenCredential;
import br.com.diegogusava.kraken.model.KrakenImage;
import br.com.diegogusava.kraken.model.KrakenUploadRequest;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class KrakenControllerIT {

    private static final KrakenCredential credential = ImmutableKrakenCredential.of("123456", "123456");

    private static final String CALLBACK_URL = "http://www.diegogusava.com.br/kraken";

    @Test
    public void uploadImageByUrlSuccess() {
        KrakenUploadRequest request = ImmutableKrakenUploadRequest.builder()
                .auth(credential)
                .lossy(true)
                .callbackUrl(CALLBACK_URL)
                .imageUrl("https://assets-cdn.github.com/images/modules/dashboard/bootcamp/octocat_setup.png")
                .build();
        KrakenControllerProducer producer = new KrakenControllerProducer();
        KrakenController controller = producer.getKrakenController();
        Optional<String> imageId = controller.uploadByImageURL(request);
        Assert.assertTrue(imageId.isPresent());

    }

    @Test
    public void downloadImageSuccess() {
        KrakenControllerProducer producer = new KrakenControllerProducer();
        KrakenController controller = producer.getKrakenController();
        Optional<KrakenImage> krakenImageOpt = controller.downloadImage("https://assets-cdn.github.com/images/modules/dashboard/bootcamp/octocat_setup.png");
        Assert.assertEquals(true, krakenImageOpt.isPresent());
    }

    @Test
    public void downloadImageError() {
        KrakenControllerProducer producer = new KrakenControllerProducer();
        KrakenController controller = producer.getKrakenController();
        Optional<KrakenImage> krakenImageOpt = controller.downloadImage("https://assets-cdn.github.com/images/modules/dashboard/bootcamp/ERROR.png");
        Assert.assertEquals(false, krakenImageOpt.isPresent());
    }


}
