/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.client.main;

import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.client.clients.AppealsClient;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.client.representations.AppealRepresentation;
import java.util.Map;

/**
 *
 * @author Girish
 */
public class Main {

    static AppealsClient appealsClient;

    public static void main(String[] args) {
        appealsClient = new AppealsClient();
        happyCaseSimulator();
    }

    public static void happyCaseSimulator() {
        System.out.println("\n\nHAPPY CASE SIMULATOR\n\n");

        //Step - 1 - CREATE an Appeal
        String xmlRequest = "<appeal>\n"
                + "<studentID>10</studentID>\n"
                + "<gradingItemID>10</gradingItemID>\n"
                + "<appealMessage>Hey there</appealMessage>\n"
                + "</appeal>";
        AppealRepresentation appealRepresentation = appealsClient.createAppeal(xmlRequest);
        Map<String, String> map = appealRepresentation.getNextStateUris();
        int appealID = appealRepresentation.getAppealID();
        System.out.println("*** Step - 1 - CREATE an Appeal ***\n");
        System.out.println("appealID " + appealID);
        String addAppealItemUri = map.get("addAppealItemUri");
        System.out.println("addAppealItemUri " + addAppealItemUri);
        String rewordAppealUri = map.get("rewordAppealUri");
        System.out.println("rewordAppealUri " + rewordAppealUri);
        String addImageUri = map.get("addImageUri");
        System.out.println("addImageUri " + addImageUri);
        String reviewAppealUri = map.get("reviewAppealUri");
        System.out.println("reviewAppealUri " + reviewAppealUri);
        String abandonAppealUri = map.get("abandonAppealUri");
        System.out.println("abandonAppealUri " + abandonAppealUri);
        String readAppealUri = map.get("readAppealUri");
        System.out.println("readAppealUri " + readAppealUri);
        System.out.println("\n\n");

        //Step - 2 - REWORD the Appeal (Update)
        xmlRequest = "<appeal>\n"
                + "<appealMessage>Hey there - updated</appealMessage>\n"
                + "</appeal>";
        appealRepresentation = appealsClient.rewordAppeal(rewordAppealUri, xmlRequest);
        map = appealRepresentation.getNextStateUris();
        System.out.println("*** Step - 2 - REWORD the Appeal (Update) ***\n");
        System.out.println("appealID " + appealID);
        addAppealItemUri = map.get("addAppealItemUri");
        System.out.println("addAppealItemUri " + addAppealItemUri);
        rewordAppealUri = map.get("rewordAppealUri");
        System.out.println("rewordAppealUri " + rewordAppealUri);
        addImageUri = map.get("addImageUri");
        System.out.println("addImageUri " + addImageUri);
        reviewAppealUri = map.get("reviewAppealUri");
        System.out.println("reviewAppealUri " + reviewAppealUri);
        abandonAppealUri = map.get("abandonAppealUri");
        System.out.println("abandonAppealUri " + abandonAppealUri);
        readAppealUri = map.get("readAppealUri");
        System.out.println("readAppealUri " + readAppealUri);
        System.out.println("\n\n");

        //Step - 3 - Start Appeal Review
        appealRepresentation = appealsClient.reviewAppeal(reviewAppealUri);
        map = appealRepresentation.getNextStateUris();
        System.out.println("*** Step - 3 - Start Appeal Review ***\n");
        System.out.println("appealID " + appealID);
        String finishAppealProcessingUri = map.get("finishAppealProcessingUri");
        System.out.println("finishAppealProcessingUri " + finishAppealProcessingUri);
        readAppealUri = map.get("readAppealUri");
        System.out.println("readAppealUri " + readAppealUri);
        System.out.println("\n\n");

        //Step - 4 - Finish Appeal Review
        appealRepresentation = appealsClient.finishAppealProcessing(finishAppealProcessingUri);
        map = appealRepresentation.getNextStateUris();
        System.out.println("*** Step - 4 - Finish Appeal Review ***\n");
        System.out.println("appealID " + appealID);
        readAppealUri = map.get("readAppealUri");
        System.out.println("readAppealUri " + readAppealUri);
        System.out.println("\n\n");
    }

    public void abandonedCaseSimulator() {

    }

    public void forgottenCaseSimulator() {

    }

    public void badStartSimulator() {

    }

    public void badIDSimulator() {

    }
}
