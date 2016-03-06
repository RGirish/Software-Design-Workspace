package asu.girish.raman.pox.foodmenu.graman1.netbeans.client;

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
public class FoodMenuClient {

    private final WebResource webResource;
    private final Client client;

    /**
     * Initiates client by setting up the default configuration and webResource
     * by connecting it with the REST server's endpoint URI.
     */
    public FoodMenuClient() {
        ClientConfig clientConfig = new DefaultClientConfig();
        client = Client.create(clientConfig);
        /*
            You might want to change the port number here
         */
        webResource = client.resource("http://localhost:8080/POX-FoodMenu-graman1-NetBeans/webresources").path("FoodItem");
    }

    /**
     * This function is called from App.java and it processes a POST request
     * from the client and receives a response from the server.
     *
     * @param xmlRequestMessage The XML request message sent from App.java.
     * @return The XML response message in XML format.
     * @throws UniformInterfaceException
     */
    public String postXMLRequest(String xmlRequestMessage) throws UniformInterfaceException {
        ClientResponse response = webResource.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(ClientResponse.class, xmlRequestMessage);
        if (response.getStatus() != 200) {
            return "error_" + response.getStatus();
        }
        return response.getEntity(String.class);
    }

    /**
     * Destroys the client instance.
     */
    public void close() {
        client.destroy();
    }
}
