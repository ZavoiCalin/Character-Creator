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
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import services.User;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class AdminCharacterListScreenController implements Initializable {

    @FXML
    public Label selectedPlayer;

    @FXML
    public Button createBttn;

    @FXML
    public Button back;

    @FXML
    public Pane paneAd1;

    @FXML
    public Pane paneAd2;

    @FXML
    public Pane paneAd3;

    @FXML
    public Pane paneAd4;

    @FXML
    public Label labelAd1;

    @FXML
    public Label labelAd2;

    @FXML
    public Label labelAd3;

    @FXML
    public Label labelAd4;

    String lab1, lab2, lab3, lab4;

    public void goBackToPlayerSelection(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getClassLoader().getResource("PlayerListScreen.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    public Image getPath(String gender, String  ears, String eyes, String hair){
        String p="src/main/resources/images/";
        if(gender.equals("Male"))
            p+="m";
        else p+="f";
        if(ears.equals("Human"))
            p+="-human";
        else if(ears.equals("Elf"))
            p+="-elf";
        else p+="-hobbit";
        if(eyes.equals("Brown"))
            p+="-brown";
        else if(eyes.equals("Blue"))
            p+="-blue";
        else p+="-green";
        if(hair.equals("Short"))
            p+="-short.png";
        else p+="-long.png";
        File file = new File(p);
        Image image = new Image(file.toURI().toString());
        return image;
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
                labelAd1.setText((String) obj2.get("Character name:")+"\nDescription: "+(String) obj2.get("Gender:")+" "+(String) obj2.get("Ears:")+" "+(String) obj2.get("Eye color:")+" "+(String) obj2.get("Hairstyle:"));
                paneAd1.setVisible(true);
                lab1=(String)obj2.get("Character name:");
                //avatarImage.setImage(getPath((String) obj2.get("Gender:"),(String) obj2.get("Ears:"),(String) obj2.get("Eye color:"),(String) obj2.get("Hairstyle:")));
            }
            if(i==1){
                labelAd2.setText((String) obj2.get("Character name:")+"\nDescription: "+(String) obj2.get("Gender:")+" "+(String) obj2.get("Ears:")+" "+(String) obj2.get("Eye color:")+" "+(String) obj2.get("Hairstyle:"));
                paneAd2.setVisible(true);
                lab2=(String) obj2.get("Character name:");
                //avatarImage1.setImage(getPath((String) obj2.get("Gender:"),(String) obj2.get("Ears:"),(String) obj2.get("Eye color:"),(String) obj2.get("Hairstyle:")));

            }
            if(i==2){
                labelAd3.setText((String) obj2.get("Character name:")+"\nDescription: "+(String) obj2.get("Gender:")+" "+(String) obj2.get("Ears:")+" "+(String) obj2.get("Eye color:")+" "+(String) obj2.get("Hairstyle:"));
                paneAd3.setVisible(true);
                lab3=(String) obj2.get("Character name:");
                //avatarImage2.setImage(getPath((String) obj2.get("Gender:"),(String) obj2.get("Ears:"),(String) obj2.get("Eye color:"),(String) obj2.get("Hairstyle:")));

            }
            if(i==3){
                labelAd4.setText((String) obj2.get("Character name:")+"\nDescription: "+(String) obj2.get("Gender:")+" "+(String) obj2.get("Ears:")+" "+(String) obj2.get("Eye color:")+" "+(String) obj2.get("Hairstyle:"));
                paneAd4.setVisible(true);
                lab4=(String) obj2.get("Character name:");
             //   avatarImage3.setImage(getPath((String) obj2.get("Gender:"),(String) obj2.get("Ears:"),(String) obj2.get("Eye color:"),(String) obj2.get("Hairstyle:")));

            }


        }

    }

    public void adminCreateNewCharacter(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getClassLoader().getResource("AdminCharacterCreationScreen.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        User.displayFeatures();
        paneAd1.setVisible(false);
        paneAd2.setVisible(false);
        paneAd3.setVisible(false);
        paneAd4.setVisible(false);
        selectedPlayer.setText(LoginController.usernam);
        displayCh();
    }
}
