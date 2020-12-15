package sample.bll;

import sample.be.Artist;
import sample.be.Genre;
import sample.dal.DALGenre;
import sample.dal.DAOGenre;

import java.util.List;

public class GenreManager {
    DALGenre dalGenre;

    public GenreManager(){
        dalGenre = new DAOGenre();
    }


    public Genre findGenreByID(int id){
        for (Genre genre : dalGenre.getAllGenres()){
            if (genre.getID() == id) return genre;
        }
        return null;
    }

    public Genre findGenreByName(String name){
        for (Genre genre : dalGenre.getAllGenres()){
            if (genre.getName() == name) return genre;

        }
        return null;
    }

    public List<Genre> getAllGenres(){
        return dalGenre.getAllGenres();
    }
}
