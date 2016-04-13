/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.representations;

import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.events.CreateAppealEvent;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.models.Appeal;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.models.AppealStatus;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

/**
 *
 * @author Girish
 */
public class AppealRepresentation {

    public static final String APPEALS_MEDIA_TYPE = "application/vnd-cse564-appeals+json";
    private int appealID, studentID, gradingItemID;
    private String appealMessage;
    private List<String> images;
    private List<String> appealItems;
    private AppealStatus appealStatus;
    private List<String> nextStateUris;

    public AppealRepresentation(Appeal appeal, String... appealsUris) {
        this.appealID = appeal.getAppealID();
        this.studentID = appeal.getStudentID();
        this.gradingItemID = appeal.getGradingItemID();
        this.appealMessage = appeal.getAppealMessage();
        this.images = appeal.getImages();
        this.appealItems = appeal.getAppealItems();
        this.appealStatus = appeal.getAppealStatus();
        this.nextStateUris = Arrays.asList(appealsUris);
    }

    public static AppealRepresentation createAppealJsonRequestToObject(String jsonString) {
        JSONObject root = new JSONObject(jsonString);
        Appeal appeal = new Appeal(root.getInt("studentID"), root.getInt("gradingItemID"), root.getString("appealMessage"));
        return new CreateAppealEvent().createAppeal(appeal);
    }

}
