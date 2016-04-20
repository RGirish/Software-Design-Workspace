/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.client.clients;

import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.client.representations.AppealRepresentation;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.UniformInterfaceException;
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

    public AppealRepresentation createAppeal(String request) throws UniformInterfaceException {
        AppealRepresentation appealRepresentation = webResource.accept(APPEALS_MEDIA_TYPE).type(APPEALS_MEDIA_TYPE).post(AppealRepresentation.class, request);
        return appealRepresentation;
    }

    public AppealRepresentation rewordAppeal(String rewordAppealUri, String xmlRequest) throws UniformInterfaceException {
        AppealRepresentation appealRepresentation;
        try {
            appealRepresentation = client.resource(rewordAppealUri).accept(APPEALS_MEDIA_TYPE).type(APPEALS_MEDIA_TYPE).put(AppealRepresentation.class, xmlRequest);
        } catch (UniformInterfaceException e) {
            if (e.toString().contains("404 Not Found")) {
                System.out.println("Response Code - 404 Not Found\n");
            } else if (e.toString().contains("409 Conflict")) {
                System.out.println("Response Code - 409 Conflict\n");
            } else if (e.toString().contains("400 Bad Request")) {
                System.out.println("Response Code - 400 Bad Request\n");
            } else if (e.toString().contains("500 Internal")) {
                System.out.println("Response Code - 500 Internal Server Error\n");
            }
            return null;
        }
        return appealRepresentation;
    }

    public AppealRepresentation addAppealItem(String addAppealItemUri, String request) throws UniformInterfaceException {
        AppealRepresentation appealRepresentation;
        try {
            appealRepresentation = client.resource(addAppealItemUri).accept(APPEALS_MEDIA_TYPE).type(APPEALS_MEDIA_TYPE).put(AppealRepresentation.class, request);
        } catch (UniformInterfaceException e) {
            if (e.toString().contains("404 Not Found")) {
                System.out.println("Response Code - 404 Not Found\n");
            } else if (e.toString().contains("409 Conflict")) {
                System.out.println("Response Code - 409 Conflict\n");
            } else if (e.toString().contains("400 Bad Request")) {
                System.out.println("Response Code - 400 Bad Request\n");
            } else if (e.toString().contains("500 Internal")) {
                System.out.println("Response Code - 500 Internal Server Error\n");
            }
            return null;
        }
        return appealRepresentation;
    }

    public AppealRepresentation followUp(String followUpUri, String request) throws UniformInterfaceException {
        AppealRepresentation appealRepresentation;
        try {
            appealRepresentation = client.resource(followUpUri).accept(APPEALS_MEDIA_TYPE).type(APPEALS_MEDIA_TYPE).put(AppealRepresentation.class, request);
        } catch (UniformInterfaceException e) {
            if (e.toString().contains("404 Not Found")) {
                System.out.println("Response Code - 404 Not Found\n");
            } else if (e.toString().contains("409 Conflict")) {
                System.out.println("Response Code - 409 Conflict\n");
            } else if (e.toString().contains("400 Bad Request")) {
                System.out.println("Response Code - 400 Bad Request\n");
            } else if (e.toString().contains("500 Internal")) {
                System.out.println("Response Code - 500 Internal Server Error\n");
            }
            return null;
        }
        return appealRepresentation;
    }

    public AppealRepresentation addImage(String addImageUri, String request) throws UniformInterfaceException {
        AppealRepresentation appealRepresentation;
        try {
            appealRepresentation = client.resource(addImageUri).accept(APPEALS_MEDIA_TYPE).type(APPEALS_MEDIA_TYPE).put(AppealRepresentation.class, request);
        } catch (UniformInterfaceException e) {
            if (e.toString().contains("404 Not Found")) {
                System.out.println("Response Code - 404 Not Found\n");
            } else if (e.toString().contains("409 Conflict")) {
                System.out.println("Response Code - 409 Conflict\n");
            } else if (e.toString().contains("400 Bad Request")) {
                System.out.println("Response Code - 400 Bad Request\n");
            } else if (e.toString().contains("500 Internal")) {
                System.out.println("Response Code - 500 Internal Server Error\n");
            }
            return null;
        }
        return appealRepresentation;
    }

    public AppealRepresentation reviewAppeal(String reviewAppealUri) throws UniformInterfaceException {
        AppealRepresentation appealRepresentation;
        try {
            appealRepresentation = client.resource(reviewAppealUri).accept(APPEALS_MEDIA_TYPE).type(APPEALS_MEDIA_TYPE).put(AppealRepresentation.class);
        } catch (UniformInterfaceException e) {
            if (e.toString().contains("404 Not Found")) {
                System.out.println("Response Code - 404 Not Found\n");
            } else if (e.toString().contains("409 Conflict")) {
                System.out.println("Response Code - 409 Conflict\n");
            } else if (e.toString().contains("400 Bad Request")) {
                System.out.println("Response Code - 400 Bad Request\n");
            } else if (e.toString().contains("500 Internal")) {
                System.out.println("Response Code - 500 Internal Server Error\n");
            }
            return null;
        }
        return appealRepresentation;
    }

    public AppealRepresentation finishAppealProcessing(String finishAppealProcessingUri) {
        AppealRepresentation appealRepresentation;
        try {
            appealRepresentation = client.resource(finishAppealProcessingUri).accept(APPEALS_MEDIA_TYPE).type(APPEALS_MEDIA_TYPE).put(AppealRepresentation.class);
        } catch (UniformInterfaceException e) {
            if (e.toString().contains("404 Not Found")) {
                System.out.println("Response Code - 404 Not Found\n");
            } else if (e.toString().contains("409 Conflict")) {
                System.out.println("Response Code - 409 Conflict\n");
            } else if (e.toString().contains("400 Bad Request")) {
                System.out.println("Response Code - 400 Bad Request\n");
            } else if (e.toString().contains("500 Internal")) {
                System.out.println("Response Code - 500 Internal Server Error\n");
            }
            return null;
        }
        return appealRepresentation;
    }

    public AppealRepresentation abandonAppeal(String abandonAppealUri) {
        AppealRepresentation appealRepresentation;
        try {
            appealRepresentation = client.resource(abandonAppealUri).accept(APPEALS_MEDIA_TYPE).type(APPEALS_MEDIA_TYPE).put(AppealRepresentation.class);
        } catch (UniformInterfaceException e) {
            if (e.toString().contains("404 Not Found")) {
                System.out.println("Response Code - 404 Not Found\n");
            } else if (e.toString().contains("409 Conflict")) {
                System.out.println("Response Code - 409 Conflict\n");
            } else if (e.toString().contains("400 Bad Request")) {
                System.out.println("Response Code - 400 Bad Request\n");
            } else if (e.toString().contains("500 Internal")) {
                System.out.println("Response Code - 500 Internal Server Error\n");
            }
            return null;
        }
        return appealRepresentation;
    }

}
