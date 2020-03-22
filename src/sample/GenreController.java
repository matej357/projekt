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

public class GenreController implements Initializable {

    ConnectionClass cn = new ConnectionClass();
    Connection connection;
    ObservableList<UserInfo> observableList;

    @FXML
    TableView<UserInfo> tableView;
    @FXML
    TableColumn<UserInfo, String> id_column;
    @FXML
    TableColumn<UserInfo, String> description_column;

    public GenreController() {

        this.connection = this.cn.getConnection();
        this.observableList = FXCollections.observableArrayList();
        this.tableView = new TableView();

    }

    public void initialize(URL location, ResourceBundle resources) {

        this.id_column.setCellValueFactory(new PropertyValueFactory("id"));
        this.description_column.setCellValueFactory(new PropertyValueFactory("description"));

        String userId = null;
        try {
            userId = new String(Files.readAllBytes(Paths.get("user_id.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String sqlGenre_infoSel = "SELECT * FROM genre_info where id = " + userId + ";";

        try {

            Statement descriptionSt = this.connection.createStatement();

            this.id_column = new TableColumn("id");
            this.description_column = new TableColumn("description");

            ResultSet descriptionRs = descriptionSt.executeQuery(sqlGenre_infoSel);

            while (descriptionRs.next()) {
                int id = descriptionRs.getInt("id");
                String description = descriptionRs.getString("description");
                this.observableList.add(new GenreInfo(id, description));

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
        System.out.print("- Shoving user data and choices in window");

    }
}
