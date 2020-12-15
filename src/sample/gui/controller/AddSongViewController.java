package sample.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
<<<<<<< HEAD

=======
>>>>>>> parent of 396cdb7... Revert "Merge remote-tracking branch 'origin/main' into main"
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
<<<<<<< HEAD
import sample.gui.models.ArtistModel;
import sample.gui.models.GenreModel;
=======
import sample.gui.models.PlaylistModel;
>>>>>>> parent of 396cdb7... Revert "Merge remote-tracking branch 'origin/main' into main"
import sample.gui.models.SongModel;

public class AddSongViewController implements Initializable {

    @FXML
    private TextField txtTitle;
    @FXML
    private ChoiceBox<Artist> artistBox;
    @FXML
<<<<<<< HEAD
    private ChoiceBox<Genre> genreBox;
    @FXML
    private TextField txtDuration;
    @FXML
=======
>>>>>>> parent of 396cdb7... Revert "Merge remote-tracking branch 'origin/main' into main"
    private TextField txtPath;
    @FXML
    private Button closeButton;

    private SongModel songModel = new SongModel();
    private ArtistModel artistModel;
    private GenreModel genreModel;

    private Song song;
    ObservableList<Song> songs = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
<<<<<<< HEAD
        artistModel = new ArtistModel();
        artistBox.setItems(artistModel.getAllArtists());
        genreModel = new GenreModel();
        genreBox.setItems(genreModel.getAllGenres());
=======

>>>>>>> parent of 396cdb7... Revert "Merge remote-tracking branch 'origin/main' into main"
    }

    @FXML
    public void addSong()
    {
        int id = 57;
        String title = txtTitle.getText();
        Artist artist = artistBox.getValue();
        Genre genre = genreBox.getValue();
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
