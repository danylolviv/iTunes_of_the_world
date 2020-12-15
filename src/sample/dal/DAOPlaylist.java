package sample.dal;

import sample.be.Playlist;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOPlaylist implements DALPlaylist {

    private static DataAccess dataAccess;

    public DAOPlaylist() {
        dataAccess = new DataAccess();
    }


    public ArrayList<Playlist> getAllPlaylists() {
        ArrayList<Playlist> playlists = new ArrayList<>();
        try (Connection con = dataAccess.getConnection()) {
            String sql = "SELECT * FROM Playlists;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("id");
                String playlistName = rs.getString("playlistName");
                Playlist playlist = new Playlist(id, playlistName);
                playlists.add(playlist);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return playlists;
    }

    public List<Playlist> searchForThePlaylists(String text) {
        return null;
    }

}
