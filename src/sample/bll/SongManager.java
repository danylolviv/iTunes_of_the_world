package sample.bll;

import sample.be.Song;
import sample.dal.DAOSong;

import java.util.List;

public class SongManager {
    private DAOSong daoSong;
    public SongManager(){
        daoSong = new DAOSong();
    }
    public List<Song> getAllSongs(){
        return daoSong.getAllSongs();
    }
}
