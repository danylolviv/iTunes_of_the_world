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
import sample.be.Playlist;
import sample.dal.DAOSong;
import sample.dal.DAOPlaylist;
import sample.gui.models.MusicPlayer;
import sample.gui.models.SongModel;
import sample.gui.models.PlaylistModel;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {
    public ListView<Song> lstViewSongs;
    public ListView<Playlist> lstViewPlaylists;
    public javafx.scene.control.Label displaySongName;
    private SongModel songModel;
    private PlaylistModel playlistModel;
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
        playlistModel = new PlaylistModel();
        lstViewPlaylists.setItems(playlistModel.getPlaylists());
    }

    public void btnPlayMusic(ActionEvent actionEvent) {
        if(lstViewSongs.getSelectionModel().isEmpty()){
            System.out.println("You need to choose song");
        }
            else{
                displaySongName.setText(lstViewSongs.getSelectionModel().getSelectedItem().getTitle());

            if(isSongPlaying==false){
                MusicPlayer.resume(lstViewSongs.getSelectionModel().getSelectedItem().getUriString());
                isSongPlaying=true;
            }

            else {
                MusicPlayer.stopSong();
                isSongPlaying=false;
            }
        }

    /*
        if(isSongPlaying==false){
            MusicPlayer.resume();
            isSongPlaying=true;
        }

        else {
            MusicPlayer.stopSong();
            isSongPlaying=false;
        }
    */

    }

    public void btnPrevSong(ActionEvent actionEvent) {

        if(lstViewSongs.getSelectionModel().isEmpty()){
            System.out.println("You need to choose song");
        }
        else{
            lstViewSongs.getSelectionModel().selectPrevious();
            MusicPlayer.stopSong();
            MusicPlayer.play(lstViewSongs.getSelectionModel().getSelectedItem().getUriString());
        }
    }

    public void btnNextSong(ActionEvent actionEvent) {
        if(lstViewSongs.getSelectionModel().isEmpty()){
            System.out.println("You need to choose song");
        }
        else{
            lstViewSongs.getSelectionModel().selectNext();
            MusicPlayer.stopSong();
            MusicPlayer.play(lstViewSongs.getSelectionModel().getSelectedItem().getUriString());
        }
    }
}
