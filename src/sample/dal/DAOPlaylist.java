package sample.dal;

import sample.be.Playlist;
import sample.bll.PlaylistManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOPlaylist implements DALPlaylist {

    private static DataAccess dataAccess;
    private PlaylistManager playlistManager;

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
                Playlist playlist = new Playlist(playlistName);
                playlists.add(playlist);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return playlists;
    }

    public void add(Playlist playlist) {
        try(Connection con = dataAccess.getConnection()){
            String sql = "INSERT INTO Playlists (playlistName) VALUES (?)";
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, playlist.getTitle());
            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Playlist> searchForThePlaylists(String text) {
        return null;
    }

}
