package br.com.diegogusava.kraken.controller;

import br.com.diegogusava.kraken.model.ImmutableKrakenCredential;
import br.com.diegogusava.kraken.model.ImmutableKrakenUploadRequest;
import br.com.diegogusava.kraken.model.KrakenCredential;
import br.com.diegogusava.kraken.model.KrakenUploadRequest;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class KrakenControllerTest {

    private static final int PORT = 8002;

    private static final String KRAKEN_UPLOAD_BY_IMAGE = "http://localhost:8002/v1/upload";
    private static final String KRAKEN_UPLOAD_BY_IMAGE_URL = "http://localhost:8002/v1/url";

    private static final String CALLBACK_URL = "http://www.diegogusava.com.br/kraken";

    public static final String IMAGE_URL = "http://www.diegogusava.com.br/image.jpg";

    private static final KrakenCredential credential = ImmutableKrakenCredential.of("123456", "123456");

    @Rule
    public WireMockClassRule wireMockRule = new WireMockClassRule(PORT);

    @Test
    public void postImageByUrlSuccess() {

        KrakenController controller = new KrakenController(KRAKEN_UPLOAD_BY_IMAGE, KRAKEN_UPLOAD_BY_IMAGE_URL);

        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("id", "1");
        JsonObject jsonResponse = builder.build();

        stubFor(post(urlMatching("/v1/url"))
                        .willReturn(aResponse()
                                        .withStatus(200)
                                        .withHeader("Content-Type", MediaType.APPLICATION_JSON)
                                        .withBody(jsonResponse.toString())
                        )
        );

        KrakenUploadRequest request = ImmutableKrakenUploadRequest.builder()
                .auth(credential)
                .lossy(true)
                .callbackUrl(CALLBACK_URL)
                .imageUrl(IMAGE_URL)
                .build();


        Optional<String> result = controller.uploadByImageURL(request);

        Assert.assertEquals("1", result.get());


    }

    @Test
    public void postImageByUrlError() {
        KrakenController controller = new KrakenController(KRAKEN_UPLOAD_BY_IMAGE, KRAKEN_UPLOAD_BY_IMAGE_URL);

        stubFor(post(urlMatching("/v1/url"))
                        .willReturn(aResponse()
                                        .withStatus(400)
                        )
        );

        KrakenUploadRequest request = ImmutableKrakenUploadRequest.builder()
                .auth(credential)
                .callbackUrl(CALLBACK_URL)
                .build();


        Optional<String> result = controller.uploadByImageURL(request);

        Assert.assertEquals(false, result.isPresent());

    }

}
