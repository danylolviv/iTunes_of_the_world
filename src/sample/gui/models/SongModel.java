package sample.gui.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.be.Song;
import sample.bll.ArtistGenreManager;
import sample.bll.SongManager;

public class SongModel {
    private ArtistGenreManager agManager;
    private SongManager songManager;
    private ObservableList<Song> songs;

    public SongModel(){
        songManager = new SongManager();
        agManager= new ArtistGenreManager();
        songs = FXCollections.observableArrayList();
        songs.addAll(songManager.getAllSongs());
    }

    public ObservableList<Song> getAllSongs() {
        return songs;

    }

    public void addSong(String title,String artist,String genre,String path) {
        songManager.addSong(title,agManager.findArtistByName(artist),agManager.findGenreByName(genre),path);

    }

}
