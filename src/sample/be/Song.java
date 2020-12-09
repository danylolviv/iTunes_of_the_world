package sample.be;

import java.util.List;

public class Song {
    private String uriString;
    private String title;
    private String artist;
    private int lenght;
    private List<Playlist> playlists;

    public Song(String uriString, String title, String artist, int lenght, Playlist plList) {
        this.uriString = uriString;
        this.title = title;
        this.artist = artist;
        this.lenght = lenght;
        this.playlists.add(plList);
    }

    public String getUriString() {
        return uriString;
    }

    public void setUriString(String uriString) {
        this.uriString = uriString;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Playlist plList) {
        this.playlists.add(plList);
    }
}
