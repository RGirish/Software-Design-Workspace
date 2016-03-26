package asu.girish.raman.crud.gradebook.graman1.netbeans.client;

import com.sun.jersey.api.client.ClientResponse;

public class Main {

    public static void main(String[] args) {
        GradeBookClient gradeBookClient = new GradeBookClient();

//        String requestMessage = "{\n"
//                + "\"requestType\" : \"readGradingItem\",\n"
//                + "\"id\" : 2\n"
//                + "}";
//        String requestMessage = "{\n"
//                + "\"requestType\" : \"createGradingItem\",\n"
//                + "\"type\" : \"quiz\",\n"
//                + "\"name\" : \"Quiz 1\",\n"
//                + "\"percentage\" : 10\n"
//                + "}";
        String requestMessage = "{\n"
                + "\t\"requestType\" : \"updateGradingItem\",\n"
                + "\t\"id\" : 102,\n"
                + "\t\"name\" : \"Quiz 2\",\n"
                + "\t\"percentage\" : 20\n"
                + "}";
        
        ClientResponse response = gradeBookClient.createGradingItem(requestMessage);
        System.out.println(response.getStatus() + " - " + response.getEntity(String.class) + "\n\n");

        response = gradeBookClient.readGradingItem(requestMessage);
        System.out.println(response.getStatus() + " - " + response.getEntity(String.class) + "\n\n");

        response = gradeBookClient.updateGradingItem(requestMessage);
        System.out.println(response.getStatus() + " - " + response.getEntity(String.class) + "\n\n");

        response = gradeBookClient.deleteGradingItem(requestMessage);
        System.out.println(response.getStatus() + " - " + response.getEntity(String.class) + "\n\n");
        gradeBookClient.close();
    }
}
