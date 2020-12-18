package sample.gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.be.Artist;
import sample.be.Genre;
import sample.be.Song;
import sample.exeptions.MrsDalException;
import sample.gui.models.ArtistModel;
import sample.gui.models.GenreModel;
import sample.gui.models.SongModel;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEditSongViewController implements Initializable {

    @FXML
    private Button btnAddEdit;
    @FXML
    private TextField txtTitle;
    @FXML
    private ChoiceBox<Artist> artistBox;
    @FXML
    private ChoiceBox<Genre> genreBox;
    @FXML
    private TextField txtDuration;
    @FXML
    private TextField txtPath;
    @FXML
    private Button closeButton;

    private SongModel songModel;
    private ArtistModel artistModel;
    private GenreModel genreModel;

    private Song editedSong;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        artistModel = new ArtistModel();
        artistBox.setItems(artistModel.getAllArtists());
        genreModel = new GenreModel();
        genreBox.setItems(genreModel.getAllGenres());

    }

    public void setSongModel(SongModel songModel){
        this.songModel = songModel;
    }

    public void setEditedSong(Song editedSong) {
        this.editedSong = editedSong;
        editFields();
    }

    public void editFields() {
        btnAddEdit.setText("Edit");
        txtTitle.setText(editedSong.getTitle());
        artistBox.setValue(artistModel.findArtistByName(editedSong.getArtist()));
        genreBox.setValue(genreModel.findGenreByName(editedSong.getGenre()));
        txtPath.setText(editedSong.getUriString());
    }

    @FXML
    private void addEditSong()
    {
        String title = txtTitle.getText();
        String artist = artistBox.getValue().getName();
        String genre = genreBox.getValue().getName();
        String path = txtPath.getText();

        if(btnAddEdit.getText().equals("Edit")){
            try {
                songModel.editSong(new Song(editedSong.getId(),title,artist,genre,10,path));
            }
            catch (MrsDalException mrsDalException) {
                mrsDalException.printStackTrace();
            }
        }
        else {
            songModel.addSong(title,artist,genre,path);
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
