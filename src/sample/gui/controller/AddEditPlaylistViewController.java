package sample.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.be.Artist;
import sample.be.Genre;
import sample.be.Playlist;
import sample.exeptions.MrsDalException;
import sample.gui.models.PlaylistModel;
import sample.gui.models.SongModel;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEditPlaylistViewController implements Initializable {

    @FXML
    private Button closeButton;
    @FXML
    private Button btnAddEdit;
    @FXML
    private TextField txtTitle;

    private PlaylistModel playlistModel;
    private Playlist editedPlaylist;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }

    public void setPlaylistModel(PlaylistModel playlistModel){
        this.playlistModel = playlistModel;
    }

    public void setEditedPlaylist(Playlist editedPlaylist) {
        this.editedPlaylist = editedPlaylist;
        btnAddEdit.setText("Edit");
        txtTitle.setText(editedPlaylist.getTitle());
    }

    @FXML
    private void addEditPlaylist(ActionEvent event)
    {
       String title = txtTitle.getText();
        if (btnAddEdit.getText().equals("Edit")) {
            try {
                playlistModel.editPlaylist(editedPlaylist,title);
            }
            catch (MrsDalException mrsDalException) {
                mrsDalException.printStackTrace();
            }
        }
        else {
            playlistModel.addPlaylist(title);
        }
       closeWindow();
    }

    @FXML
    private void closeWindow()
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}

