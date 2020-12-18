package sample.gui.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.be.Playlist;
import sample.bll.PlaylistManager;
import sample.exeptions.MrsDalException;


public class PlaylistModel {

    private PlaylistManager playlistManager;
    private ObservableList<Playlist> playlists;

    public PlaylistModel(){
        playlistManager = new PlaylistManager();
        playlists = FXCollections.observableArrayList();
        playlists.addAll(playlistManager.getAllPlaylists());
    }

    public void updatePlaylists(){
        playlists.setAll(playlistManager.getAllPlaylists());
    }

    public ObservableList<Playlist> getPlaylists() {
        return playlists;
    }

    public void addPlaylist(String title) {
        playlistManager.addPlaylist(title);
        updatePlaylists();
    }

    public void deletePlaylist(Playlist playlist) throws MrsDalException {
        playlistManager.deletePlaylist(playlist);
        updatePlaylists();
    }

    public void editPlaylist(Playlist playlist,String newName) throws MrsDalException {
        playlistManager.editPlaylist(playlist,newName);
        updatePlaylists();
    }
}
