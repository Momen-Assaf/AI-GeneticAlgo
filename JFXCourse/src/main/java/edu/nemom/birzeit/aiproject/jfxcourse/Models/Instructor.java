package edu.nemom.birzeit.aiproject.jfxcourse.Models;

public class Instructor {
    private String instName;
    private int numOfSections;


    public Instructor(String instName, int numOfSections) {
        this.instName = instName;
        this.numOfSections = numOfSections;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
    }

    public int getNumOfSections() {
        return numOfSections;
    }

    public void setNumOfSections(int numOfSections) {
        this.numOfSections = numOfSections;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "instName='" + instName + '\'' +
                ", numOfSections=" + numOfSections +
                '}';
    }
}
