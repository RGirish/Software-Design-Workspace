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
public class GradingItemsClient {

    private final WebResource webResource;
    private final Client client;

    public GradingItemsClient() {
        ClientConfig clientConfig = new DefaultClientConfig();
        client = Client.create(clientConfig);
        webResource = client.resource("http://localhost:8080/CRUD-GradeBook-graman1-NetBeans/webresources").path("Gradebook/GradingItem");
    }

    public ClientResponse createGradingItem(String jsonRequestMessage) throws UniformInterfaceException {
        return webResource.path("createGradingItem").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, jsonRequestMessage);
    }

    public ClientResponse readGradingItem(int id) throws UniformInterfaceException {
        return webResource.path("readGradingItem/" + String.valueOf(id)).accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
    }

    public ClientResponse updateGradingItem(String jsonRequestMessage) throws UniformInterfaceException {
        return webResource.path("updateGradingItem").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).put(ClientResponse.class, jsonRequestMessage);
    }

    public ClientResponse deleteGradingItem(int id) throws UniformInterfaceException {
        return webResource.path("deleteGradingItem/" + String.valueOf(id)).delete(ClientResponse.class);
    }

    public void close() {
        client.destroy();
    }

    public String getAllGradingItemIDs() {
        return webResource.path("/getAllIDs").get(String.class);
    }

}
