package sample.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.be.Playlist;
import sample.gui.models.PlaylistModel;

public class AddPlaylistViewController implements Initializable {

    @FXML
    private Button closeButton;
    @FXML
    private TextField txtTitle;


    private Playlist playlist;
    private PlaylistModel plModel;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        plModel = PlaylistModel.getInstance();
    }

    @FXML
    private void addPlaylist(ActionEvent event)
    {
        String name = txtTitle.getText();

        playlist = new Playlist(name);
        plModel.addPlaylist(playlist);
        closeWindow();
        plModel.updatePlaylistView();

    }

    @FXML
    private void closeWindow()
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

}

