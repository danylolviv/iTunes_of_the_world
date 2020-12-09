package sample.dal;

import java.util.List;


import sample.be.Song;
import sample.exeptions.MrsDalException;

import java.io.IOException;
        import java.util.List;

public interface DALSong {
    List<Song> getAllMovies() ;
    List<Song> searchForTheMovies(String text);

    void add(Song song);

    void update(Song song) throws MrsDalException;

    void delete(Song song) throws MrsDalException;

    // Movie getMovieByID(int movieID);

    Song createSong(int releaseYear, String title, String songTitle, String artist) ;



}
