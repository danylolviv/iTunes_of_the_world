package sample.gui.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.be.Playlist;

public class PlaylistModel {

    private static PlaylistModel instance;

    private ObservableList<Playlist> playlists = FXCollections.observableArrayList();
    private ObservableList playlistTitles = FXCollections.observableArrayList();

    public static PlaylistModel getInstance()
    {
        if (instance == null)
        {
            instance = new PlaylistModel();
        }
        return instance;
    }

    public PlaylistModel() {

    }

    public void addPlaylist(Playlist playlist)
    {
        playlists.add(playlist);
    }

    public ObservableList<Playlist> getPlaylists()
    {
        return playlists;
    }

    public void setPlaylistTitles()
    {
        playlistTitles.clear();
        for (Playlist playlist : playlists)
        {
            playlistTitles.add(playlist.getTitle());
        }

    }

    public ObservableList<String> getPlaylistTitles()
    {
        setPlaylistTitles();
        return playlistTitles;
    }

    public void updatePlaylistView()
    {

    }

}
