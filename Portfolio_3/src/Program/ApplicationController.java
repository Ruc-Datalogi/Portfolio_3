package Program;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class ApplicationController {
    private JDBC jdbc;
    private ApplicationView applicationView;
    private ArrayList<Student> allStudents = new ArrayList<>();


    public ApplicationController(JDBC jdbc) {
        this.jdbc = jdbc;
        jdbc.connect();
        allStudents.addAll(jdbc.createStudents());
        jdbc.setGrades(allStudents);
    }

    public void setApplicationView(ApplicationView view) {
        this.applicationView = view;
        view.exitBtn.setOnAction(e -> Platform.exit());
        EventHandler<ActionEvent> studentInfo = e -> studentInfo(view.studentNames.getValue(), view.outputText);
        EventHandler<ActionEvent> gradeInfo = e -> gradeAverageInfo(view.courseNames.getValue(), view.outputText);

        view.printInfoBtn.setOnAction(studentInfo);
        view.avgGradeBtn.setOnAction(gradeInfo);
    }

    public ObservableList<String> getNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Student s : allStudents) {
            names.add(s.getName());
        }
        ObservableList<String> output = FXCollections.observableArrayList(names);

        return output;
    }

    public void studentInfo(String name, TextArea textArea) {
        String forTextArea = "";
        textArea.clear();

        // Find the correct student
        for (Student s : allStudents) {
            if (s.getName().equals(name)) {

                forTextArea = s.toString() + s.coursesAndGrades() + "\n" + "Average grade: " + jdbc.averageGradeStudent(s.getStudentID());
            }
        }
        textArea.appendText(forTextArea);
    }



    public void gradeAverageInfo(String course, TextArea textArea){
        textArea.clear();
        String forTextArea;

        forTextArea = "Average grade for the course " + course +" : " + String.valueOf(jdbc.averageGradeCourse(course));

        textArea.appendText(forTextArea);
    }

    public ObservableList<String> getCourses(){
        ObservableList<String> output = FXCollections.observableArrayList(jdbc.allCourses());
        return output;
    }
}
