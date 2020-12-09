package sample.dal;

import sample.be.Song;
import sample.exeptions.MrsDalException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

