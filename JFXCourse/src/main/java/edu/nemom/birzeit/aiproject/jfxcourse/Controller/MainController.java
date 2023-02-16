package edu.nemom.birzeit.aiproject.jfxcourse.Controller;

import edu.nemom.birzeit.aiproject.jfxcourse.HelloApplication;
import edu.nemom.birzeit.aiproject.jfxcourse.Models.Course;
import edu.nemom.birzeit.aiproject.jfxcourse.Models.MeetingTime;
import edu.nemom.birzeit.aiproject.jfxcourse.Section;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MainController implements Initializable {
    public static HashMap<Integer, ArrayList<Course>> yearsMap;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        yearsMap = new HashMap<>();

        System.out.println("-----------------Welcome to our Project!!-----------------");
        System.out.println("This project was done by:\nMomen Assaf-1191529\nMahmoud Abu Sharkh-1200953\nAnas Sarabta-1200242");

        //initalize map
        for (int i = 2; i < 6; i++) {
            yearsMap.put(i, new ArrayList<>());
        }
    }

    @FXML
    void readCourseFile(ActionEvent event) throws IOException {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Files", "*.txt"));
        try {
            File myfile = chooser.showOpenDialog(null);

            if (myfile != null) {
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

                        for (int j = 0; j < s; j++) {
                            ;

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
                            } else {
                                System.out.println("INVALID DATA");
                            }
                        }
                    }
                    newCourse.setSections(sectionList);
                    yearsMap.get(cYear).add(newCourse);
                }
                myReader.close();
            }
            loadStage(event);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    void loadStage(ActionEvent event) throws IOException {
        Stage stage= new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tableView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 917 , 621);
        stage.setTitle("Genetic Algorithm Table");
        stage.setScene(scene);
        stage.show();
        Node node = (Node) event.getSource();
        Stage currStage = (Stage) node.getScene().getWindow();
        currStage.close();
    }
}