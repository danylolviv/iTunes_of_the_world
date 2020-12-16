package sample.bll;

import sample.be.Playlist;
import sample.be.Song;
import sample.dal.DAOPlaylist;

import java.util.List;


public class PlaylistManager {

    private DAOPlaylist daoPlaylist;

    public PlaylistManager(){

        daoPlaylist = new DAOPlaylist();

    }


    public List<Playlist> getAllPlaylists(){
        return daoPlaylist.getAllPlaylists();
    }
}

