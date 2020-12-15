package sample.gui.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.be.Song;
import sample.bll.SongManager;

public class SongModel {
    private SongManager songMenager;
    private ObservableList<Song> songs;

    public SongModel(){
        songMenager = new SongManager();
        songs = FXCollections.observableArrayList();
        songs.addAll(songMenager.getAllSongs());

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

}
