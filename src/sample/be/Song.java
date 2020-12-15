package sample.be;

import java.util.List;

public class Song {
    private String uriString;
    private int ID;
    private String title;
    private Artist artist;
    private Genre genre;
    private int duration;
    private List<Playlist> playlists;


    public Song(int ID, String title, Artist artist, Genre genre, int duration, String uriString) {

        this.genre = genre;
        this.title = title;
        this.artist = artist;
        this.uriString = uriString;
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

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Playlist plList) {
        this.playlists.add(plList);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Genre getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return ID + "  " + title + '\t' + artist.getName()  ;

    }
}
