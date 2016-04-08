package asu.girish.raman.crud.gradebook.graman1.netbeans.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Girish
 */
public class AppealsClient {

    private final WebResource webResource;
    private final Client client;

    public AppealsClient() {
        ClientConfig clientConfig = new DefaultClientConfig();
        client = Client.create(clientConfig);
        webResource = client.resource("http://localhost:8080/CRUD-GradeBook-graman1-NetBeans/webresources").path("Gradebook/Appeals");
    }

    public ClientResponse viewAllAppeals() throws UniformInterfaceException {
        return webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
    }

    public ClientResponse fileAnAppeal(String jsonRequest) throws UniformInterfaceException {
        return webResource.type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, jsonRequest);
    }

    public void close() {
        client.destroy();
    }
}
