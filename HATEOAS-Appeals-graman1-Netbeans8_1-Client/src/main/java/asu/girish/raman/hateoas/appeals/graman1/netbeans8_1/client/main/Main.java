/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.client.main;

import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.client.clients.AppealsClient;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.client.representations.AppealRepresentation;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Girish
 */
public class Main {

    static AppealsClient appealsClient, appealsClientWithBadEntryUri;

    public static void main(String[] args) {
        appealsClient = new AppealsClient();
        appealsClientWithBadEntryUri = new AppealsClient("bad");
        //happyCaseSimulator();
        //abandonedCaseSimulator();
        //forgottenCaseSimulator();
        //badIDSimulator();
        badStartSimulator();
    }

    public static void happyCaseSimulator() {
        System.out.println("\n\nHAPPY CASE SIMULATOR\n\n");
        Scanner scanner = new Scanner(System.in);

        //Step - 1 - CREATE an Appeal
        System.out.println("*** Step - 1 - CREATE an Appeal ***\n");
        String xmlRequest = "<appeal>\n"
                + "<studentID>10</studentID>\n"
                + "<gradingItemID>10</gradingItemID>\n"
                + "<appealMessage>Hey there</appealMessage>\n"
                + "</appeal>";
        AppealRepresentation appealRepresentation = appealsClient.createAppeal(xmlRequest);
        if (appealRepresentation == null) {
            System.out.println("Client Terminating...\n\n");
            return;
        }
        Map<String, String> map = appealRepresentation.getNextStateUris();
        int appealID = appealRepresentation.getAppealID();
        System.out.println("Resposne From Server : \n");
        System.out.println("appealID " + appealID);
        String addAppealItemUri = map.get("addAppealItemUri");
        System.out.println("addAppealItemUri " + addAppealItemUri);
        String rewordAppealUri = map.get("rewordAppealUri");
        System.out.println("rewordAppealUri " + rewordAppealUri);
        String addImageUri = map.get("addImageUri");
        System.out.println("addImageUri " + addImageUri);
        String followUpUri = map.get("followUpUri");
        System.out.println("followUpUri " + followUpUri);
        String reviewAppealUri = map.get("reviewAppealUri");
        System.out.println("reviewAppealUri " + reviewAppealUri);
        String abandonAppealUri = map.get("abandonAppealUri");
        System.out.println("abandonAppealUri " + abandonAppealUri);
        String readAppealUri = map.get("readAppealUri");
        System.out.println("readAppealUri " + readAppealUri);
        System.out.println("\n\nYou can access the readAppealUri while I'm waiting! Press <Enter> to proceed");
        scanner.nextLine();

        //Step - 2 - REWORD the Appeal (Update)
        System.out.println("*** Step - 2 - REWORD the Appeal (Update) ***\n");
        xmlRequest = "<appeal>\n"
                + "<appealMessage>Hey there - updated</appealMessage>\n"
                + "</appeal>";
        appealRepresentation = appealsClient.rewordAppeal(rewordAppealUri, xmlRequest);
        if (appealRepresentation == null) {
            System.out.println("Client Terminating...\n\n");
            return;
        }
        map = appealRepresentation.getNextStateUris();
        System.out.println("appealID " + appealID);
        addAppealItemUri = map.get("addAppealItemUri");
        System.out.println("addAppealItemUri " + addAppealItemUri);
        rewordAppealUri = map.get("rewordAppealUri");
        System.out.println("rewordAppealUri " + rewordAppealUri);
        addImageUri = map.get("addImageUri");
        System.out.println("addImageUri " + addImageUri);
        followUpUri = map.get("followUpUri");
        System.out.println("followUpUri " + followUpUri);
        reviewAppealUri = map.get("reviewAppealUri");
        System.out.println("reviewAppealUri " + reviewAppealUri);
        abandonAppealUri = map.get("abandonAppealUri");
        System.out.println("abandonAppealUri " + abandonAppealUri);
        readAppealUri = map.get("readAppealUri");
        System.out.println("readAppealUri " + readAppealUri);
        System.out.println("\n\nYou can access the readAppealUri while I'm waiting! Press <Enter> to proceed");
        scanner.nextLine();

        //Step - 3 - Start Appeal Review
        System.out.println("*** Step - 3 - Start Appeal Review ***\n");
        appealRepresentation = appealsClient.reviewAppeal(reviewAppealUri);
        if (appealRepresentation == null) {
            System.out.println("Client Terminating...\n\n");
            return;
        }
        map = appealRepresentation.getNextStateUris();
        System.out.println("appealID " + appealID);
        String finishAppealProcessingUri = map.get("finishAppealProcessingUri");
        System.out.println("finishAppealProcessingUri " + finishAppealProcessingUri);
        readAppealUri = map.get("readAppealUri");
        System.out.println("readAppealUri " + readAppealUri);
        System.out.println("\n\nYou can access the readAppealUri while I'm waiting! Press <Enter> to proceed");
        scanner.nextLine();

        //Step - 4 - Finish Appeal Review
        System.out.println("*** Step - 4 - Finish Appeal Review ***\n");
        appealRepresentation = appealsClient.finishAppealProcessing(finishAppealProcessingUri);
        if (appealRepresentation == null) {
            System.out.println("Client Terminating...\n\n");
            return;
        }
        map = appealRepresentation.getNextStateUris();
        System.out.println("appealID " + appealID);
        readAppealUri = map.get("readAppealUri");
        System.out.println("readAppealUri " + readAppealUri);
        System.out.println("\n\nHappy Case Simulator Completed!\n\n");
    }

    public static void abandonedCaseSimulator() {
        System.out.println("\n\nABANDONED CASE SIMULATOR\n\n");
        Scanner scanner = new Scanner(System.in);

        //Step - 1 - CREATE an Appeal
        System.out.println("*** Step - 1 - CREATE an Appeal ***\n");
        String xmlRequest = "<appeal>\n"
                + "<studentID>10</studentID>\n"
                + "<gradingItemID>10</gradingItemID>\n"
                + "<appealMessage>Hey there</appealMessage>\n"
                + "</appeal>";
        AppealRepresentation appealRepresentation = appealsClient.createAppeal(xmlRequest);
        if (appealRepresentation == null) {
            System.out.println("Client Terminating...\n\n");
            return;
        }
        Map<String, String> map = appealRepresentation.getNextStateUris();
        int appealID = appealRepresentation.getAppealID();
        System.out.println("appealID " + appealID);
        String addAppealItemUri = map.get("addAppealItemUri");
        System.out.println("addAppealItemUri " + addAppealItemUri);
        String rewordAppealUri = map.get("rewordAppealUri");
        System.out.println("rewordAppealUri " + rewordAppealUri);
        String addImageUri = map.get("addImageUri");
        System.out.println("addImageUri " + addImageUri);
        String followUpUri = map.get("followUpUri");
        System.out.println("followUpUri " + followUpUri);
        String reviewAppealUri = map.get("reviewAppealUri");
        System.out.println("reviewAppealUri " + reviewAppealUri);
        String abandonAppealUri = map.get("abandonAppealUri");
        System.out.println("abandonAppealUri " + abandonAppealUri);
        String readAppealUri = map.get("readAppealUri");
        System.out.println("readAppealUri " + readAppealUri);
        System.out.println("\n\nYou can access the readAppealUri while I'm waiting! Press <Enter> to proceed");
        scanner.nextLine();

        //Step - 2 - REWORD the Appeal (Update)
        System.out.println("*** Step - 2 - REWORD the Appeal (Update) ***\n");
        xmlRequest = "<appeal>\n"
                + "<appealMessage>Hey there - updated</appealMessage>\n"
                + "</appeal>";
        appealRepresentation = appealsClient.rewordAppeal(rewordAppealUri, xmlRequest);
        if (appealRepresentation == null) {
            System.out.println("Client Terminating...\n\n");
            return;
        }
        map = appealRepresentation.getNextStateUris();
        System.out.println("appealID " + appealID);
        addAppealItemUri = map.get("addAppealItemUri");
        System.out.println("addAppealItemUri " + addAppealItemUri);
        rewordAppealUri = map.get("rewordAppealUri");
        System.out.println("rewordAppealUri " + rewordAppealUri);
        addImageUri = map.get("addImageUri");
        System.out.println("addImageUri " + addImageUri);
        followUpUri = map.get("followUpUri");
        System.out.println("followUpUri " + followUpUri);
        reviewAppealUri = map.get("reviewAppealUri");
        System.out.println("reviewAppealUri " + reviewAppealUri);
        abandonAppealUri = map.get("abandonAppealUri");
        System.out.println("abandonAppealUri " + abandonAppealUri);
        readAppealUri = map.get("readAppealUri");
        System.out.println("readAppealUri " + readAppealUri);
        System.out.println("\n\nYou can access the readAppealUri while I'm waiting! Press <Enter> to proceed");
        scanner.nextLine();

        //Step - 3 - Abandon Appeal
        System.out.println("*** Step - 3 - Abandon Appeal***\n");
        appealRepresentation = appealsClient.abandonAppeal(abandonAppealUri);
        if (appealRepresentation == null) {
            System.out.println("Client Terminating...\n\n");
            return;
        }
        map = appealRepresentation.getNextStateUris();
        System.out.println("appealID " + appealID);
        readAppealUri = map.get("readAppealUri");
        System.out.println("readAppealUri " + readAppealUri);
        System.out.println("\n\nAbandoned Case Simulator Completed!\n\n");

    }

    public static void forgottenCaseSimulator() {
        System.out.println("\n\nFORGOTTEN CASE SIMULATOR\n\n");
        Scanner scanner = new Scanner(System.in);

        //Step - 1 - CREATE an Appeal
        System.out.println("*** Step - 1 - CREATE an Appeal ***\n");
        String xmlRequest = "<appeal>\n"
                + "<studentID>10</studentID>\n"
                + "<gradingItemID>10</gradingItemID>\n"
                + "<appealMessage>Hey there</appealMessage>\n"
                + "</appeal>";
        AppealRepresentation appealRepresentation = appealsClient.createAppeal(xmlRequest);
        if (appealRepresentation == null) {
            System.out.println("Client Terminating...\n\n");
            return;
        }
        Map<String, String> map = appealRepresentation.getNextStateUris();
        int appealID = appealRepresentation.getAppealID();
        System.out.println("appealID " + appealID);
        String addAppealItemUri = map.get("addAppealItemUri");
        System.out.println("addAppealItemUri " + addAppealItemUri);
        String rewordAppealUri = map.get("rewordAppealUri");
        System.out.println("rewordAppealUri " + rewordAppealUri);
        String addImageUri = map.get("addImageUri");
        System.out.println("addImageUri " + addImageUri);
        String followUpUri = map.get("followUpUri");
        System.out.println("followUpUri " + followUpUri);
        String reviewAppealUri = map.get("reviewAppealUri");
        System.out.println("reviewAppealUri " + reviewAppealUri);
        String abandonAppealUri = map.get("abandonAppealUri");
        System.out.println("abandonAppealUri " + abandonAppealUri);
        String readAppealUri = map.get("readAppealUri");
        System.out.println("readAppealUri " + readAppealUri);
        System.out.println("\n\nYou can access the readAppealUri while I'm waiting! Press <Enter> to proceed");
        scanner.nextLine();

        System.out.println("\nA Week goes by... No response from the Instructor! Let's follow up on the appeal. Press <Enter> to proceed\n");
        scanner.nextLine();

        //Step - 2 - FOLLOW UP on the Appeal
        System.out.println("*** Step - 2 - FOLLOW UP on the Appeal ***\n");
        xmlRequest = "<appeal>\n"
                + "<followUp>Hello. It has been long. What is happeneing?</followUp>\n"
                + "</appeal>";
        appealRepresentation = appealsClient.followUp(followUpUri, xmlRequest);
        if (appealRepresentation == null) {
            System.out.println("Client Terminating...\n\n");
            return;
        }
        map = appealRepresentation.getNextStateUris();
        System.out.println("appealID " + appealID);
        addAppealItemUri = map.get("addAppealItemUri");
        System.out.println("addAppealItemUri " + addAppealItemUri);
        rewordAppealUri = map.get("rewordAppealUri");
        System.out.println("rewordAppealUri " + rewordAppealUri);
        addImageUri = map.get("addImageUri");
        System.out.println("addImageUri " + addImageUri);
        reviewAppealUri = map.get("reviewAppealUri");
        followUpUri = map.get("followUpUri");
        System.out.println("followUpUri " + followUpUri);
        System.out.println("reviewAppealUri " + reviewAppealUri);
        abandonAppealUri = map.get("abandonAppealUri");
        System.out.println("abandonAppealUri " + abandonAppealUri);
        readAppealUri = map.get("readAppealUri");
        System.out.println("readAppealUri " + readAppealUri);
        System.out.println("\n\nYou can access the readAppealUri while I'm waiting! Press <Enter> to proceed");
        scanner.nextLine();

        System.out.println("\nNow the instructor notices the appeal. And acts on it. Press <Enter> to proceed\n");
        scanner.nextLine();

        //Step - 3 - Start Appeal Review
        System.out.println("*** Step - 3 - Start Appeal Review ***\n");
        appealRepresentation = appealsClient.reviewAppeal(reviewAppealUri);
        if (appealRepresentation == null) {
            System.out.println("Client Terminating...\n\n");
            return;
        }
        map = appealRepresentation.getNextStateUris();
        System.out.println("appealID " + appealID);
        String finishAppealProcessingUri = map.get("finishAppealProcessingUri");
        System.out.println("finishAppealProcessingUri " + finishAppealProcessingUri);
        readAppealUri = map.get("readAppealUri");
        System.out.println("readAppealUri " + readAppealUri);
        System.out.println("\n\nYou can access the readAppealUri while I'm waiting! Press <Enter> to proceed");
        scanner.nextLine();

        //Step - 4 - Finish Appeal Review
        System.out.println("*** Step - 4 - Finish Appeal Review ***\n");
        appealRepresentation = appealsClient.finishAppealProcessing(finishAppealProcessingUri);
        if (appealRepresentation == null) {
            System.out.println("Client Terminating...\n\n");
            return;
        }
        map = appealRepresentation.getNextStateUris();
        System.out.println("appealID " + appealID);
        readAppealUri = map.get("readAppealUri");
        System.out.println("readAppealUri " + readAppealUri);
        System.out.println("\n\nForgotten Case Simulator Completed!\n\n");
    }

    public static void badStartSimulator() {
        System.out.println("\n\nBAD START SIMULATOR\n\n");
        Scanner scanner = new Scanner(System.in);

        //Step - 1 - CREATE an Appeal
        System.out.println("*** Step - 1 - CREATE an Appeal ***\n");
        String xmlRequest = "<appeal>\n"
                + "<studentID>10</studentID>\n"
                + "<gradingItemID>10</gradingItemID>\n"
                + "<appealMessage>Hey there</appealMessage>\n"
                + "</appeal>";
        AppealRepresentation appealRepresentation = appealsClientWithBadEntryUri.createAppeal(xmlRequest);
        if (appealRepresentation == null) {
            System.out.println("Client Terminating...\n\n");
            return;
        }
        Map<String, String> map = appealRepresentation.getNextStateUris();
        int appealID = appealRepresentation.getAppealID();
        System.out.println("Resposne From Server : \n");
        System.out.println("appealID " + appealID);
        String addAppealItemUri = map.get("addAppealItemUri");
        System.out.println("addAppealItemUri " + addAppealItemUri);
        String rewordAppealUri = map.get("rewordAppealUri");
        System.out.println("rewordAppealUri " + rewordAppealUri);
        String addImageUri = map.get("addImageUri");
        System.out.println("addImageUri " + addImageUri);
        String followUpUri = map.get("followUpUri");
        System.out.println("followUpUri " + followUpUri);
        String reviewAppealUri = map.get("reviewAppealUri");
        System.out.println("reviewAppealUri " + reviewAppealUri);
        String abandonAppealUri = map.get("abandonAppealUri");
        System.out.println("abandonAppealUri " + abandonAppealUri);
        String readAppealUri = map.get("readAppealUri");
        System.out.println("readAppealUri " + readAppealUri);
        System.out.println("\n\nYou can access the readAppealUri while I'm waiting! Press <Enter> to proceed");
        scanner.nextLine();

        //Step - 2 - REWORD the Appeal (Update)
        System.out.println("*** Step - 2 - REWORD the Appeal (Update) ***\n");
        xmlRequest = "<appeal>\n"
                + "<appealMessage>Hey there - updated</appealMessage>\n"
                + "</appeal>";
        appealRepresentation = appealsClientWithBadEntryUri.rewordAppeal(rewordAppealUri, xmlRequest);
        if (appealRepresentation == null) {
            System.out.println("Client Terminating...\n\n");
            return;
        }
        map = appealRepresentation.getNextStateUris();
        System.out.println("appealID " + appealID);
        addAppealItemUri = map.get("addAppealItemUri");
        System.out.println("addAppealItemUri " + addAppealItemUri);
        rewordAppealUri = map.get("rewordAppealUri");
        System.out.println("rewordAppealUri " + rewordAppealUri);
        addImageUri = map.get("addImageUri");
        System.out.println("addImageUri " + addImageUri);
        followUpUri = map.get("followUpUri");
        System.out.println("followUpUri " + followUpUri);
        reviewAppealUri = map.get("reviewAppealUri");
        System.out.println("reviewAppealUri " + reviewAppealUri);
        abandonAppealUri = map.get("abandonAppealUri");
        System.out.println("abandonAppealUri " + abandonAppealUri);
        readAppealUri = map.get("readAppealUri");
        System.out.println("readAppealUri " + readAppealUri);
        System.out.println("\n\nYou can access the readAppealUri while I'm waiting! Press <Enter> to proceed");
        scanner.nextLine();

        //Step - 3 - Start Appeal Review
        System.out.println("*** Step - 3 - Start Appeal Review ***\n");
        appealRepresentation = appealsClientWithBadEntryUri.reviewAppeal(reviewAppealUri);
        if (appealRepresentation == null) {
            System.out.println("Client Terminating...\n\n");
            return;
        }
        map = appealRepresentation.getNextStateUris();
        System.out.println("appealID " + appealID);
        String finishAppealProcessingUri = map.get("finishAppealProcessingUri");
        System.out.println("finishAppealProcessingUri " + finishAppealProcessingUri);
        readAppealUri = map.get("readAppealUri");
        System.out.println("readAppealUri " + readAppealUri);
        System.out.println("\n\nYou can access the readAppealUri while I'm waiting! Press <Enter> to proceed");
        scanner.nextLine();

        //Step - 4 - Finish Appeal Review
        System.out.println("*** Step - 4 - Finish Appeal Review ***\n");
        appealRepresentation = appealsClientWithBadEntryUri.finishAppealProcessing(finishAppealProcessingUri);
        if (appealRepresentation == null) {
            System.out.println("Client Terminating...\n\n");
            return;
        }
        map = appealRepresentation.getNextStateUris();
        System.out.println("appealID " + appealID);
        readAppealUri = map.get("readAppealUri");
        System.out.println("readAppealUri " + readAppealUri);
        System.out.println("\n\nBad Start Simulator Completed!\n\n");

    }

    public static void badIDSimulator() {
        System.out.println("\n\nBAD ID CASE SIMULATOR\n\n");
        Scanner scanner = new Scanner(System.in);

        //Step - 1 - CREATE an Appeal
        System.out.println("*** Step - 1 - CREATE an Appeal ***\n");
        String xmlRequest = "<appeal>\n"
                + "<studentID>10</studentID>\n"
                + "<gradingItemID>10</gradingItemID>\n"
                + "<appealMessage>Hey there</appealMessage>\n"
                + "</appeal>";
        AppealRepresentation appealRepresentation = appealsClient.createAppeal(xmlRequest);
        if (appealRepresentation == null) {
            System.out.println("Client Terminating...\n\n");
            return;
        }
        Map<String, String> map = appealRepresentation.getNextStateUris();
        int appealID = appealRepresentation.getAppealID();
        System.out.println("appealID " + appealID);
        String addAppealItemUri = map.get("addAppealItemUri");
        System.out.println("addAppealItemUri " + addAppealItemUri);
        String rewordAppealUri = map.get("rewordAppealUri");
        System.out.println("rewordAppealUri " + rewordAppealUri);
        String addImageUri = map.get("addImageUri");
        System.out.println("addImageUri " + addImageUri);
        String followUpUri = map.get("followUpUri");
        System.out.println("followUpUri " + followUpUri);
        String reviewAppealUri = map.get("reviewAppealUri");
        System.out.println("reviewAppealUri " + reviewAppealUri);
        String abandonAppealUri = map.get("abandonAppealUri");
        System.out.println("abandonAppealUri " + abandonAppealUri);
        String readAppealUri = map.get("readAppealUri");
        System.out.println("readAppealUri " + readAppealUri);
        System.out.println("\n\nYou can access the readAppealUri while I'm waiting! Press <Enter> to proceed");
        scanner.nextLine();

        System.out.println("\nA Week goes by... No response from the Instructor! Let's follow up on the appeal. Press <Enter> to proceed\n");
        scanner.nextLine();

        System.out.println("\nOh. Looks like we have lost the appeal ID! Let us just try with a random one..\n");
        scanner.nextLine();

        //Step - 2 - FOLLOW UP on the Appeal
        System.out.println("*** Step - 2 - FOLLOW UP on the Appeal ***\n");
        xmlRequest = "<appeal>\n"
                + "<followUp>Hello. It has been long. What is happeneing?</followUp>\n"
                + "</appeal>";
        //Appending '9' to followUpUri will change the ID that appears at the end of the uri. 
        appealRepresentation = appealsClient.followUp(followUpUri + "9", xmlRequest);
        if (appealRepresentation == null) {
            System.out.println("Client Terminating...\n\n");
            return;
        }
        map = appealRepresentation.getNextStateUris();
        System.out.println("appealID " + appealID);
        addAppealItemUri = map.get("addAppealItemUri");
        System.out.println("addAppealItemUri " + addAppealItemUri);
        rewordAppealUri = map.get("rewordAppealUri");
        System.out.println("rewordAppealUri " + rewordAppealUri);
        addImageUri = map.get("addImageUri");
        System.out.println("addImageUri " + addImageUri);
        reviewAppealUri = map.get("reviewAppealUri");
        followUpUri = map.get("followUpUri");
        System.out.println("followUpUri " + followUpUri);
        System.out.println("reviewAppealUri " + reviewAppealUri);
        abandonAppealUri = map.get("abandonAppealUri");
        System.out.println("abandonAppealUri " + abandonAppealUri);
        readAppealUri = map.get("readAppealUri");
        System.out.println("readAppealUri " + readAppealUri);
        System.out.println("\n\nYou can access the readAppealUri while I'm waiting! Press <Enter> to proceed");
        scanner.nextLine();

        System.out.println("\nNow the instructor notices the appeal. And acts on it. Press <Enter> to proceed\n");
        scanner.nextLine();

        //Step - 3 - Start Appeal Review
        System.out.println("*** Step - 3 - Start Appeal Review ***\n");
        appealRepresentation = appealsClient.reviewAppeal(reviewAppealUri);
        if (appealRepresentation == null) {
            System.out.println("Client Terminating...\n\n");
            return;
        }
        map = appealRepresentation.getNextStateUris();
        System.out.println("appealID " + appealID);
        String finishAppealProcessingUri = map.get("finishAppealProcessingUri");
        System.out.println("finishAppealProcessingUri " + finishAppealProcessingUri);
        readAppealUri = map.get("readAppealUri");
        System.out.println("readAppealUri " + readAppealUri);
        System.out.println("\n\nYou can access the readAppealUri while I'm waiting! Press <Enter> to proceed");
        scanner.nextLine();

        //Step - 4 - Finish Appeal Review
        System.out.println("*** Step - 4 - Finish Appeal Review ***\n");
        appealRepresentation = appealsClient.finishAppealProcessing(finishAppealProcessingUri);
        if (appealRepresentation == null) {
            System.out.println("Client Terminating...\n\n");
            return;
        }
        map = appealRepresentation.getNextStateUris();
        System.out.println("appealID " + appealID);
        readAppealUri = map.get("readAppealUri");
        System.out.println("readAppealUri " + readAppealUri);
        System.out.println("\n\nBad ID Case Simulator Completed!\n\n");
    }
}
