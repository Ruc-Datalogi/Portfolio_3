package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        //launch(args);
        JDBC jdbc = new JDBC("jdbc:sqlite:C:/Users/marti/Desktop/Portfolio_3/Data/Database.db");
        jdbc.connect();
        ArrayList<Student> students = new ArrayList<>();
        students.addAll(jdbc.createStudents());
        for (Student s : students){
            System.out.println(s);
        }
        jdbc.getGrades(students);
        System.out.println(jdbc.averageGradeStudent(1 ));

        jdbc.close();
    }
}
