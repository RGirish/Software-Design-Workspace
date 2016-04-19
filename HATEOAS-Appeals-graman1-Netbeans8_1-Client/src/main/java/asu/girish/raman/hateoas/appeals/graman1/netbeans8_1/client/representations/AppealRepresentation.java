/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.client.representations;

import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.client.models.Appeal;
import asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.client.models.AppealStatus;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Girish
 */
@XmlRootElement(name = "appeal")
public class AppealRepresentation {

    private int appealID;
    private int studentID;
    private int gradingItemID;
    private String appealMessage;
    private List<String> images;
    private List<String> followUps;
    private List<String> appealItems;
    private AppealStatus appealStatus;
    private Map<String, String> nextStateUris;

    public AppealRepresentation(Appeal appeal, Map<String, String> appealsUris) {
        this.appealID = appeal.getAppealID();
        this.studentID = appeal.getStudentID();
        this.gradingItemID = appeal.getGradingItemID();
        this.appealMessage = appeal.getAppealMessage();
        this.images = appeal.getImages();
        this.followUps = appeal.getFollowUps();
        this.appealItems = appeal.getAppealItems();
        this.appealStatus = appeal.getAppealStatus();
        this.nextStateUris = appealsUris;
    }

    AppealRepresentation(Appeal appeal) {

    }

    AppealRepresentation() {

    }

    @XmlElement(name = "followUp")
    public List<String> getFollowUps() {
        return followUps;
    }

    public void setFollowUps(List<String> followUps) {
        this.followUps = followUps;
    }

    @XmlElement(name = "appealID")
    public int getAppealID() {
        return appealID;
    }

    public void setAppealID(int appealID) {
        this.appealID = appealID;
    }

    @XmlElement(name = "studentID")
    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    @XmlElement(name = "gradingItemID")
    public int getGradingItemID() {
        return gradingItemID;
    }

    public void setGradingItemID(int gradingItemID) {
        this.gradingItemID = gradingItemID;
    }

    @XmlElement(name = "appealMessage")
    public String getAppealMessage() {
        return appealMessage;
    }

    public void setAppealMessage(String appealMessage) {
        this.appealMessage = appealMessage;
    }

    @XmlElement(name = "image")
    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @XmlElement(name = "appealItem")
    public List<String> getAppealItems() {
        return appealItems;
    }

    public void setAppealItems(List<String> appealItems) {
        this.appealItems = appealItems;
    }

    @XmlElement(name = "appealStatus")
    public AppealStatus getAppealStatus() {
        return appealStatus;
    }

    public void setAppealStatus(AppealStatus appealStatus) {
        this.appealStatus = appealStatus;
    }

    @XmlJavaTypeAdapter(NextStateURIMapperAdapter.class)
    public Map<String, String> getNextStateUris() {
        return nextStateUris;
    }

    public void setNextStateUris(Map<String, String> nextStateUris) {
        this.nextStateUris = nextStateUris;
    }

    @Override
    public String toString() {
        try {
            JAXBContext context = JAXBContext.newInstance(AppealRepresentation.class);
            Marshaller marshaller = context.createMarshaller();
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(this, stringWriter);
            return stringWriter.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
