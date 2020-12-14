package sample.dal;

import sample.be.Playlist;
import sample.be.Song;

import java.util.ArrayList;
import java.util.List;

public class MockData implements dataAccessnterface {

    public List<Song> allSongs = new ArrayList<>();

    @Override
    public List<Song> getAllSongs() {
        //allSongs.add(s1);
        //allSongs.add(s2);
        //allSongs.add(s3);
        return allSongs;
    }

    Playlist zori = new Playlist("Zori");
    Playlist pathetic = new Playlist("Pathetic");

    //Song s1 = new Song(1,"theSong1", "Kalinka", 223,  "zori");
    //Song s2 = new Song(2, "Makaroni", "Hang", 432,  "zori");
    //Song s3 = new Song(3,"Lasagna", "Uber Eat", 432,  "pathetic");

}
