package sample.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class MainViewController {


    @FXML
    private Button newPlaylistButton;

    public void handleNewPlaylistbtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/gui/view/AddPlaylistView.fxml"));
        Parent root = loader.load();

        Stage addPlaylistViewStage = new Stage();
        addPlaylistViewStage.setScene(new Scene(root));

        addPlaylistViewStage.initModality(Modality.WINDOW_MODAL);

        addPlaylistViewStage.show();
    }
}
