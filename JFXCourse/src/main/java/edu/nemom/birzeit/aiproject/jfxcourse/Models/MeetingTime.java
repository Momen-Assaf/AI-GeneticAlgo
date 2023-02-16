package edu.nemom.birzeit.aiproject.jfxcourse.Models;

public class MeetingTime {
    private String day;
    private String timeSlot;

    public MeetingTime(String day, String timeSlot) {
        this.day = day;
        this.timeSlot = timeSlot;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    @Override
    public String toString() {
        return "MeetingTime{" +
                "days='" + day + '\'' +
                ", timeSlot='" + timeSlot + '\'' +
                '}';
    }
}
