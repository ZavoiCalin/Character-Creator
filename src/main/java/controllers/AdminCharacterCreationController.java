package controllers;

import exceptions.EmptyCharNameException;
import exceptions.NameNotAvailableException;
import exceptions.TooManyCharException;
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
import javafx.stage.Stage;
import services.User;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminCharacterCreationController implements Initializable {
    @FXML
    public ComboBox<String> genderComboBox1;
    ObservableList<String> genderListObs1= FXCollections.observableArrayList ("Male","Female");

    @FXML
    public ComboBox<String> earsComboBox1;
    ObservableList<String> earsListObs1= FXCollections.observableArrayList ("Human","Elf","Hobbit");

    @FXML
    public ComboBox<String> eyeColorComboBox1;
    ObservableList<String> eyeColorListObs1= FXCollections.observableArrayList ("Blue","Green","Brown");

    @FXML
    public ComboBox<String> hairstyleComboBox1;
    ObservableList<String> hairstyleListObs1= FXCollections.observableArrayList ("Short","Long");

    @FXML
    public ImageView avatarImage1;

    @FXML
    public Button createButton1;

    @FXML
    public Label creationErrorLabel1;

    @FXML
    public TextField nameAvatar1;

    @FXML
    public TextField deletionKeyAvatar1;

    @FXML
    public Button createCancelButton1;

    public void getPath(){
        String p="src/main/resources/images/";

        if(((String)genderComboBox1.getValue()).equals("Male"))
            p+="m";
        else p+="f";

        if(((String)earsComboBox1.getValue()).equals("Human"))
            p+="-human";
        else if(((String)earsComboBox1.getValue()).equals("Elf"))
            p+="-elf";
        else p+="-hobbit";

        if(((String)eyeColorComboBox1.getValue()).equals("Brown"))
            p+="-brown";
        else if(((String)eyeColorComboBox1.getValue()).equals("Blue"))
            p+="-blue";
        else p+="-green";

        if(((String)hairstyleComboBox1.getValue()).equals("Short"))
            p+="-short.png";
        else p+="-long.png";

        File file = new File(p);
        Image image = new Image(file.toURI().toString());
        avatarImage1.setImage(image);
    }

    @FXML
    public void createChar1(ActionEvent event) throws IOException {
        try {
            User.addCharacter(nameAvatar1.getText(), deletionKeyAvatar1.getText(), (String)genderComboBox1.getValue(), (String)earsComboBox1.getValue(), (String)eyeColorComboBox1.getValue(), (String)hairstyleComboBox1.getValue());

            Parent home_page_parent = FXMLLoader.load(getClass().getClassLoader().getResource("AdminCharacterListScreen.fxml"));
            Scene home_page_scene = new Scene(home_page_parent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.hide();
            app_stage.setScene(home_page_scene);
            app_stage.show();
        }
        catch (EmptyCharNameException e) {
            creationErrorLabel1.setText(e.getMessage());
        }
        catch(NameNotAvailableException e) {
            creationErrorLabel1.setText(e.getMessage());
        }
        catch(TooManyCharException e) {
            creationErrorLabel1.setText(e.getMessage());
        }
    }

    @FXML
    public void applyFeatures1(ActionEvent event){
        this.getPath();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        genderComboBox1.setItems(genderListObs1);
        genderComboBox1.setEditable(false);
        earsComboBox1.setItems(earsListObs1);
        earsComboBox1.setEditable(false);
        eyeColorComboBox1.setItems(eyeColorListObs1);
        eyeColorComboBox1.setEditable(false);
        hairstyleComboBox1.setItems(hairstyleListObs1);
        hairstyleComboBox1.setEditable(false);
    }

    public void switchBackToList1(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getClassLoader().getResource("AdminCharacterListScreen.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

}
