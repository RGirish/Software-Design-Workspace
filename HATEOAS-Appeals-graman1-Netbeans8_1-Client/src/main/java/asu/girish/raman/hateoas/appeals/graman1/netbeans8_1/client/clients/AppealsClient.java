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

    public AppealRepresentation rewordAppeal(String rewordAppealUri, String xmlRequest) {
        AppealRepresentation appealRepresentation = client.resource(rewordAppealUri).accept(APPEALS_MEDIA_TYPE).type(APPEALS_MEDIA_TYPE).put(AppealRepresentation.class, xmlRequest);
        return appealRepresentation;
    }

    public AppealRepresentation addAppealItem(String addAppealItemUri, String request) {
        AppealRepresentation appealRepresentation = client.resource(addAppealItemUri).accept(APPEALS_MEDIA_TYPE).type(APPEALS_MEDIA_TYPE).put(AppealRepresentation.class, request);
        return appealRepresentation;
    }

    public AppealRepresentation followUp(String followUpUri, String request) {
        AppealRepresentation appealRepresentation = client.resource(followUpUri).accept(APPEALS_MEDIA_TYPE).type(APPEALS_MEDIA_TYPE).put(AppealRepresentation.class, request);
        return appealRepresentation;
    }

    public AppealRepresentation addImage(String addAppealItemUri, String request) {
        AppealRepresentation appealRepresentation = client.resource(addAppealItemUri).accept(APPEALS_MEDIA_TYPE).type(APPEALS_MEDIA_TYPE).put(AppealRepresentation.class, request);
        return appealRepresentation;
    }

    public AppealRepresentation reviewAppeal(String reviewAppealUri) {
        AppealRepresentation appealRepresentation = client.resource(reviewAppealUri).accept(APPEALS_MEDIA_TYPE).put(AppealRepresentation.class);
        return appealRepresentation;
    }

    public AppealRepresentation finishAppealProcessing(String finishAppealProcessingUri) {
        AppealRepresentation appealRepresentation = client.resource(finishAppealProcessingUri).accept(APPEALS_MEDIA_TYPE).put(AppealRepresentation.class);
        return appealRepresentation;
    }

    public AppealRepresentation abandonAppeal(String abandonAppealUri) {
        AppealRepresentation appealRepresentation = client.resource(abandonAppealUri).accept(APPEALS_MEDIA_TYPE).put(AppealRepresentation.class);
        return appealRepresentation;
    }

}
