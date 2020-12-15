package sample.bll;

import sample.be.Artist;
import sample.be.Genre;
import sample.dal.DALArtist;
import sample.dal.DALGenre;
import sample.dal.DAOArtist;
import sample.dal.DAOGenre;

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

    public Artist findArtistByName(String name){
        for (Artist artist : dalArtist.getAllArtists()){
            if (artist.getName() == name) return artist;
        }
        return null;
    }

    public Genre findGenreByName(String name){
        for (Genre genre : dalGenre.getAllGenres()){
            if (genre.getName() == name) return genre;

        }
        return null;
    }
}
