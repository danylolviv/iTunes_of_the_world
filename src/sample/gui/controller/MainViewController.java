package sample.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.be.Playlist;
import sample.be.PlaylistSong;
import sample.be.Song;
import sample.bll.SongManager;
import sample.exeptions.MrsDalException;
import sample.gui.models.MusicPlayer;
import sample.gui.models.PlaylistModel;
import sample.gui.models.PlaylistSongModel;
import sample.gui.models.SongModel;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.gui.models.MusicPlayer.currentSong;

public class MainViewController implements Initializable {
    public ListView<Song> lstViewSongs;
    public ListView<Playlist> lstViewPlaylists;
    public ListView<PlaylistSong> lstViewPlaylistSongs;

    public javafx.scene.control.Label displaySongName;
    public javafx.scene.control.Button btnPlaylistSongAddRemove;

    private SongModel songModel;
    private PlaylistModel playlistModel;
    private PlaylistSongModel playlistSongModel;
    private MusicPlayer MP = new MusicPlayer(currentSong);

    Alert alertDALexception = new Alert(Alert.AlertType.CONFIRMATION, "An error occurred while accessing the database", ButtonType.OK);


    private boolean isSongPlaying = Boolean.parseBoolean(null);

    @FXML
    private javafx.scene.control.TextField typeField;

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
        playlistSongModel = new PlaylistSongModel();
        btnPlaylistSongAddRemove.setVisible(false);

        typeField.textProperty().addListener((observableValue, s, t1) -> {
            lstViewSongs.setItems(songModel.searchedSongs(t1));
        });
    }

    public void btnDeleteSong() throws MrsDalException {
        songModel.deleteSong(lstViewSongs.getSelectionModel().getSelectedItem());
    }

    public void btnPlayMusic(ActionEvent actionEvent) {

        if(isSongPlaying==true){

            MP.stopSong();
            isSongPlaying=false;
        }

        else {
            MP.resume();
            isSongPlaying=true;
        }
    }

    public void btnPrevSong(ActionEvent actionEvent) {

        if(lstViewSongs.getSelectionModel().isEmpty()){
            System.out.println("You need to choose song");
        }
        else{
            lstViewSongs.getSelectionModel().selectPrevious();
            MP.currentSong= lstViewSongs.getSelectionModel().getSelectedItem();
            MP.stopSong();
            MP.play();
            displaySongName.setText(currentSong.getTitle());
        }
    }

    public void btnNextSong(ActionEvent actionEvent) {
        if(lstViewSongs.getSelectionModel().isEmpty()){
            System.out.println("You need to choose song");
        }
        else{
            lstViewSongs.getSelectionModel().selectNext();
            MP.currentSong = lstViewSongs.getSelectionModel().getSelectedItem();
            MP.stopSong();
            MP.play();
            displaySongName.setText(MP.currentSong.getTitle());
        }
    }

    public void btnChooseSong(MouseEvent mouseEvent) {
        lstViewPlaylistSongs.getSelectionModel().clearSelection();
        MP.currentSong = lstViewSongs.getSelectionModel().getSelectedItem();
        if(isSongPlaying==true){
            MP.stopSong();
            isSongPlaying= false;
        }else {
            MP.play();
            isSongPlaying=true;
        }
        displaySongName.setText(currentSong.getTitle());
    }

    public void addRemovePlaylistSong(ActionEvent actionEvent){
        if(lstViewSongs.getSelectionModel().isEmpty() && !lstViewPlaylistSongs.getSelectionModel().isEmpty()){
            try {
                //remove the song
                playlistSongModel.deletePlaylistSong(lstViewPlaylistSongs.getSelectionModel().getSelectedItem());
                //refresh the list
                lstViewPlaylistSongs.setItems(playlistSongModel.getSongsInPlaylist(lstViewPlaylists.getSelectionModel().getSelectedItem().getId()));
            }
            catch (MrsDalException mrsDalException){
                alertDALexception.showAndWait();
            }
        }
        else{
            Song selectedSong = lstViewSongs.getSelectionModel().getSelectedItem();
            Playlist selectedPlaylist = lstViewPlaylists.getSelectionModel().getSelectedItem();
            playlistSongModel.addPlaylistSong(new PlaylistSong(selectedSong,selectedPlaylist.getId(),playlistSongModel.findNextSongPosition(selectedPlaylist.getId())));
            lstViewPlaylistSongs.setItems(playlistSongModel.getSongsInPlaylist(lstViewPlaylists.getSelectionModel().getSelectedItem().getId()));
        }
    }

    public void playlistSelect(MouseEvent mouseEvent){
        lstViewPlaylistSongs.getSelectionModel().clearSelection();
        if(lstViewSongs.getSelectionModel().isEmpty()) btnPlaylistSongAddRemove.setVisible(false);
        lstViewPlaylistSongs.setItems(playlistSongModel.getSongsInPlaylist(lstViewPlaylists.getSelectionModel().getSelectedItem().getId()));
    }

    public void playlistSongSelect(MouseEvent mouseEvent){
        lstViewSongs.getSelectionModel().clearSelection();
        displaySongName.setText(lstViewPlaylistSongs.getSelectionModel().getSelectedItem().getTitle());
        btnPlaylistSongAddRemove.setText("X");
        btnPlaylistSongAddRemove.setVisible(true);
    }

    public void songSelect(MouseEvent mouseEvent) {
        lstViewPlaylistSongs.getSelectionModel().clearSelection();
        displaySongName.setText(lstViewSongs.getSelectionModel().getSelectedItem().getTitle());
        btnPlaylistSongAddRemove.setText("<");
        btnPlaylistSongAddRemove.setVisible(true);
        if(isSongPlaying==true){
            MusicPlayer.stopSong();
            isSongPlaying= false;
        }else {
            MusicPlayer.play(lstViewSongs.getSelectionModel().getSelectedItem().getUriString());
            isSongPlaying=true;
        }

    }

    public void savePlaylist(ActionEvent actionEvent){

    }
}
