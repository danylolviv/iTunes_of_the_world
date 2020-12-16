package sample.gui.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.be.Artist;
import sample.be.Genre;
import sample.be.Song;
import sample.bll.ArtistManager;
import sample.bll.GenreManager;
import sample.bll.SongManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SongModel {
    private ArtistManager artistManager;
    private GenreManager genreManager;
    private SongManager songManager;
    private ObservableList<Song> songs;

    public SongModel(){
        songManager = new SongManager();
        artistManager = new ArtistManager();
        genreManager = new GenreManager();
        songs = FXCollections.observableArrayList();
        songs.addAll(songManager.getAllSongs());
    }

    public ObservableList<Song> getAllSongs() {
        return songs;
    }

    public ObservableList<Song> searchedSongs(String searchQuery){
        System.out.println(searchQuery);
        ObservableList<Song> serchedSongs;
        serchedSongs = FXCollections.observableArrayList();
        if (songs.size() >= 1){
            for(Song s: songs){
                if (s.getTitle().contains(searchQuery.toLowerCase(Locale.ROOT))){
                    serchedSongs.add(s);
                }
            }
        }
        else{
            Genre gNull = new Genre(999,"null");
            Artist aNull = new Artist(999, "null");
            Song sNull = new Song(76394, "null", aNull , gNull,0, "hello null" );
            serchedSongs.add(sNull);
        }
    return serchedSongs;
    }

    public void addSong(String title, Artist artist, Genre genre, String path) {
        songManager.addSong(title, artist, genre,path);

    }

}
