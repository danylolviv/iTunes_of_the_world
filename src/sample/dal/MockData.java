package sample.dal;

import sample.be.Playlist;
import sample.be.Song;

import java.util.ArrayList;
import java.util.List;

public class MockData implements dataAccessnterface {

    public List<Song> allSongs = new ArrayList<>();

    @Override
    public List<Song> getAllSongs() {
        allSongs.add(s1);
        allSongs.add(s2);
        allSongs.add(s3);
        return allSongs;
    }

    Playlist zori = new Playlist("Zori");
    Playlist pathetic = new Playlist("Pathetic");

    Song s1 = new Song("theSong1", "Kalinka", "Artist 1", 289, zori);
    Song s2 = new Song("Makaroni", "Hang", "Tifest", 309, zori);
    Song s3 = new Song("Lasagna", "Uber Eat", "Tifest", 320, pathetic);

}
