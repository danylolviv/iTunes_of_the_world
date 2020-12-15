package sample.bll;

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


}
