package sample.gui.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.be.Artist;
import sample.be.Genre;
import sample.be.Song;
import sample.bll.ArtistManager;
import sample.bll.GenreManager;

public class GenreModel {
    private GenreManager genreManager;
    private ObservableList<Genre> genres;

    public GenreModel(){
        genreManager = new GenreManager();
        genres = FXCollections.observableArrayList();
        genres.addAll(genreManager.getAllGenres());
    }

    public ObservableList<Genre> getAllGenres() {
        return genres;

    }
}
