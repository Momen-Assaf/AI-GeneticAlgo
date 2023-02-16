package edu.nemom.birzeit.aiproject.jfxcourse.Models;

public class Data {
    private String courseID;
    private int courseYear;
    private String instName;
    private int sectionNum;
    private String timeSlot;
    private String day;

    public Data(String courseID, int courseYear, String instName, int sectionNum, String timeSlot, String day) {
        this.courseID = courseID;
        this.courseYear = courseYear;
        this.instName = instName;
        this.sectionNum = sectionNum;
        this.timeSlot = timeSlot;
        this.day = day;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public int getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(int courseYear) {
        this.courseYear = courseYear;
    }

    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
    }

    public int getSectionNum() {
        return sectionNum;
    }

    public void setSectionNum(int sectionNum) {
        this.sectionNum = sectionNum;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Data{" +
                "courseID='" + courseID + '\'' +
                ", courseYear=" + courseYear +
                ", instName='" + instName + '\'' +
                ", sectionNum=" + sectionNum +
                ", timeSlot='" + timeSlot + '\'' +
                ", day='" + day + '\'' +
                '}';
    }
}
