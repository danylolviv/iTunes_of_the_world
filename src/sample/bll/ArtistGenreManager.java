package sample.bll;

import sample.be.Artist;
import sample.be.Genre;
import sample.dal.DALArtist;
import sample.dal.DALGenre;
import sample.dal.DAOArtist;
import sample.dal.DAOGenre;

import java.util.ArrayList;
import java.util.List;

public class ArtistGenreManager {
    DALGenre dalGenre;
    DALArtist dalArtist;

    public ArtistGenreManager(){
       dalGenre = new DAOGenre();
       dalArtist = new DAOArtist();
    }

    public Artist findArtistByID(int id){
        for (Artist artist : dalArtist.getAllArtists()){
            if (artist.getID() == id) return artist;
        }
        return null;
    }

    public Genre findGenreByID(int id){
        for (Genre genre : dalGenre.getAllGenres()){
            if (genre.getID() == id) return genre;
        }
        return null;
    }
}
