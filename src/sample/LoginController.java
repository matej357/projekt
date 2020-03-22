package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {

    public PasswordField passField;
    public TextField nickField = null;

    ConnectionClass cn = ConnectionClass.getInstance();
    Connection connection = cn.getConnection();

    public LoginController() throws FileNotFoundException {
    }

    public void login(ActionEvent actionEvent) throws IOException, SQLException {

        //Vymazanie TXT sokumentu s ID používateľa pri každom prihlásení (aby tam stále bolo iba jedno ID)
        File userIdFile = new File("C:\\Users\\Matej\\Desktop\\try\\user_id.txt");

        if (userIdFile.delete()) {
        }

        String sqlLogin = "SELECT * FROM USER WHERE NICK = '" + nickField.getText() + "' and pass = '" + passField.getText() + "';";
        String sqlIdSel = "SELECT id FROM USER WHERE NICK = '" + nickField.getText() + "';";

        try {

            Statement loginSt = connection.createStatement();
            ResultSet loginRs = loginSt.executeQuery(sqlLogin);

            Statement idSelSt = connection.createStatement();
            ResultSet idSelRs = idSelSt.executeQuery(sqlIdSel);

            if (idSelRs.next()) {

                String user_id = (idSelRs.getString(1));

                //Zápis do TXT dokumentu
                try (BufferedWriter idWrite = new BufferedWriter(new FileWriter("user_id.txt", true))) {
                    idWrite.write(user_id);
                    idWrite.newLine();
                }

            }

            if (loginRs.next()) {

                System.out.println("- Login succesful");

                Parent userInfo = FXMLLoader.load(getClass().getResource("menu.fxml"));
                Scene sceneUser = new Scene(userInfo);
                Stage userWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                userWindow.setScene(sceneUser);
                userWindow.show();

            } else {
                System.out.println("- Nick or Password incorrect");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void register(ActionEvent actionEvent) throws IOException {

        Parent userInfo = FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene sceneUser = new Scene(userInfo);
        Stage userWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        userWindow.setScene(sceneUser);
        userWindow.show();

        System.out.println("- Showing 'Register' window");

    }

    public void exitApp(ActionEvent actionEvent) {

        //Ak užívateľ klikne na tlačítko Exit tak sa aplikácia vypne
        System.out.println("- Shutting off the Macrohard");
        System.exit(0);

    }
}













