package edu.nemom.birzeit.aiproject.jfxcourse.Models;

import edu.nemom.birzeit.aiproject.jfxcourse.Section;

import java.util.ArrayList;

public class Course {

        private String courseID;
        private int courseYear;
        private int courseHour;
        private String courseName;
        private int numOfSections;
        ArrayList<Section> sections;

    public Course(String courseID, int courseYear, int courseHour, String courseName, int numOfSections) {
        this.courseID = courseID;
        this.courseYear = courseYear;
        this.courseHour = courseHour;
        this.courseName = courseName;
        this.numOfSections = numOfSections;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
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

    public int getCourseHour() {
        return courseHour;
    }

    public void setCourseHour(int courseHour) {
        this.courseHour = courseHour;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getNumOfSections() {
        return numOfSections;
    }

    public void setNumOfSections(int numOfSections) {
        this.numOfSections = numOfSections;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID='" + courseID + '\'' +
                ", courseYear=" + courseYear +
                ", courseHour=" + courseHour +
                ", courseName='" + courseName + '\'' +
                ", numOfSections=" + numOfSections +
                "\n ===============[sections]===============\n"+
                sections.toString()+
                "\n===============[End of Sections]==========="+
                '}'+'\n';
    }
}