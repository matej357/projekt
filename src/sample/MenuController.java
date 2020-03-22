package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    public TextField newNick;
    public TextField newPass;

    ConnectionClass cn = new ConnectionClass();
    Connection connection;
    ObservableList<UserInfo> observableList;

    @FXML
    TableView<UserInfo> tableView;
    @FXML
    TableColumn<UserInfo, String> id_column;
    @FXML
    TableColumn<UserInfo, String> fgenre_column;
    @FXML
    TableColumn<UserInfo, String> fartist_column;
    @FXML
    TableColumn<UserInfo, String> fsong_column;


    public MenuController() throws IOException {

        this.connection = this.cn.getConnection();
        this.observableList = FXCollections.observableArrayList();
        this.tableView = new TableView();

    }

    public void initialize(URL location, ResourceBundle resources) {

        this.id_column.setCellValueFactory(new PropertyValueFactory("id"));
        this.fgenre_column.setCellValueFactory(new PropertyValueFactory("fgenre"));
        this.fartist_column.setCellValueFactory(new PropertyValueFactory("fartist"));
        this.fsong_column.setCellValueFactory(new PropertyValueFactory("fsong"));

        String userId = null;

        try {
            userId = new String(Files.readAllBytes(Paths.get("user_id.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }


        String sql = "SELECT * FROM user_info where id = " + userId + ";";

        try {

            Statement user_infoSt = this.connection.createStatement();
            this.id_column = new TableColumn("id");
            this.fgenre_column = new TableColumn("fgenre");
            this.fartist_column = new TableColumn("fartist");
            this.fsong_column = new TableColumn("fsong");
            ResultSet user_infoRs = user_infoSt.executeQuery(sql);

            while (user_infoRs.next()) {
                int id = user_infoRs.getInt("id");
                String fgenre = user_infoRs.getString("fgenre");
                String fartist = user_infoRs.getString("fartist");
                String fsong = user_infoRs.getString("fsong");
                this.observableList.add(new UserInfo(id, fgenre, fartist, fsong));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.tableView.setItems(this.observableList);
        System.out.println("- Showing user data and choices in window");
    }

    public void showSong(ActionEvent actionEvent) throws IOException {

        Parent userInfo = FXMLLoader.load(getClass().getResource("songInfo.fxml"));
        Scene sceneUser = new Scene(userInfo);
        Stage userWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        userWindow.setScene(sceneUser);
        userWindow.show();
        System.out.println("- Showing song info table");

    }

    public void showArtist(ActionEvent actionEvent) throws IOException {

        Parent userInfo = FXMLLoader.load(getClass().getResource("artistInfo.fxml"));
        Scene sceneUser = new Scene(userInfo);
        Stage userWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        userWindow.setScene(sceneUser);
        userWindow.show();
        System.out.println("- Showing artist info table");

    }

    public void showGenre(ActionEvent actionEvent) throws IOException {

        Parent userInfo = FXMLLoader.load(getClass().getResource("genreInfo.fxml"));
        Scene sceneUser = new Scene(userInfo);
        Stage userWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        userWindow.setScene(sceneUser);
        userWindow.show();
        System.out.println("- Showing genre info table");

    }

    public void changeData(ActionEvent actionEvent) {

        try {

            Statement updateSt = this.connection.createStatement();

            String userId = null;

                userId = new String(Files.readAllBytes(Paths.get("user_id.txt")));


            String sqlUpdate = "UPDATE user SET nick = '" + newNick.getText() + "', pass = '" + newPass.getText() + "' WHERE id = " + userId + ";";
            int updateRs = updateSt.executeUpdate(sqlUpdate);

            System.out.println("- Your login data have been updated, your nick is : " + newNick.getText() + "");

        } catch (SQLException | IOException e) {
            e.printStackTrace();

        }

    }

    public void deleteAccount(ActionEvent actionEvent) throws IOException {

        Parent userInfo = FXMLLoader.load(getClass().getResource("delete.fxml"));
        Scene sceneUser = new Scene(userInfo);
        Stage userWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        userWindow.setScene(sceneUser);
        userWindow.show();
        System.out.println("- Showing 'Are you sure ?' window");

    }

    public void logOut(ActionEvent actionEvent) throws IOException {

        System.out.println("- You have been logged out");

        Parent userInfo = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene sceneUser = new Scene(userInfo);
        Stage userWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        userWindow.setScene(sceneUser);
        userWindow.show();

    }

    public void exitApp(ActionEvent actionEvent) {

        System.out.println("- Shutting off the Macrohard");
        System.exit(0);

    }

    public void musicPlayer(ActionEvent actionEvent) throws IOException {

        Parent userInfo = FXMLLoader.load(getClass().getResource("player.fxml"));
        Scene sceneUser = new Scene(userInfo);
        Stage userWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        userWindow.setScene(sceneUser);
        userWindow.show();
        System.out.println("- Showing music player");

    }
}
