package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            BorderPane root = FXMLLoader.load(getClass().getResource("Paint.fxml"));
            Scene scene = new Scene(root, 900, 650);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Paint by Md Sakil Khan");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}