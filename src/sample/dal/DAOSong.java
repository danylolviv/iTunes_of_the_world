package sample.dal;

import sample.be.Song;
import sample.exeptions.MrsDalException;

import java.util.List;

public class DAOSong implements DALSong {

    @Override
    public List<Song> getAllSongs() {
        return null;
    }

    @Override
    public List<Song> searchForTheSongs(String text) {
        return null;
    }

    @Override
    public void add(Song song) {

    }

    @Override
    public void update(Song song) throws MrsDalException {

    }

    @Override
    public void delete(Song song) throws MrsDalException {

    }

    @Override
    public Song createSong(int releaseYear, String title, String songTitle, String artist) {
        return null;
    }
}


