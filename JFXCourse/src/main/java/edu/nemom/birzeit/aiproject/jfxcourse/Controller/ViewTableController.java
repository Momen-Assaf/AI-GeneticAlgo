package edu.nemom.birzeit.aiproject.jfxcourse.Controller;

import edu.nemom.birzeit.aiproject.jfxcourse.Models.Course;
import edu.nemom.birzeit.aiproject.jfxcourse.Models.Data;
import edu.nemom.birzeit.aiproject.jfxcourse.Models.MeetingTime;
import edu.nemom.birzeit.aiproject.jfxcourse.Section;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.ResourceBundle;

public class ViewTableController implements Initializable {

    int calculateCounter = 1;
    @FXML
    private TableView<Data> cTable = new TableView<>();

    @FXML
    private TableColumn<Data, String> cCourseID;

    @FXML
    private TableColumn<Data, String> cSectionInst;

    @FXML
    private TableColumn<Data, Integer> cSectionNum;

    @FXML
    private TableColumn<Data, String> cTimeSlot;

    @FXML
    private TableColumn<Data, String> cDay;

    @FXML
    private TableColumn<Data, Integer> cYear;

    private ObservableList<Data> oC = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //oC = FXCollections.observableArrayList(new Data("xx",1,"sdas",2,"05","d"));
        cCourseID.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        cSectionInst.setCellValueFactory(new PropertyValueFactory<>("instName"));
        cSectionNum.setCellValueFactory(new PropertyValueFactory<>("sectionNum"));
        cTimeSlot.setCellValueFactory(new PropertyValueFactory<>("timeSlot"));
        cDay.setCellValueFactory(new PropertyValueFactory<>("day"));
        cYear.setCellValueFactory(new PropertyValueFactory<>("courseYear"));
        //cTable.setItems(oC);
    }

    @FXML
    void calculateGenetic(ActionEvent event) {
        if( calculateCounter == 1) {
            for (int i = 2; i < 6; i++) {
                population(i);
            }
            cTable.setItems(oC);
            System.out.println(cTable.getItems());
            calculateCounter++;
        }
        else{
            oC.clear();
            cTable.getItems().clear();
            for (int i = 2; i < 6; i++) {
                population(i);
            }
            cTable.setItems(oC);
            System.out.println(cTable.getItems());
            calculateCounter++;
        }
    }

    public void population(int y) {
        ArrayList<Course> course = MainController.yearsMap.get(y);
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
            Course cour = course.get(i);
            Section[] sec = new Section[secArr.size()];

            for (int j = 0; j < secArr.size(); j++) {
                sec[j] = secArr.get(j);
            }
            for (int j = 0; j < secArr.size();j++) {
                for (int k = 0; k < secArr.size(); k++) {
                    secArr.get(k).randTimeFunc(secArr, course, i, j);
                }
            }
            for (int j = 0; j < sec.length; j++) {
                oC.add(new Data(cour.getCourseID()
                        , cour.getCourseYear()
                        , sec[j].getInstName()
                        , sec[j].getSectionNum()
                        , sec[j].getMeetingTime().getTimeSlot()
                        , sec[j].getMeetingTime().getDay()));
            }

        }
    }

}