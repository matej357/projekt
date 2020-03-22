package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterController {

    public TextField nickField;
    public PasswordField passField;
    ConnectionClass cn = ConnectionClass.getInstance();
    Connection connection = cn.getConnection();

    public void create(ActionEvent actionEvent) throws IOException {

        try {

            Statement insertSt = this.connection.createStatement();

                //Vytvorenie nového používateľa
                String sqlInsUser = "INSERT INTO user VALUES (null, '" + nickField.getText() + "','" + passField.getText() + "');";
                //Vytorenie ostatných záznamov v tabuľkách, aby bolo zachované jednotné ID
                String sqlInsUser_info = "INSERT INTO user_info VALUES (null, 'unknown genre','unkown artist', 'unknown song');";
                String sqlInsGenre_info = "INSERT INTO genre_info VALUES (null, 'no description due to unknown favorite genre');";
                String sqlInsArtist_info = "INSERT INTO artist_info VALUES (null, 'unknown artist', 0, 'unknown place');";
                String sqlInsSong_info = "INSERT INTO song_info VALUES (null, 'unknown song','00:00', 0);";

                int insUserRs = insertSt.executeUpdate(sqlInsUser);
                int insUser_infoRs = insertSt.executeUpdate(sqlInsUser_info);
                int insGenre_infoRs = insertSt.executeUpdate(sqlInsGenre_info);
                int insArtist_infoRs = insertSt.executeUpdate(sqlInsArtist_info);
                int insSong_infoRs = insertSt.executeUpdate(sqlInsSong_info);

                Parent userInfo = FXMLLoader.load(getClass().getResource("login.fxml"));
                Scene sceneUser = new Scene(userInfo);
                Stage userWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                userWindow.setScene(sceneUser);
                userWindow.show();
                System.out.println("- Showing 'Login' window");

                System.out.println("- Now you can login");
            } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        public void goBack (ActionEvent actionEvent) throws IOException {

            Parent userInfo = FXMLLoader.load(getClass().getResource("login.fxml"));
            Scene sceneUser = new Scene(userInfo);
            Stage userWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            userWindow.setScene(sceneUser);
            userWindow.show();
            System.out.println("- Showing 'Login' window");

        }
    }

