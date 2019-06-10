package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.MusicLibrary;
import view.ViewModel;

import java.io.IOException;


public class Main extends Application {
//    private static ConsoleController controller = new ConsoleController(new ViewModel(), new MusicLibrary());
//
//    public static void main(String[] args) {
//        controller.launch();
//    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent panel = FXMLLoader.load(getClass().getResource("../view/main.fxml"));
        Scene scene = new Scene(panel, 600, 400);
        primaryStage.setTitle("Main");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
