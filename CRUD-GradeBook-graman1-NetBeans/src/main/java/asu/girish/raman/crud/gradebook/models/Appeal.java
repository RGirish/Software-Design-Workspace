package asu.girish.raman.crud.gradebook.models;

public class Appeal {

    int appealId;
    int studentId, gradingItemId;
    String appealMessage;

    public int getAppealId() {
        return appealId;
    }

    public void setAppealId(int appealId) {
        this.appealId = appealId;
    }

    public Appeal(int studentId, int gradingItemId, String appealMessage) {
        this.studentId = studentId;
        this.gradingItemId = gradingItemId;
        this.appealMessage = appealMessage;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getGradingItemId() {
        return gradingItemId;
    }

    public void setGradingItemId(int gradingItemId) {
        this.gradingItemId = gradingItemId;
    }

    public String getAppealMessage() {
        return appealMessage;
    }

    public void setAppealMessage(String appealMessage) {
        this.appealMessage = appealMessage;
    }
}
