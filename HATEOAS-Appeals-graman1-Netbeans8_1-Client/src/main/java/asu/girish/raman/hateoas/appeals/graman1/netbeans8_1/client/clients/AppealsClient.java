/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.client.clients;

import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.client.representations.AppealRepresentation;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

/**
 *
 * @author Girish
 */
public class AppealsClient {

    public static final String APPEALS_MEDIA_TYPE = "application/vnd-cse564-appeals+xml";
    public static final String ENTRY_POINT = "http://localhost:8080/HATEOAS-Appeals-graman1-Netbeans8_1/webresources/appeals";
    Client client;
    WebResource webResource;

    public AppealsClient() {
        client = Client.create();
        webResource = client.resource(ENTRY_POINT);
    }

    public AppealRepresentation createAppeal(String request) {
        AppealRepresentation appealRepresentation = webResource.accept(APPEALS_MEDIA_TYPE).type(APPEALS_MEDIA_TYPE).post(AppealRepresentation.class, request);
        return appealRepresentation;
    }

}
