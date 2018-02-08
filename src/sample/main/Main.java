package sample.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample/main/sample.fxml"));
        primaryStage.setTitle("Tuner");
        Scene scene = new Scene(root, 970, 518);
        scene.getStylesheets().add("sample/css/main.css");
        primaryStage.setScene(scene);
        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
