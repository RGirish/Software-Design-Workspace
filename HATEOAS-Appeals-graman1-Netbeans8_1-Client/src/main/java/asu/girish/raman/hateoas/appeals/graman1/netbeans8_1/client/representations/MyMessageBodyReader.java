/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.client.representations;

import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.client.models.Appeal;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;

/**
 *
 * @author Girish
 */
public class MyMessageBodyReader
        implements MessageBodyReader<AppealRepresentation> {

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return type == AppealRepresentation.class;
    }

    @Override
    public AppealRepresentation readFrom(Class<AppealRepresentation> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
//        try {
//            JAXBContext jaxbContext = JAXBContext.newInstance(MyBean.class);
//            MyBean myBean = (MyBean) jaxbContext.createUnmarshaller().unmarshal(entityStream);
//            return myBean;
//        } catch (JAXBException jaxbException) {
//            throw new ProcessingException("Error deserializing a MyBean.", jaxbException);
//        }
        return new AppealRepresentation(new Appeal());
    }
}
