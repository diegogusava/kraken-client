package br.com.diegogusava.kraken.reader;

import br.com.diegogusava.kraken.model.ImmutableKrakenImage;
import br.com.diegogusava.kraken.model.KrakenImage;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
public class ImageMessageReader implements MessageBodyReader<KrakenImage> {

    @Override
    public boolean isReadable(Class type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return mediaType.getType().equals("image");
    }

    @Override
    public KrakenImage readFrom(Class type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
        byte[] bytes = new byte[entityStream.available()];
        entityStream.read(bytes);
        return ImmutableKrakenImage.of(bytes);
    }

}
