package sample.bll;

import sample.be.Artist;
import sample.be.Genre;
import sample.be.Song;
import sample.dal.DALArtist;
import sample.dal.DALGenre;
import sample.dal.DAOArtist;
import sample.dal.DAOGenre;

import java.util.ArrayList;
import java.util.List;

public class ArtistManager {
    DALArtist dalArtist;

    public ArtistManager(){
       dalArtist = new DAOArtist();
    }

    public Artist findArtistByID(int id){
        for (Artist artist : dalArtist.getAllArtists()){
            if (artist.getID() == id) return artist;
        }
        return null;
    }

    public Artist findArtistByName(String name){
        for (Artist artist : dalArtist.getAllArtists()){
            if (artist.getName() == name) return artist;
        }
        return null;
    }

    public List<Artist> getAllArtists(){
        return dalArtist.getAllArtists();
    }
}
