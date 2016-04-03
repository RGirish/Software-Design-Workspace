package asu.girish.raman.crud.gradebook.graman1.netbeans.client;

import com.sun.jersey.api.client.ClientResponse;

public class Main {

    public static void main(String[] args) {
//        gradingItemsClient();
//        gradingItemsClient();
        studentProfilesClient(1, 1);
//        studentProfilesClient(1, 1);
//        studentProfilesClient(1, 1);
//        studentProfilesClient(1, 2);
//        studentProfilesClient(2, 1);
//        studentProfilesClient(2, 2);
//        appealsClient();
    }

    private static void studentProfilesClient(int studentId, int gradingItemId) {
        StudentProfilesClient studentProfilesClient = new StudentProfilesClient();
//        String requestMessage = "{\n"
//                + "\"requestType\" : \"createStudentProfile\",\n"
//                + "\"name\" : \"Girish\"\n"
//                + "}";
//        ClientResponse response = studentProfilesClient.createStudentProfile(requestMessage);
//        ClientResponse response = studentProfilesClient.readStudentProfile(2);
        ClientResponse response = studentProfilesClient.getAllGradesForAStudent(2);
//        ClientResponse response = studentProfilesClient.getGradeForAStudent(1, 2);
//        String requestMessage = "{\n"
//                + "\t\"requestType\" : \"updateGrades\",\n"
//                + "\t\"studentId\" : " + studentId + ",\n"
//                + "\t\"gradingItemId\" : " + gradingItemId + ",\n"
//                + "\t\"points\" : 20.0,\n"
//                + "\t\"feedback\" : \"Good job!\"\n"
//                + "}";
//        ClientResponse response = studentProfilesClient.updateGrade(requestMessage);
//        String requestMessage = "{\n"
//                + "\"requestType\" : \"updateStudentProfile\",\n"
//                + "\"id\" : 2,\n"
//                + "\"name\" : \"Giribhai\"\n"
//                + "}";
//        ClientResponse response = studentProfilesClient.updateStudentProfile(requestMessage);
//        ClientResponse response = studentProfilesClient.deleteStudentProfile(2);
        if (response.getStatus() != 204) {
            System.out.println(response.getStatus() + " - " + response.getEntity(String.class) + "\n\n");
        } else {
            System.out.println(response.getStatus() + " - No response body\n\n");
        }
        studentProfilesClient.close();
    }

    private static void gradingItemsClient() {
        GradingItemsClient gradingItemsClient = new GradingItemsClient();
        String requestMessage = "{\n"
                + "\"requestType\" : \"createGradingItem\",\n"
                + "\"type\" : \"quiz\",\n"
                + "\"name\" : \"Quiz 1\",\n"
                + "\"percentage\" : 10\n"
                + "}";
        ClientResponse response = gradingItemsClient.createGradingItem(requestMessage);
//        ClientResponse response = gradingItemsClient.readGradingItem(2);
//        String requestMessage = "{\n"
//                + "\t\"requestType\" : \"updateGradingItem\",\n"
//                + "\t\"id\" : 2,\n"
//                + "\t\"type\" : \"quiz\",\n"
//                + "\t\"name\" : \"Quiz 2\",\n"
//                + "\t\"percentage\" : 90\n"
//                + "}";
//        ClientResponse response = gradingItemsClient.updateGradingItem(requestMessage);
//        ClientResponse response = gradingItemsClient.deleteGradingItem(6);
        if (response.getStatus() != 204) {
            System.out.println(response.getStatus() + " - " + response.getEntity(String.class) + "\n\n");
        } else {
            System.out.println(response.getStatus() + " - No response body\n\n");
        }
        gradingItemsClient.close();
    }

    private static void appealsClient() {
        AppealsClient appealsClient = new AppealsClient();
        ClientResponse response = appealsClient.viewAllAppeals();
//        String requestMessage = "{\n"
//                + "\t\"requestType\" : \"fileAnAppeal\",\n"
//                + "\t\"studentId\" : 1,\n"
//                + "\t\"gradingItemId\" : 1,\n"
//                + "\t\"appealMessage\" : \"Hey, I deserve more!\"\n"
//                + "}";
//        ClientResponse response = appealsClient.fileAnAppeal(requestMessage);
        System.out.println(response.getStatus() + " - " + response.getEntity(String.class) + "\n\n");
        appealsClient.close();
    }
}
