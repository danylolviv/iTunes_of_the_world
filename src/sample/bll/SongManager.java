package sample.bll;

import sample.be.Artist;
import sample.be.Genre;
import sample.be.Song;
import sample.dal.DAOSong;

import java.util.ArrayList;
import java.util.List;

public class SongManager {
    private DAOSong daoSong;
    public SongManager(){
        daoSong = new DAOSong();
    }
    public List<Song> getAllSongs(){
        return daoSong.getAllSongs();
    }
    public List<Song> searchTheSongs(String searchQuery){ return daoSong.searchForTheSongs(searchQuery); }


    public int getNextId() {
        List<Song> songs = getAllSongs();
        return songs.stream().filter(song -> song.getID() != songs.indexOf(song)).findFirst().map(songs::indexOf).orElse(songs.size());
    }
    public void addSong(String title, Artist artist, Genre genre, String path){

        daoSong.add(new Song(getNextId(),title,artist,genre,20,path));
    }
}
