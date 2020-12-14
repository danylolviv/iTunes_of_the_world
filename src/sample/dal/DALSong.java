package sample.dal;

import java.util.List;


import sample.be.Song;
import sample.exeptions.MrsDalException;

import java.io.IOException;
        import java.util.List;

public interface DALSong {
    List<Song> getAllSongs() ;
    List<Song> searchForTheSongs(String text);

    void add(Song song);

    void update(Song song) throws MrsDalException;

    void delete(Song song) throws MrsDalException;

}
