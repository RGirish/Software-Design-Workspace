package asu.girish.raman.crud.gradebook.models;

public class GradingItem {

    String type, name;
    double percentage;
    int id;

    public GradingItem() {
    }

    public int getId() {
        return id;
    }

    public GradingItem(int id, String type, String name, double percentage) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.percentage = percentage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
