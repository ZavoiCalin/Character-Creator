package controllers;

import com.sun.corba.se.spi.monitoring.MonitoredAttributeInfo;
import exceptions.EmptyCharNameException;
import exceptions.NameNotAvailableException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.User;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CharacterCreationController implements Initializable {
    @FXML
    public ComboBox<String> genderComboBox;
    ObservableList<String> genderListObs= FXCollections.observableArrayList ("Male","Female");

    @FXML
    public ComboBox<String> earsComboBox;
    ObservableList<String> earsListObs= FXCollections.observableArrayList ("Human","Elf","Hobbit");

    @FXML
    public ComboBox<String> eyeColorComboBox;
    ObservableList<String> eyeColorListObs= FXCollections.observableArrayList ("Blue","Green","Brown");

    @FXML
    public ComboBox<String> hairstyleComboBox;
    ObservableList<String> hairstyleListObs= FXCollections.observableArrayList ("Short","Long");

    @FXML
    public ImageView avatarImage;

    @FXML
    public Button createButton;

    @FXML
    public Label creationErrorLabel;

    @FXML
    public TextField nameAvatar;

    @FXML
    public TextField deletionKeyAvatar;

    @FXML
    public Button createCancelButton;

    public void getPath(){
        String p="src/main/resources/images/";

        if(((String)genderComboBox.getValue()).equals("Male"))
            p+="m";
        else p+="f";

        if(((String)earsComboBox.getValue()).equals("Human"))
            p+="-human";
        else if(((String)earsComboBox.getValue()).equals("Elf"))
            p+="-elf";
        else p+="-hobbit";

        if(((String)eyeColorComboBox.getValue()).equals("Brown"))
            p+="-brown";
        else if(((String)eyeColorComboBox.getValue()).equals("Blue"))
            p+="-blue";
        else p+="-green";

        if(((String)hairstyleComboBox.getValue()).equals("Short"))
            p+="-short.png";
        else p+="-long.png";

        File file = new File(p);
        Image image = new Image(file.toURI().toString());
        avatarImage.setImage(image);
    }

    @FXML
    public void createChar(ActionEvent event) throws IOException {
        try
        {
            User.addCharacter(nameAvatar.getText(), deletionKeyAvatar.getText(), (String)genderComboBox.getValue(), (String)earsComboBox.getValue(), (String)eyeColorComboBox.getValue(), (String)hairstyleComboBox.getValue());

            Parent home_page_parent = FXMLLoader.load(getClass().getClassLoader().getResource("CharacterListScreen.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(home_page_scene);
            app_stage.show();
        }
        catch (EmptyCharNameException e)
        {
            //error if not all fields are completed
            creationErrorLabel.setText(e.getMessage());
        }
        catch(NameNotAvailableException e)
        {
            creationErrorLabel.setText(e.getMessage());
        }
    }

    @FXML
    public void applyFeatures(ActionEvent event){
            this.getPath();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        genderComboBox.setItems(genderListObs);
        genderComboBox.setEditable(false);
        earsComboBox.setItems(earsListObs);
        earsComboBox.setEditable(false);
        eyeColorComboBox.setItems(eyeColorListObs);
        eyeColorComboBox.setEditable(false);
        hairstyleComboBox.setItems(hairstyleListObs);
        hairstyleComboBox.setEditable(false);
    }

    public void switchBackToList(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getClassLoader().getResource("CharacterListScreen.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

}
