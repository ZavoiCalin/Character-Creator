package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CharacterListScreenController implements Initializable {

    @FXML
    public Label player;

    @FXML
    public Button createNew;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        player.setText(User.user);
    }

    public void switchToCreation(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getClassLoader().getResource("CharacterCreationScreen.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();

    }

}
