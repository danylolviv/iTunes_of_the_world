package sample.gui.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.be.Artist;
import sample.be.Genre;
import sample.be.Song;
import sample.bll.ArtistManager;
import sample.bll.GenreManager;
import sample.bll.SongManager;

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

    public void searchedSongs(String searchQuery){
        System.out.println(searchQuery);
//                try{
//            songs.removeAll();
//            songs.addAll(songMenager.searchTheSongs(searchQuery));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void addSong(String title, Artist artist, Genre genre, String path) {
        songManager.addSong(title, artist, genre,path);

    }

}
