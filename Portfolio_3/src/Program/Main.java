package Program;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        JDBC jdbc = new JDBC("jdbc:sqlite:C:/Users/marti/Desktop/Portfolio_3/Data/Database.db");
        ApplicationController applicationController = new ApplicationController(jdbc);
        ApplicationView applicationView = new ApplicationView(applicationController);
        applicationController.setApplicationView(applicationView);
        primaryStage.setTitle("Student Database Viewer");
        primaryStage.setScene(new Scene(applicationView.asParent(), 600, 420));
        primaryStage.setResizable(false);
        primaryStage.show();
    }




    public static void main(String[] args) {
        launch(args);
    }

}
