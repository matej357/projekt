package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {

        //Vypísanie hlášky zapnutia do konzoly
        System.out.println("- Macrohard successfully started");

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        //Definovanie základného FXML súboru a rozmery scény
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setTitle("MacroHard");
        primaryStage.setResizable(false);
        primaryStage.show();

        //Vypísanie hlášky o zobrazovaní
        System.out.println("- Showing 'Login' window");

    }
}
