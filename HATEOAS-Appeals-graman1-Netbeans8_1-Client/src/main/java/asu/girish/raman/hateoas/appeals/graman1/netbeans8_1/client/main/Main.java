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
        String xmlRequest = "<appeal>\n"
                + "<studentID>10</studentID>\n"
                + "<gradingItemID>10</gradingItemID>\n"
                + "<appealMessage>Hey there</appealMessage>\n"
                + "</appeal>";
        AppealRepresentation appealRepresentation = appealsClient.createAppeal(xmlRequest);
        Map<String, String> map = appealRepresentation.getNextStateUris();
        int appealID = appealRepresentation.getAppealID();
        System.out.println("appealID " + appealID);
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
