package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteController {

    ConnectionClass cn = ConnectionClass.getInstance();
    Connection connection = cn.getConnection();

    public void noDelete(ActionEvent actionEvent) throws IOException {

        //Zmena stage-u späť na stránku používateľa (po kliknutí na tlačítko nie)
        Parent userInfo = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene sceneUser = new Scene(userInfo);
        Stage userWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        userWindow.setScene(sceneUser);
        userWindow.show();

    }

    public void delete(ActionEvent actionEvent) throws SQLException, IOException {

        //Ak užívateľ klikne na tlačítko áno, tak začne proces vymazávanie jeho účtu a informácií s ním spojených

        //Načítanie ID používateľa z TXT dokumentu
        String userId = null;
        try {
            userId = new String(Files.readAllBytes(Paths.get("user_id.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Vymazanie nicku a hesla z tabuľky user
        Statement userDelSt = this.connection.createStatement();
        String sqlUserDel = "delete from user where id =" + userId + ";";
        int userDelRes = userDelSt.executeUpdate(sqlUserDel);

        //Vymazanie informácií o obľúbenej piesni atď. požívateľa z tabuľky user_info
        Statement user_infoDelSt = this.connection.createStatement();
        String sqlUser_infoDel = "delete from user_info where id =" + userId + ";";
        int user_infoDelRes = user_infoDelSt.executeUpdate(sqlUser_infoDel);

        //Vymazanie informácií o obľúbenom žánry používateľa z tabuľky genre_info
        Statement genre_infoDelSt = this.connection.createStatement();
        String sqlGenre_infoDel = "delete from genre_info where id =" + userId + ";";
        int genre_infoDelRes = genre_infoDelSt.executeUpdate(sqlGenre_infoDel);

        //Vymazanie informácií o obľúmenom autorovi používateľa z tabuľky artist_info
        Statement artist_infoDelSt = this.connection.createStatement();
        String sqlArtist_infoDel = "delete from artist_info where id =" + userId + ";";
        int artist_infoDelRes = artist_infoDelSt.executeUpdate(sqlArtist_infoDel);

        //Vymazanie informácií o obľúbenej piesni používateľa z tabuľky song_info
        Statement song_infoDelSt = this.connection.createStatement();
        String sqlSong_infoDel = "delete from song_info where id =" + userId + ";";
        int song_infoDelRes = song_infoDelSt.executeUpdate(sqlSong_infoDel);

        //Po ukončení vymazávania všetkých informácií z databázy sa zmení stage naspäť na prihlasovaciu obrazovku
        Parent userInfo = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene sceneUser = new Scene(userInfo);
        Stage userWindow = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        userWindow.setScene(sceneUser);
        userWindow.show();

        //Vypísanie správy o úspešnom vymazaní účtu do konzoly
        System.out.println("- Your account have been successfully deleted");

    }
}
