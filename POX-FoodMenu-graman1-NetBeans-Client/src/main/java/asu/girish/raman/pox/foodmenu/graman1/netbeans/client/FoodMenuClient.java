package asu.girish.raman.pox.foodmenu.graman1.netbeans.client;

import com.sun.jersey.api.client.Client;
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

    public FoodMenuClient() {
        System.out.println("Creating a REST Client");
        ClientConfig config = new DefaultClientConfig();
        client = Client.create(config);
        webResource = client.resource("http://localhost:8080/POX-FoodMenu-graman1-NetBeans/webresources").path("FoodItem");
    }

    public String postXMLRequest(String xmlRequestMessage) throws UniformInterfaceException {
        System.out.println("XML Request message sent - \n" + xmlRequestMessage);
        webResource.type(MediaType.APPLICATION_XML).post(xmlRequestMessage);
        String response = webResource.accept(MediaType.APPLICATION_XML).post(String.class);
        return response;
    }

    public void close() {
        client.destroy();
    }
}
