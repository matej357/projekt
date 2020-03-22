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

public class SongController implements Initializable {

    ConnectionClass cn = new ConnectionClass();
    Connection connection;
    ObservableList<UserInfo> observableList;

    @FXML
    TableView<UserInfo> tableView;
    @FXML
    TableColumn<UserInfo, String> id_column;
    @FXML
    TableColumn<UserInfo, String> name_column;
    @FXML
    TableColumn<UserInfo, String> duration_column;
    @FXML
    TableColumn<UserInfo, String> released_column;

    public SongController() {

        this.connection = this.cn.getConnection();
        this.observableList = FXCollections.observableArrayList();
        this.tableView = new TableView();

    }

    public void initialize(URL location, ResourceBundle resources) {

        this.id_column.setCellValueFactory(new PropertyValueFactory("id"));
        this.name_column.setCellValueFactory(new PropertyValueFactory("name"));
        this.duration_column.setCellValueFactory(new PropertyValueFactory("duration"));
        this.released_column.setCellValueFactory(new PropertyValueFactory("released"));

        String userId = null;
        try {
            userId = new String(Files.readAllBytes(Paths.get("user_id.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String sqlSongSel = "SELECT * FROM song_info where id = " + userId + ";";

        try {

            Statement songSelSt = this.connection.createStatement();
            this.id_column = new TableColumn("id");
            this.name_column = new TableColumn("name");
            this.duration_column = new TableColumn("duration");
            this.released_column = new TableColumn("released");
            ResultSet songSelRs = songSelSt.executeQuery(sqlSongSel);

            while (songSelRs.next()) {
                int id = songSelRs.getInt("id");
                String name = songSelRs.getString("name");
                String duration = songSelRs.getString("duration");
                int released = songSelRs.getInt("released");
                this.observableList.add(new SongInfo(id, name, released, duration));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.tableView.setItems(this.observableList);

    }

    public void goBack(ActionEvent actionEvent) throws IOException {

        Parent userInfo = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene sceneUser = new Scene(userInfo);
        Stage userWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        userWindow.setScene(sceneUser);
        userWindow.show();

    }
}
