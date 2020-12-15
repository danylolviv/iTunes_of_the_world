package sample.dal;

import sample.be.Artist;
import sample.be.Genre;
import sample.be.Song;
import sample.bll.ArtistGenreManager;
import sample.exeptions.MrsDalException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;


public class DAOSong implements DALSong {

    private static DataAccess dataAccess;
    private ArtistGenreManager agManager;
    public DAOSong(){
        dataAccess= new DataAccess();
        agManager = new ArtistGenreManager();
    }


    @Override
    public List<Song> getAllSongs() {
        ArrayList<Song> songs = new ArrayList<>();
        try (Connection con = dataAccess.getConnection()) {
            String sql = "SELECT * FROM Songs;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("id");
                String title = rs.getString("title");
                int genreId = rs.getInt("genreId");
                int artistId = rs.getInt("artistId");
                String songUrl = rs.getString("songUrl");
                Song song = new Song(id, title, agManager.findArtistByID(artistId), agManager.findGenreByID(genreId), 10, songUrl);
                songs.add(song);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return songs;
    }


    @Override
    public List<Song> searchForTheSongs(String text) {
        return null;
    }

    @Override
    public void add(Song song) {
        try(Connection con = dataAccess.getConnection()){
            String sql = "INSERT INTO Songs (id,title,artistId,genreId,songUrl) VALUES (DateTime.UtcNow.Ticks.ToString(),?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, song.getTitle());
            statement.setInt(2, song.getArtist().getID());
            statement.setInt(3, song.getGenre().getID());
            statement.setString(4, song.getUriString());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Song song) throws MrsDalException {
        try(Connection con = dataAccess.getConnection()){
            String sql = "UPDATE Songs SET title = ?, artistID = ?, genreID = ?, songUrl = ? WHERE id = ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, song.getTitle());
            statement.setInt(2, song.getArtist().getID());
            statement.setInt(3, song.getGenre().getID());
            statement.setString(4, song.getUriString());
            statement.setInt(5, song.getID());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Song song) throws MrsDalException {
        try(Connection con = dataAccess.getConnection()){
            String sql = "DELETE FROM Songs WHERE id = ?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setInt(1, song.getID());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

