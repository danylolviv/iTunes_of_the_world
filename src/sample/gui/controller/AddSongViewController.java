package sample.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.be.Playlist;
import sample.be.Song;
import sample.bll.ArtistGenreManager;
import sample.gui.models.PlaylistModel;
import sample.gui.models.SongModel;

public class AddSongViewController implements Initializable {

    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtArtist;
    @FXML
    private TextField txtGenre;
    @FXML
    private TextField txtDuration;
    @FXML
    private TextField txtPath;
    @FXML
    private Button closeButton;

    private SongModel songModel = new SongModel();

    private Song song;
    ObservableList<Song> songs = FXCollections.observableArrayList();

    private Playlist playlist;
    private PlaylistModel plModel;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        plModel = PlaylistModel.getInstance();
    }

    @FXML
    public void addSong()
    {
        int id = 57;
        String title = txtTitle.getText();
        String artist = txtArtist.getText();
        //String genre = txtGenre.getText();
        String genre = "genre";
        String path = txtPath.getText();

        songModel.addSong(title,artist,genre,path);
        closeWindow();

    }

    @FXML
    private void closeWindow()
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}
