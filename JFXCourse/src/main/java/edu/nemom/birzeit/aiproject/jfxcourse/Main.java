package edu.nemom.birzeit.aiproject.jfxcourse;

import edu.nemom.birzeit.aiproject.jfxcourse.Models.Course;
import edu.nemom.birzeit.aiproject.jfxcourse.Models.MeetingTime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static HashMap<Integer, ArrayList<Course>> yearsMap;

    public static void main(String[] args) {

        yearsMap = new HashMap<>();

        System.out.println("-----------------Welcome to our Project!!-----------------");
        System.out.println("This project was done by:\nMomen Assaf-1191529\nMahmoud Abu Sharkh-1200953\nAnas Sarabta-1200242");

        //initalize map
        for (int i = 2; i < 6; i++) {
            yearsMap.put(i, new ArrayList<>());
        }

        try {
            File myfile = new File("data_1.txt");
            Scanner myReader = new Scanner(myfile);

            while (myReader.hasNextLine()) {

                String[] dataSet = myReader.nextLine().split(";");
                int cYear = Integer.parseInt(Character.toString(dataSet[0].charAt(4)));
                int cHour = Integer.parseInt(Character.toString(dataSet[0].charAt(5)));
                int incSec = 1;

                Course newCourse = new Course(dataSet[0], cYear, cHour, dataSet[1], Integer.parseInt(dataSet[2]));
                ArrayList<Section> sectionList = new ArrayList<>();
                String[] sectionData = dataSet[3].trim().split(",");

                Random rand = new Random();

                String[] day1 = {"S", "M", "T", "W", "R"};
                String[] timeSlot1 = {"8:00=>10:40", "11:25=>2:05", "2:15=>4:55"};

                String[] day3 = {"SM", "SW", "MW", "TR"};
                String[] timeSlot3 = {"8:30=>9:45", "10:00=>11:15", "11:25=>12:40", "12:50=>2:05", "2:15=>3:30"};

                for (int i = 0; i < sectionData.length; i++) {
                    String[] sectionSet = sectionData[i].split(":");
                    int s = Integer.parseInt("" + sectionSet[1].trim().charAt(0));

                    for (int j = 0; j < s; j++) {;

                        int d3 = rand.nextInt(4);
                        int t3 = rand.nextInt(5);

                        int d1 = rand.nextInt(5);
                        int t1 = rand.nextInt(3);

                        if (cHour == 3) {

                            MeetingTime meeting = new MeetingTime(day3[d3], timeSlot3[t3]);
                            Section sec = new Section(sectionSet[0].trim(), incSec, meeting);
                            sec.fitnessFunc(sec, newCourse);
                            sectionList.add(sec);
                            incSec += 1;

                        } else if (cHour == 1) {

                            MeetingTime meeting = new MeetingTime(day1[d1], timeSlot1[t1]);
                            Section sec = new Section(sectionSet[0].trim(), incSec, meeting);
                            sec.fitnessFunc(sec, newCourse);
                            sectionList.add(sec);
                            incSec += 1;
                        }
                        else{
                            System.out.println("INVALID DATA");
                        }
                    }
                }
                newCourse.setSections(sectionList);
                yearsMap.get(cYear).add(newCourse);
            }
            myReader.close();
            //System.out.println(yearsMap.toString());
            for (int i = 2; i < 6; i++) {
                System.out.println("============================" + i + "===================================");
                population(i);
                System.out.println("===============================================================");
            }


        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static void population(int y) {
        ArrayList<Course> course = yearsMap.get(y);
        for (int i = 0; i < course.size(); i++) {
            //System.out.println(course.get(i).getCourseID());
            //System.out.println(course.get(i).getSections().toString());
        }
        Random random = new Random();
        Section crossed;
        Section mutated;
        //Selection:
        double max1 = 0;
        double max2 = 0;

        for (int i = 0; i < course.size(); i++) {
            ArrayList<Section> secArr = course.get(i).getSections();
            MeetingTime meeting;
            if (secArr.size() == 0) {
                System.out.println("List is empty");
            } else if (secArr.size() == 1) {
                crossed = secArr.get(0);
            } else {
                Section cross1 = secArr.get(0);
                Section cross2 = null;
                max1 = cross1.getFitness();
                max2 = 0;
                for (int j = 1; j < secArr.size(); j++) {
                    if (secArr.get(j).getFitness() > max2) {
                        if (secArr.get(j).getFitness() > max1) {
                            max2 = max1;
                            max1 = secArr.get(j).getFitness();
                            cross2 = cross1;
                            cross1 = secArr.get(j);
                        } else {
                            max2 = secArr.get(j).getFitness();
                            cross2 = secArr.get(j);
                        }
                    }
                }
                for (int j = 0; j < secArr.size(); j++) {
                    //CrossOver
                    double d = random.nextDouble(11);
                    if (d <= 7.5) {
                        int x = random.nextInt(2);
                        String tempD1;
                        String tempD2;
                        String tempT1;
                        String tempT2;

                        if (x == 1) {
                            tempD1 = cross1.getMeetingTime().getDay();
                            tempT2 = cross2.getMeetingTime().getTimeSlot();
                            meeting = new MeetingTime(tempD1, tempT2);
                            crossed = new Section(secArr.get(j).getInstName(), secArr.get(j).getSectionNum(), meeting);
                            crossed.fitnessFunc(crossed, course.get(i));
                            secArr.set(j, crossed);
                        } else {
                            tempT1 = cross1.getMeetingTime().getTimeSlot();
                            tempD2 = cross2.getMeetingTime().getDay();
                            meeting = new MeetingTime(tempD2, tempT1);
                            crossed = new Section(secArr.get(j).getInstName(), secArr.get(j).getSectionNum(), meeting);
                            crossed.fitnessFunc(crossed, course.get(i));
                            secArr.set(j, crossed);
                        }
                    }
                }
                //MUTATION
                for (int j = 0; j < secArr.size(); j++) {
                    double m = random.nextDouble(11);
                    if (m < 0.5) {
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
                            mutated = new Section(secArr.get(j).getInstName(), secArr.get(j).getSectionNum(), meeting);
                            mutated.fitnessFunc(mutated, course.get(i));
                            secArr.set(j, mutated);
                        } else {
                            meeting = new MeetingTime(day1[d1], timeSlot1[t1]);
                            secArr.get(j).setMeetingTime(meeting);
                            mutated = new Section(secArr.get(j).getInstName(), secArr.get(j).getSectionNum(), meeting);
                            mutated.fitnessFunc(mutated, course.get(i));
                            secArr.set(j, mutated);
                        }
                    }
                }
            }
            for (int j = 0; j < secArr.size(); j++){
                secArr.get(j).randTimeFunc(secArr,course,i,j);
            }
            System.out.println(course.get(i).getCourseID());
            System.out.println(secArr);
        }
    }
}
