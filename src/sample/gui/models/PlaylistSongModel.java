package sample.gui.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.be.PlaylistSong;
import sample.bll.PlaylistSongManager;
import sample.exeptions.MrsDalException;
import sample.gui.controller.MainViewController;

import java.util.Collections;


public class PlaylistSongModel {
    private static final int SHIFT_ORDER_UP = -1;
    private static final int SHIFT_ORDER_DOWN = 1;

    private PlaylistSongManager playlistSongManager;
    private ObservableList<PlaylistSong> playlistSongs;

    public PlaylistSongModel(){
        playlistSongManager = new PlaylistSongManager();
        playlistSongs = FXCollections.observableArrayList();
        playlistSongs.addAll(playlistSongManager.getAllPlaylistSongs());
    }

    public ObservableList<PlaylistSong> getSongsInPlaylist(int playlistID) {
        ObservableList<PlaylistSong> songsInPlaylist = null;
        songsInPlaylist = FXCollections.observableArrayList();

        for (PlaylistSong playlistSong : playlistSongs) {
            if (playlistSong.getPlaylistID() == playlistID) songsInPlaylist.add(playlistSong);
        }
        return songsInPlaylist;
    }

    public int findNextSongPosition(int playlistId){
        int position = 1;

        for (PlaylistSong playlistSong : playlistSongs ) {
            if(playlistId == playlistSong.getPlaylistID() && position == playlistSong.getSongPosition()) position++;
        }

        return position;
    }

    public void switchPlaylistSongs(PlaylistSong playlistSongDown,PlaylistSong playlistSongUp) throws MrsDalException {
        playlistSongManager.updatePlaylistSong(playlistSongDown,SHIFT_ORDER_DOWN);
        playlistSongManager.updatePlaylistSong(playlistSongUp,SHIFT_ORDER_UP);

        Collections.swap(playlistSongs,playlistSongs.indexOf(playlistSongDown),playlistSongs.indexOf(playlistSongUp));

        playlistSongDown.setSongPosition(playlistSongDown.getSongPosition()+SHIFT_ORDER_DOWN);
        playlistSongUp.setSongPosition(playlistSongUp.getSongPosition()+SHIFT_ORDER_UP);
    }

    public void addPlaylistSong(PlaylistSong playlistSong) {
        playlistSongManager.addPlaylistSong(playlistSong);
        playlistSongs.add(playlistSong);
    }

    public void deletePlaylistSong(PlaylistSong playlistSong) throws MrsDalException {
        playlistSongManager.deletePlaylistSong(playlistSong);
        playlistSongs.remove(playlistSong);
        shiftSongsUp(playlistSong);
    }

    public void shiftSongsUp(PlaylistSong removedSong) throws MrsDalException {
        for (PlaylistSong playlistSong: playlistSongs) {
            if (playlistSong.getPlaylistID() == removedSong.getPlaylistID() && playlistSong.getSongPosition() > removedSong.getSongPosition()) {
                playlistSongManager.updatePlaylistSong(playlistSong, SHIFT_ORDER_UP);
                playlistSong.setSongPosition(playlistSong.getSongPosition() + SHIFT_ORDER_UP);
            }
        }
    }
}
