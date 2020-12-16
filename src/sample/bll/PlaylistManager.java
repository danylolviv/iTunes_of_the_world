package sample.bll;

import sample.be.Playlist;
import sample.be.Song;
import sample.dal.DAOPlaylist;

import java.util.List;
import java.util.stream.Stream;


public class PlaylistManager {

    private DAOPlaylist daoPlaylist;

    public PlaylistManager(){

        daoPlaylist = new DAOPlaylist();

    }


    public List<Playlist> getAllPlaylists(){
        return daoPlaylist.getAllPlaylists();
    }

    public void addPlaylist(String title) {
        daoPlaylist.add(new Playlist(title));
    }
}

