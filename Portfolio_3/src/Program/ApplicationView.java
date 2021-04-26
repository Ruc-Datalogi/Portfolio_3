package Program;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class ApplicationView {
    private ApplicationController applicationController;
    private GridPane startview;
    Button exitBtn = new Button("Exit");
    Button printInfoBtn = new Button("Print info for student");
    Button avgGradeBtn = new Button("Print average grade for course");
    Label selectStudentLabel = new Label("Select a student");
    Label selectCourseLabel = new Label("Select a course");

    TextArea outputText = new TextArea();

    ComboBox<String> studentNames = new ComboBox<>();
    ComboBox<String> courseNames = new ComboBox<>();
    ArrayList<Student> allStudent = new ArrayList<>();


    public ApplicationView(ApplicationController applicationController){
        this.applicationController = applicationController;
        createAndConfigure();

        // So it's not possible to edit the text area
        outputText.setEditable(false);
    }


    // The GUI for the program
    public void createAndConfigure(){
        startview = new GridPane();
        startview.setMinSize(450,300);
        startview.setPadding(new Insets(10,10,10,10));
        startview.setVgap(3);
        startview.setHgap(3);
        startview.add(exitBtn,5,70);
        startview.add(printInfoBtn,15,8);
        startview.add(avgGradeBtn,15,17);

        startview.add(selectStudentLabel,5,5);
        startview.add(studentNames,5,8);

        startview.add(selectCourseLabel,5,14);
        startview.add(courseNames,5,17);

        startview.add(outputText,5,25,20,40);

        ObservableList<String> students = applicationController.getNames();
        ObservableList<String> courses = applicationController.getCourses();
        studentNames.setItems(students);
        studentNames.getSelectionModel().selectFirst();

        courseNames.setItems(courses);
        courseNames.getSelectionModel().selectFirst();
    }


    public Parent asParent(){
        return startview;
    }


}
