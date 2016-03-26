package asu.girish.raman.crud.gradebook.models;

import java.util.Map;

public class Student {

    String name;
    int id;
    Map<Integer, Double> points;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<Integer, Double> getPoints() {
        return points;
    }

    public void setPoints(Map<Integer, Double> points) {
        this.points = points;
    }
}
