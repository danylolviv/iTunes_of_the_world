package sample.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.be.Song;
import sample.gui.models.MusicPlayer;
import sample.gui.models.SongModel;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    public ListView<Song> lstViewSongs;
    private SongModel songModel;
    private boolean isSongPlaying = Boolean.parseBoolean(null);

    @FXML
    private Button newPlaylistButton;

    public void handleNewPlaylistbtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/gui/view/AddPlaylistView.fxml"));
        Parent root = loader.load();

        Stage addPlaylistViewStage = new Stage();
        addPlaylistViewStage.setScene(new Scene(root));

        addPlaylistViewStage.initModality(Modality.WINDOW_MODAL);

        addPlaylistViewStage.show();
    }

    public void handleNewSongbtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/gui/view/AddSongView.fxml"));
        Parent root = loader.load();

        Stage addPlaylistViewStage = new Stage();
        addPlaylistViewStage.setScene(new Scene(root));

        addPlaylistViewStage.initModality(Modality.WINDOW_MODAL);

        addPlaylistViewStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        songModel = new SongModel();
        lstViewSongs.setItems(songModel.getAllSongs());
    }

    public void btnPlayMusic(ActionEvent actionEvent) {
if(isSongPlaying==false){
MusicPlayer.resume();
isSongPlaying=true;
}
else {
    MusicPlayer.stopSong();
    isSongPlaying=false;
}
    }
}
