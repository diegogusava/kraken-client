package br.com.diegogusava.kraken.controller;

import br.com.diegogusava.kraken.model.KrakedImage;
import br.com.diegogusava.kraken.model.KrakenUploadRequest;
import br.com.diegogusava.kraken.reader.ImageMessageReader;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import javax.json.JsonObject;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.ServerErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class KrakenController {

    private final String uploadByImage;
    private final String uploadByImageUrl;

    KrakenController(String uploadByImage, String uploadByImageUrl) {
        this.uploadByImage = uploadByImage;
        this.uploadByImageUrl = uploadByImageUrl;
    }

    public Optional<String> uploadByImageURL(KrakenUploadRequest request) {

        final Logger logger = Logger.getLogger(KrakenController.class.getSimpleName());

        if (!request.imageUrl().isPresent()) {
            throw new IllegalArgumentException("Image URL cannot be null");
        }

        Client client = null;

        try {
            client = new ResteasyClientBuilder()
                    .establishConnectionTimeout(15, TimeUnit.SECONDS)
                    .socketTimeout(15, TimeUnit.SECONDS)
                    .build();
            URI uri = UriBuilder.fromPath(uploadByImageUrl).build();
            WebTarget webTarget = client.target(uri);
            Response response = webTarget
                    .request()
                    .accept(MediaType.APPLICATION_JSON)
                    .buildPost(Entity.json(request.toJson()))
                    .invoke();

            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                JsonObject responseJson = response.readEntity(JsonObject.class);
                return Optional.of(responseJson.getString("id"));
            }

        } catch (ClientErrorException ce) {
            logger.warning(ce.getMessage());
        } catch (ServerErrorException se) {
            logger.warning(se.getMessage());
        } finally {
            if (client != null) {
                client.close();
            }
        }

        return Optional.empty();

    }

    public void uploadByImage(KrakenUploadRequest request) {
        //TODO
    }

    public Optional<KrakedImage> downloadImage(String imageUrl) {

        final Logger logger = Logger.getLogger(KrakenController.class.getSimpleName());

        Client client = null;

        try {
            client = new ResteasyClientBuilder()
                    .register(ImageMessageReader.class)
                    .establishConnectionTimeout(15, TimeUnit.SECONDS)
                    .socketTimeout(15, TimeUnit.SECONDS)
                    .build();
            URI uri = UriBuilder.fromPath(imageUrl).build();
            WebTarget webTarget = client.target(uri);
            Response response = webTarget
                    .request()
                    .accept("image/*")
                    .buildGet()
                    .invoke();

            if (response.getStatus() == Response.Status.OK.getStatusCode()) {
                return Optional.of(response.readEntity(KrakedImage.class));
            }

        } catch (ClientErrorException ce) {
            logger.warning(ce.getMessage());
        } catch (ServerErrorException se) {
            logger.warning(se.getMessage());
        } finally {
            if (client != null) {
                client.close();
            }
        }
        return Optional.empty();
    }


}
