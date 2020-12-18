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
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.be.Playlist;
import sample.be.PlaylistSong;
import sample.be.Song;
import sample.exeptions.MrsDalException;
import sample.gui.models.MusicPlayer;
import sample.gui.models.PlaylistModel;
import sample.gui.models.PlaylistSongModel;
import sample.gui.models.SongModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.gui.models.MusicPlayer.currentSong;

public class MainViewController implements Initializable {
    @FXML
    private ListView<Song> lstViewSongs;
    @FXML
    private ListView<Playlist> lstViewPlaylists;
    @FXML
    private ListView<PlaylistSong> lstViewPlaylistSongs;

    @FXML
    private javafx.scene.control.Label displaySongName;
    @FXML
    private javafx.scene.control.Button btnPlaylistSongAddRemove;

    private SongModel songModel;
    private PlaylistModel playlistModel;
    private PlaylistSongModel playlistSongModel;
    private MusicPlayer MP = new MusicPlayer(currentSong);

    Alert alertDALexception = new Alert(Alert.AlertType.CONFIRMATION, "An error occurred while accessing the database", ButtonType.OK);


    private boolean isSongPlaying = Boolean.parseBoolean(null);

    @FXML
    private javafx.scene.control.TextField typeField;

    @FXML
    private void handleNewPlaylistbtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/gui/view/AddEditPlaylistView.fxml"));
        Parent root = loader.load();

        ((AddEditPlaylistViewController)loader.getController()).setPlaylistModel(playlistModel);
        Stage addPlaylistViewStage = new Stage();
        addPlaylistViewStage.setScene(new Scene(root));

        addPlaylistViewStage.initModality(Modality.WINDOW_MODAL);

        addPlaylistViewStage.show();
    }

    @FXML
    private void handleEditPlaylistbtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/gui/view/AddEditPlaylistView.fxml"));
        Parent root = loader.load();

        ((AddEditPlaylistViewController)loader.getController()).setPlaylistModel(playlistModel);
        ((AddEditPlaylistViewController)loader.getController()).setEditedPlaylist(lstViewPlaylists.getSelectionModel().getSelectedItem());

        Stage editPlaylistViewStage = new Stage();
        editPlaylistViewStage.setScene(new Scene(root));

        editPlaylistViewStage.initModality(Modality.WINDOW_MODAL);

        editPlaylistViewStage.show();
    }

    @FXML
    private void handleNewSongbtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/gui/view/AddEditSongView.fxml"));
        Parent root = loader.load();

        ((AddEditSongViewController)loader.getController()).setSongModel(songModel);

        Stage addEditSongViewStage = new Stage();
        addEditSongViewStage.setScene(new Scene(root));

        addEditSongViewStage.initModality(Modality.WINDOW_MODAL);

        addEditSongViewStage.show();
    }

    @FXML
    private void handleEditSongBtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/gui/view/AddEditSongView.fxml"));
        Parent root = loader.load();

        ((AddEditSongViewController)loader.getController()).setSongModel(songModel);
        ((AddEditSongViewController)loader.getController()).setEditedSong(lstViewSongs.getSelectionModel().getSelectedItem());

        Stage addEditSongViewStage = new Stage();
        addEditSongViewStage.setScene(new Scene(root));

        addEditSongViewStage.initModality(Modality.WINDOW_MODAL);

        addEditSongViewStage.show();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        songModel = new SongModel();
        lstViewSongs.setItems(songModel.getSongs());
        playlistModel = new PlaylistModel();
        lstViewPlaylists.setItems(playlistModel.getPlaylists());
        playlistSongModel = new PlaylistSongModel();
        btnPlaylistSongAddRemove.setVisible(false);

        typeField.textProperty().addListener((observableValue, s, t1) -> {
            lstViewSongs.setItems(songModel.searchedSongs(t1));
        });
    }

    public void btnDeleteSong(){
        try {
            songModel.deleteSong(lstViewSongs.getSelectionModel().getSelectedItem());
            lstViewSongs.setItems(songModel.getSongs());
            lstViewSongs.getSelectionModel().selectPrevious();
        }
        catch (MrsDalException mrsDalException) {
            mrsDalException.printStackTrace();
            alertDALexception.showAndWait();
        }
    }

    public void btnDeletePlaylist(){
        try {


            for (PlaylistSong playlistSong: lstViewPlaylistSongs.getItems()){
                if(playlistSong.getPlaylistID() == lstViewPlaylists.getSelectionModel().getSelectedItem().getId()){
                    playlistSongModel.deletePlaylistSong(playlistSong);
                }
            }
            playlistModel.deletePlaylist(lstViewPlaylists.getSelectionModel().getSelectedItem());
            lstViewPlaylists.getSelectionModel().selectPrevious();
            lstViewPlaylists.setItems(playlistModel.getPlaylists());
            lstViewPlaylistSongs.setItems(null);
        }
        catch (MrsDalException mrsDalException) {
            mrsDalException.printStackTrace();
            alertDALexception.showAndWait();
        }
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

    public void addRemovePlaylistSong(ActionEvent actionEvent){
        if(lstViewSongs.getSelectionModel().isEmpty() && !lstViewPlaylistSongs.getSelectionModel().isEmpty()){
            try {
                //remove the song
                playlistSongModel.deletePlaylistSong(lstViewPlaylistSongs.getSelectionModel().getSelectedItem());
                //select the song above
                lstViewPlaylistSongs.getSelectionModel().selectPrevious();
                //refresh the list
                refreshLstPlaylistSongs();
            }
            catch (MrsDalException mrsDalException){
                mrsDalException.printStackTrace();
                alertDALexception.showAndWait();
            }
        }
        if(!lstViewSongs.getSelectionModel().isEmpty() && lstViewPlaylistSongs.getSelectionModel().isEmpty()){
            Song selectedSong = lstViewSongs.getSelectionModel().getSelectedItem();
            Playlist selectedPlaylist = lstViewPlaylists.getSelectionModel().getSelectedItem();
            playlistSongModel.addPlaylistSong(new PlaylistSong(selectedSong,selectedPlaylist.getId(),playlistSongModel.findNextSongPosition(selectedPlaylist.getId())));
            lstViewPlaylistSongs.setItems(playlistSongModel.getSongsInPlaylist(lstViewPlaylists.getSelectionModel().getSelectedItem().getId()));
        }
    }



    public void playlistSelect(MouseEvent mouseEvent){
        lstViewPlaylistSongs.getSelectionModel().clearSelection();
        btnPlaylistSongAddRemove.setVisible(!lstViewSongs.getSelectionModel().isEmpty());
        refreshLstPlaylistSongs();
    }

    public void playlistSongSelect(MouseEvent mouseEvent){
        if(!lstViewPlaylistSongs.getSelectionModel().isEmpty()){
            lstViewSongs.getSelectionModel().clearSelection();
            displaySongName.setText(lstViewPlaylistSongs.getSelectionModel().getSelectedItem().getTitle());
            MP.setCurrentSong(lstViewPlaylistSongs.getSelectionModel().getSelectedItem());
            btnPlaylistSongAddRemove.setText("X");
            btnPlaylistSongAddRemove.setText(">");
            btnPlaylistSongAddRemove.setVisible(true);
            if(isSongPlaying==true){
                MP.stopSong();
                isSongPlaying= false;
            }else {
                MP.play();
                isSongPlaying=true;
            }
        }
    }

    public void songSelect(MouseEvent mouseEvent) {
        lstViewPlaylistSongs.getSelectionModel().clearSelection();
        displaySongName.setText(lstViewSongs.getSelectionModel().getSelectedItem().getTitle());
        if(!lstViewPlaylists.getSelectionModel().isEmpty()) {
            btnPlaylistSongAddRemove.setText("<");
            btnPlaylistSongAddRemove.setVisible(true);
        }
        MP.setCurrentSong(lstViewPlaylistSongs.getSelectionModel().getSelectedItem());
        if(isSongPlaying==true){
            MP.stopSong();
            isSongPlaying= false;
        }else {
            MP.play();
            isSongPlaying=true;
        }
    }

    public void btnPlaylistSongUp(ActionEvent actionEvent){
        if(lstViewPlaylistSongs.getSelectionModel().getSelectedIndex() > 0) {
            PlaylistSong selectedPlaylistSong = lstViewPlaylistSongs.getSelectionModel().getSelectedItem();
            PlaylistSong overSelectedPlaylistSong = lstViewPlaylistSongs.getItems().get(lstViewPlaylistSongs.getSelectionModel().getSelectedIndex() - 1);
            if (selectedPlaylistSong.getPlaylistID() == overSelectedPlaylistSong.getPlaylistID() && selectedPlaylistSong.getId() == overSelectedPlaylistSong.getId()) {
                lstViewPlaylistSongs.getSelectionModel().selectPrevious();
            } else {
                try {
                    playlistSongModel.switchPlaylistSongs(overSelectedPlaylistSong, selectedPlaylistSong);
                } catch (MrsDalException mrsDalException) {
                    mrsDalException.printStackTrace();
                    alertDALexception.showAndWait();
                }
            }
        }
        refreshLstPlaylistSongs();
    }

    public void btnPlaylistSongDown(ActionEvent actionEvent){
        if(lstViewPlaylistSongs.getSelectionModel().getSelectedIndex() < lstViewPlaylistSongs.getItems().size()-1) {
            PlaylistSong selectedPlaylistSong = lstViewPlaylistSongs.getSelectionModel().getSelectedItem();
            PlaylistSong underSelectedPlaylistSong = lstViewPlaylistSongs.getItems().get(lstViewPlaylistSongs.getSelectionModel().getSelectedIndex() + 1);
            if (selectedPlaylistSong.getPlaylistID() == underSelectedPlaylistSong.getPlaylistID() && selectedPlaylistSong.getId() == underSelectedPlaylistSong.getId()) {
                lstViewPlaylistSongs.getSelectionModel().selectNext();
            } else {
                try {
                    playlistSongModel.switchPlaylistSongs(selectedPlaylistSong, underSelectedPlaylistSong);
                } catch (MrsDalException mrsDalException) {
                    mrsDalException.printStackTrace();
                    alertDALexception.showAndWait();
                }
            }
            refreshLstPlaylistSongs();
        }
    }

    public void refreshLstPlaylistSongs(){
        lstViewPlaylistSongs.setItems(playlistSongModel.getSongsInPlaylist(lstViewPlaylists.getSelectionModel().getSelectedItem().getId()));
    }
}
