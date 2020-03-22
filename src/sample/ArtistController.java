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

public class ArtistController implements Initializable {

    //Vytvorenie spojenia
    ConnectionClass cn = new ConnectionClass();
    Connection connection;
    ObservableList<UserInfo> observableList;

    //Prepojenie dát z FXML
    @FXML
    TableView<UserInfo> tableView;
    @FXML
    TableColumn<UserInfo, String> id_column;
    @FXML
    TableColumn<UserInfo, String> name_column;
    @FXML
    TableColumn<UserInfo, String> active_column;
    @FXML
    TableColumn<UserInfo, String> origin_column;

    public ArtistController() {

        //Bližšie definovanie spojenia
        this.connection = this.cn.getConnection();
        this.observableList = FXCollections.observableArrayList();
        this.tableView = new TableView();

    }

    public void initialize(URL location, ResourceBundle resources) {

        this.id_column.setCellValueFactory(new PropertyValueFactory("id"));
        this.name_column.setCellValueFactory(new PropertyValueFactory("name"));
        this.active_column.setCellValueFactory(new PropertyValueFactory("active"));
        this.origin_column.setCellValueFactory(new PropertyValueFactory("origin"));

        //Načítanie ID používateľa z TXT dokumentu
        String userId = null;
        try {
            userId = new String(Files.readAllBytes(Paths.get("user_id.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //SQL príkaz na zobrazenie dád z tabuľky artist_info s príslušným ID
        String sqlSelArtist = "SELECT * FROM artist_info where id = " + userId + ";";

        try {

            //Definovanie stĺpcov v tabuľke
            Statement artistSt = this.connection.createStatement();
            this.id_column = new TableColumn("id");
            this.name_column = new TableColumn("name");
            this.active_column = new TableColumn("active");
            this.origin_column = new TableColumn("origin");
            ResultSet artistResult = artistSt.executeQuery(sqlSelArtist);

            //Zapĺňnanie tabuľky dátami z databázy
            while (artistResult.next()) {

                int id = artistResult.getInt("id");
                int active = artistResult.getInt("active");
                String name = artistResult.getString("name");
                String origin = artistResult.getString("origin");
                this.observableList.add(new ArtistInfo(id, name, active, origin));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.tableView.setItems(this.observableList);

    }

    public void goBack(ActionEvent actionEvent) throws IOException {

        //Zmena stage-u (späť na stránku používateľa) po kliknutí na tlačítko
        Parent userInfo = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene sceneUser = new Scene(userInfo);
        Stage userWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        userWindow.setScene(sceneUser);
        userWindow.show();

    }
}
