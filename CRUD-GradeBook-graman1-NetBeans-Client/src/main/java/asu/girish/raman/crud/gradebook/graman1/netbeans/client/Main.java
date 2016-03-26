package asu.girish.raman.crud.gradebook.graman1.netbeans.client;

import com.sun.jersey.api.client.ClientResponse;

public class Main {

    public static void main(String[] args) {
        GradeBookClient gradeBookClient = new GradeBookClient();

//        String requestMessage = "{\n"
//                + "\"requestType\" : \"createGradingItem\",\n"
//                + "\"type\" : \"quiz\",\n"
//                + "\"name\" : \"Quiz 1\",\n"
//                + "\"percentage\" : 10\n"
//                + "}";
//        ClientResponse response = gradeBookClient.createGradingItem(requestMessage);
//        ClientResponse response = gradeBookClient.readGradingItem(2);
//        String requestMessage = "{\n"
//                + "\t\"requestType\" : \"updateGradingItem\",\n"
//                + "\t\"id\" : 2,\n"
//                + "\t\"type\" : \"quiz\",\n"
//                + "\t\"name\" : \"Quiz 2\",\n"
//                + "\t\"percentage\" : 90\n"
//                + "}";
//        ClientResponse response = gradeBookClient.updateGradingItem(requestMessage);
        ClientResponse response = gradeBookClient.deleteGradingItem(6);
        if (response.getStatus() != 204) {
            System.out.println(response.getStatus() + " - " + response.getEntity(String.class) + "\n\n");
        } else {
            System.out.println(response.getStatus() + " - No response body\n\n");
        }
        gradeBookClient.close();
    }
}
