package sample.dal;

import sample.be.Song;
import sample.exeptions.MrsDalException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;


public class DAOSong implements DALSong {

    private static DataAccess dataAccess;
    public DAOSong(){
        dataAccess= new DataAccess();
    }


    @Override
    public List<Song> getAllSongs() {
        ArrayList<Song> allSongs = new ArrayList<>();
        try (Connection con = dataAccess.getConnection()) {
            String sql = "SELECT * FROM Songs;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("id");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                Integer length = rs.getInt("length");
                String filePath = rs.getString("path");
                Song song = new Song(id, title, artist, length, filePath);
                allSongs.add(song);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return allSongs;
    }


    @Override
    public List<Song> searchForTheSongs(String text) {
        return null;
    }

    @Override
    public void add(Song song) {
        try(Connection con = dataAccess.getConnection()){
            String sql = "INSERT INTO Songs (id,title,artistId,genreId,duration_sec,songUrl) VALUES (DateTime.UtcNow.Ticks.ToString(),?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, song.getTitle());
            statement.setInt(2, song.getArtist().getID());
            statement.setInt(3, song.getGenre().getID());
            statement.setInt(4, song.getDuration());
            statement.setString(5, song.getUriString());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Song song) throws MrsDalException {

    }

    @Override
    public void delete(Song song) throws MrsDalException {

    }

    @Override
    public Song createSong(int releaseYear, String title, String songTitle, String artist) {
        return null;
    }
}

