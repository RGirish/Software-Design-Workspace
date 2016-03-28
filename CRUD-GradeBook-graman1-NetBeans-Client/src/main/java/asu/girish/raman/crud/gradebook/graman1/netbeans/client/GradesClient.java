package asu.girish.raman.crud.gradebook.graman1.netbeans.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import javax.ws.rs.core.MediaType;

public class GradesClient {

    private final WebResource webResource;
    private final Client client;

    public GradesClient() {
        ClientConfig clientConfig = new DefaultClientConfig();
        client = Client.create(clientConfig);
        webResource = client.resource("http://localhost:8080/CRUD-GradeBook-graman1-NetBeans/webresources").path("Grades");
    }

    public ClientResponse readGrades(int studentId, int gradingItemId) throws UniformInterfaceException {
        return webResource.path("/" + studentId + "/" + gradingItemId).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
    }

    ClientResponse updateGrade(String jsonRequest) {
        return webResource.path("/updateGrades").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).put(ClientResponse.class, jsonRequest);
    }

    public void close() {
        client.destroy();
    }
}
