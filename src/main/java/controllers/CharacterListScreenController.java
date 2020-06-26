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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import services.User;

import java.io.File;
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
    @FXML
    public ImageView avatarImage1;
    @FXML
    public ImageView avatarImage2;
    @FXML
    public ImageView avatarImage3;
    @FXML
    public TextField deletionKey;
    @FXML
    public Button delete;
    String l1;
    String l2;
    String l3;
    String l4;

    @FXML
    public Button sel1;
    @FXML
    public Button sel2;
    @FXML
    public Button sel3;
    @FXML
    public Button sel4;
    public void button1(ActionEvent event){
        avatarImage.setVisible(true);
        avatarImage1.setVisible(false);
        avatarImage2.setVisible(false);
        avatarImage3.setVisible(false);
        sel1.setVisible(false);
        sel2.setVisible(true);
        sel3.setVisible(true);
        sel4.setVisible(true);

    }
    public void button2(ActionEvent event){
        avatarImage.setVisible(false);
        avatarImage1.setVisible(true);
        avatarImage2.setVisible(false);
        avatarImage3.setVisible(false);
        sel1.setVisible(true);
        sel2.setVisible(false);
        sel3.setVisible(true);
        sel4.setVisible(true);

    }
    public void button3(ActionEvent event){
        avatarImage.setVisible(false);
        avatarImage1.setVisible(false);
        avatarImage2.setVisible(true);
        avatarImage3.setVisible(false);
        sel1.setVisible(true);
        sel2.setVisible(true);
        sel3.setVisible(false);
        sel4.setVisible(true);

    }
    public void button4(ActionEvent event){
        avatarImage.setVisible(false);
        avatarImage1.setVisible(false);
        avatarImage2.setVisible(false);
        avatarImage3.setVisible(true);
        sel1.setVisible(true);
        sel2.setVisible(true);
        sel3.setVisible(true);
        sel4.setVisible(false);

    }
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
        avatarImage.setVisible(false);
        avatarImage1.setVisible(false);
        avatarImage2.setVisible(false);
        avatarImage3.setVisible(false);

        player.setText(LoginController.usernam);

        displayCh();

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

                labelCh1.setText((String) obj2.get("Character name:")+"\nDescription: "+(String) obj2.get("Gender:")+" "+(String) obj2.get("Ears:")+" "+(String) obj2.get("Eye color:")+" "+(String) obj2.get("Hairstyle:"));
                paneCh1.setVisible(true);
                l1=(String)obj2.get("Character name:");
                avatarImage.setImage(getPath((String) obj2.get("Gender:"),(String) obj2.get("Ears:"),(String) obj2.get("Eye color:"),(String) obj2.get("Hairstyle:")));
            }
            if(i==1){
                labelCh2.setText((String) obj2.get("Character name:")+"\nDescription: "+(String) obj2.get("Gender:")+" "+(String) obj2.get("Ears:")+" "+(String) obj2.get("Eye color:")+" "+(String) obj2.get("Hairstyle:"));
                paneCh2.setVisible(true);
                l2=(String) obj2.get("Character name:");
                avatarImage1.setImage(getPath((String) obj2.get("Gender:"),(String) obj2.get("Ears:"),(String) obj2.get("Eye color:"),(String) obj2.get("Hairstyle:")));


            }
            if(i==2){
                labelCh3.setText((String) obj2.get("Character name:")+"\nDescription: "+(String) obj2.get("Gender:")+" "+(String) obj2.get("Ears:")+" "+(String) obj2.get("Eye color:")+" "+(String) obj2.get("Hairstyle:"));
                paneCh3.setVisible(true);
                l3=(String) obj2.get("Character name:");
                avatarImage2.setImage(getPath((String) obj2.get("Gender:"),(String) obj2.get("Ears:"),(String) obj2.get("Eye color:"),(String) obj2.get("Hairstyle:")));


            }
            if(i==3){
                labelCh4.setText((String) obj2.get("Character name:")+"\nDescription: "+(String) obj2.get("Gender:")+" "+(String) obj2.get("Ears:")+" "+(String) obj2.get("Eye color:")+" "+(String) obj2.get("Hairstyle:"));
                paneCh4.setVisible(true);
                 l4=(String) obj2.get("Character name:");
                avatarImage3.setImage(getPath((String) obj2.get("Gender:"),(String) obj2.get("Ears:"),(String) obj2.get("Eye color:"),(String) obj2.get("Hairstyle:")));


            }




        }


    }

    public void handleDelete(ActionEvent event)
    {
        String name=User.deletionkey(deletionKey.getText());
        //System.out.println(l1);
        //System.out.println(name);
        if(name.equals(l1))
        {
            paneCh1.setVisible(false);

        }
        else if(name.equals(l2))
        {
            paneCh2.setVisible(false);

        }
        else if(name.equals(l3))
        {
            paneCh3.setVisible(false);

        }
        else if(name.equals(l3))
        {
            paneCh4.setVisible(false);

        }





    }


}
