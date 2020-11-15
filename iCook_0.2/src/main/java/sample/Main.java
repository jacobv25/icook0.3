package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.model.HandlerCheckBox;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login_menu.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setTitle("iCook");
        //primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
