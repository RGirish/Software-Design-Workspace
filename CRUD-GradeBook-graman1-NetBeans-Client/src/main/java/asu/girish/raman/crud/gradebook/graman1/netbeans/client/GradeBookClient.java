/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asu.girish.raman.crud.gradebook.graman1.netbeans.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import javax.ws.rs.core.MediaType;

public class GradeBookClient {

    private final WebResource webResource;
    private final Client client;

    public GradeBookClient() {
        ClientConfig clientConfig = new DefaultClientConfig();
        client = Client.create(clientConfig);
        webResource = client.resource("http://localhost:8080/CRUD-GradeBook-graman1-NetBeans/webresources").path("GradeBook");
    }

    public ClientResponse createGradingItem(String jsonRequestMessage) throws UniformInterfaceException {
        return webResource.path("createGradingItem").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, jsonRequestMessage);
    }

    public ClientResponse readGradingItem(String jsonRequestMessage) throws UniformInterfaceException {
        return webResource.path("readGradingItem").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, jsonRequestMessage);
    }

    public ClientResponse updateGradingItem(String jsonRequestMessage) throws UniformInterfaceException {
        return webResource.path("updateGradingItem").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, jsonRequestMessage);
    }

    public ClientResponse deleteGradingItem(String jsonRequestMessage) throws UniformInterfaceException {
        return webResource.path("deleteGradingItem").type(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, jsonRequestMessage);
    }

    public void close() {
        client.destroy();
    }

}
