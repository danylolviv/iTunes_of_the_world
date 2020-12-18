package sample.gui.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.be.Artist;
import sample.be.Genre;
import sample.bll.GenreManager;


import sample.gui.controller.MainViewController;

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

    public Genre findGenreByName(String name){
        for (Genre genre:genres) {
            if (genre.getName().equals(name)) return genre;
        }
        return null;
    }
}
