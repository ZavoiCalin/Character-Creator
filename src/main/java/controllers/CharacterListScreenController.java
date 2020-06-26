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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import services.User;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;


public class CharacterListScreenController implements Initializable {

    @FXML
    public Label player;

    @FXML
    public Button createNew;

    @FXML
    public Pane paneCh1;

    @FXML
    public Pane paneCh2;

    @FXML
    public Pane paneCh3;

    @FXML
    public Pane paneCh4;

    @FXML
    public Label labelCh1;

    @FXML
    public Label labelCh2;

    @FXML
    public Label labelCh3;

    @FXML
    public Label labelCh4;

    @FXML
    public ImageView avatarImage;

    public void switchToCreation(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getClassLoader().getResource("CharacterCreationScreen.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User.displayFeatures();

        paneCh1.setVisible(false);
        paneCh2.setVisible(false);
        paneCh3.setVisible(false);
        paneCh4.setVisible(false);


        player.setText(LoginController.usernam);

        displayCh();

    }




    public void displayCh()  {
        //try {
        JSONArray array=new JSONArray();
        array=User.displayFeatures();

        Iterator<JSONObject> iterator = array.iterator();

       for(int i=0;i<array.size();i++)
        {
            JSONObject obj2 = iterator.next();
            if(i==0){
                labelCh1.setText((String) obj2.get("Character name:")+"\nDescription: "+(String) obj2.get("Gender:")+" "+(String) obj2.get("Ears:")+" "+(String) obj2.get("Eye color:")+" "+(String) obj2.get("Hairstyle:"));
                paneCh1.setVisible(true);

            }
            if(i==1){
                labelCh2.setText((String) obj2.get("Character name:")+"\nDescription: "+(String) obj2.get("Gender:")+" "+(String) obj2.get("Ears:")+" "+(String) obj2.get("Eye color:")+" "+(String) obj2.get("Hairstyle:"));
                paneCh2.setVisible(true);

            }
            if(i==2){
                labelCh3.setText((String) obj2.get("Character name:")+"\nDescription: "+(String) obj2.get("Gender:")+" "+(String) obj2.get("Ears:")+" "+(String) obj2.get("Eye color:")+" "+(String) obj2.get("Hairstyle:"));
                paneCh3.setVisible(true);

            }
            if(i==3){
                labelCh4.setText((String) obj2.get("Character name:")+"\nDescription: "+(String) obj2.get("Gender:")+" "+(String) obj2.get("Ears:")+" "+(String) obj2.get("Eye color:")+" "+(String) obj2.get("Hairstyle:"));
                paneCh4.setVisible(true);

            }




        }


    }


}
