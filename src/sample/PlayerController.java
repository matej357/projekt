package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.IOException;

public class PlayerController {

    public ImageView pendulum;
    public ImageView acdc;
    public Label pendulumLabel;
    public Label acdcLabel;
    MediaPlayer mediaPlayer;

    Media streamline = new Media("file:///C:/Users/Matej/Desktop/try/pendulum_streamline.mp3");
    Media thunder = new Media("file:///C:/Users/Matej/Desktop/try/acdc_thunder.mp3");

    public PlayerController() {

       mediaPlayer = new MediaPlayer(streamline);

    }

    public void play(ActionEvent actionEvent) {

        mediaPlayer.setVolume(0.1);
        mediaPlayer.play();
        System.out.print("- Playing song");

    }

    public void stop(ActionEvent actionEvent) {

        mediaPlayer.pause();
        System.out.print("- Song paused");

    }

    public void next(ActionEvent actionEvent) {

        pendulum.setVisible(false);
        pendulumLabel.setVisible(false);
        acdc.setVisible(true);
        acdcLabel.setVisible(true);
        mediaPlayer.stop();
        mediaPlayer = new MediaPlayer(thunder);
        mediaPlayer.setVolume(0.1);
        mediaPlayer.play();
        System.out.println("- Playing next song");

    }

    public void prev(ActionEvent actionEvent) {

        acdc.setVisible(false);
        acdcLabel.setVisible(false);
        pendulum.setVisible(true);
        pendulumLabel.setVisible(true);
        mediaPlayer.stop();
        mediaPlayer = new MediaPlayer(streamline);
        mediaPlayer.setVolume(0.1);
        mediaPlayer.play();
        System.out.print("- Playing previous song");

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
