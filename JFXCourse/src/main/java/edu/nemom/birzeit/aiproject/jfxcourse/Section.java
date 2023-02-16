package edu.nemom.birzeit.aiproject.jfxcourse;

import edu.nemom.birzeit.aiproject.jfxcourse.Models.Course;
import edu.nemom.birzeit.aiproject.jfxcourse.Models.MeetingTime;

import java.util.ArrayList;
import java.util.Random;

public class Section {
    private String instName;
    private int sectionNum;
    private double fitness;
    private MeetingTime meetingTime;

    public Section(String instName, int sectionNum, MeetingTime meetingTime) {
        this.instName = instName;
        this.sectionNum = sectionNum;
        this.meetingTime = meetingTime;
    }

    public MeetingTime getMeetingTime() {
        return meetingTime;
    }

    public void setMeetingTime(MeetingTime meetingTime) {
        this.meetingTime = meetingTime;
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

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    @Override
    public String toString() {
        return "Section{" +
                "instName='" + instName + '\'' +
                ", SectionNumber=" + sectionNum +
                ", meetingTime=" + meetingTime +
                //", Fitness=" + fitness +
                '}' + "\n";
    }

    public double fitnessFunc(Section section, Course course) {
        double fitness = 0;
        Random r = new Random();
        double x = r.nextDouble(20);
        double y = r.nextDouble(5);
        double secN = section.getSectionNum();
        double couS = course.getNumOfSections();
        int couH = course.getCourseHour();
        if (couH == 3) {
            fitness = (1 / secN + y / couS + x / 12.5) / 3;
            section.setFitness(fitness);
        } else {
            fitness = (1 / secN + y / couS + x / 15) / 3;
            section.setFitness(fitness);
        }
        return fitness;
    }

    public void randTimeFunc(ArrayList<Section> secArr, ArrayList<Course> course, int i, int j) {
        Random random = new Random();
        if (secArr.get(j).instName == course.get(i).getSections().get(j).getInstName()) {
            if (secArr.get(j).getMeetingTime() == course.get(i).getSections().get(j).getMeetingTime()
                    || secArr.get(j).getMeetingTime().getDay().charAt(0) == course.get(i).getSections().get(j).getMeetingTime().getDay().charAt(0)
                    || secArr.get(j).getMeetingTime().getDay().charAt(0) == course.get(i).getSections().get(j).getMeetingTime().getDay().charAt(1)
                    || secArr.get(j).getMeetingTime().getDay().charAt(1) == course.get(i).getSections().get(j).getMeetingTime().getDay().charAt(0)
                    || secArr.get(j).getMeetingTime().getDay().charAt(1) == course.get(i).getSections().get(j).getMeetingTime().getDay().charAt(1)) {
                MeetingTime meeting;
                Section newSec;
                String[] day1 = {"S", "M", "T", "W", "R"};
                String[] timeSlot1 = {"8:00=>10:40", "11:25=>2:05", "2:15=>4:55"};

                String[] day3 = {"SM", "SW", "MW", "TR"};
                String[] timeSlot3 = {"8:30=>9:45", "10:00=>11:15", "11:25=>12:40", "12:50=>2:05", "2:15=>3:30"};

                int d3 = random.nextInt(4);
                int t3 = random.nextInt(5);

                int d1 = random.nextInt(5);
                int t1 = random.nextInt(3);

                if (course.get(i).getCourseHour() == 3) {
                    meeting = new MeetingTime(day3[d3], timeSlot3[t3]);
                    secArr.get(j).setMeetingTime(meeting);
                    newSec = new Section(secArr.get(j).getInstName(), secArr.get(j).getSectionNum(), meeting);
                    newSec.fitnessFunc(newSec, course.get(i));
                    secArr.set(j, newSec);
                } else if (course.get(i).getCourseHour() == 1) {
                    meeting = new MeetingTime(day1[d1], timeSlot1[t1]);
                    secArr.get(j).setMeetingTime(meeting);
                    newSec = new Section(secArr.get(j).getInstName(), secArr.get(j).getSectionNum(), meeting);
                    newSec.fitnessFunc(newSec, course.get(i));
                    secArr.set(j, newSec);
                }
            }
        }
    }
}

