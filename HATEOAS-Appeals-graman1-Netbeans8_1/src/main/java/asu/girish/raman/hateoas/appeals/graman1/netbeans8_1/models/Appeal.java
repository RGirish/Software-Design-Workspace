/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.models;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Girish
 */
public class Appeal {

    int appealID, studentID, gradingItemID;
    String appealMessage;
    List<String> images = new LinkedList<>();
    List<String> appealItems = new LinkedList<>();
    List<String> followUps = new LinkedList<>();
    private AppealStatus appealStatus = AppealStatus.SUBMITTED;

    public Appeal(int studentID, int gradingItemID, String appealMessage) {
        this.studentID = studentID;
        this.gradingItemID = gradingItemID;
        this.appealMessage = appealMessage;
        this.images = new LinkedList<>();
        this.appealItems = new LinkedList<>();
    }

    public Appeal() {
    }

    public List<String> getFollowUps() {
        return followUps;
    }

    public void setFollowUps(List<String> followUps) {
        this.followUps = followUps;
    }

    public List<String> getAppealItems() {
        return appealItems;
    }

    public void setAppealItems(List<String> appealItems) {
        this.appealItems = appealItems;
    }

    public AppealStatus getAppealStatus() {
        return appealStatus;
    }

    public void setAppealStatus(AppealStatus appealStatus) {
        this.appealStatus = appealStatus;
    }

    public int getAppealID() {
        return appealID;
    }

    public void setAppealID(int appealID) {
        this.appealID = appealID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getGradingItemID() {
        return gradingItemID;
    }

    public void setGradingItemID(int gradingItemID) {
        this.gradingItemID = gradingItemID;
    }

    public String getAppealMessage() {
        return appealMessage;
    }

    public void setAppealMessage(String appealMessage) {
        this.appealMessage = appealMessage;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
