package controllers;

import exceptions.EmptyCharNameException;
import exceptions.NameNotAvailableException;
import exceptions.TooManyCharException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import services.User;

import javax.swing.*;
import java.io.*;

import static org.junit.Assert.assertEquals;

public class CharacterCreationControllerTest extends ApplicationTest {

    public static final String TEST_NAMEAVATAR = "Avatar name";

    public static final String TEST_DELETIONKEYAVATAR ="Deletion key";
    String pTest="src/main/resources/images/m-human-blue-short.png";

    public static final ActionEvent TEST_EVENT = null;

    private CharacterCreationController controller;

    private static JSONArray array = new JSONArray();

    private ActionEvent event = null;

    @Before
    public void setUp() throws Exception{

        controller = new CharacterCreationController();

        controller.genderComboBox = new ComboBox<String>();
        controller.earsComboBox = new ComboBox<String>();
        controller.eyeColorComboBox = new ComboBox<String>();
        controller.hairstyleComboBox = new ComboBox<String>();

        controller.avatarImage = new ImageView();
        controller.creationErrorLabel = new Label();

        controller.nameAvatar = new TextField();
        controller.deletionKeyAvatar = new TextField();

        controller.p = new String();

        controller.nameAvatar.setText(TEST_NAMEAVATAR);
        controller.deletionKeyAvatar.setText(TEST_DELETIONKEYAVATAR);

        controller.genderComboBox.setItems(controller.genderListObs);
        controller.earsComboBox.setItems(controller.earsListObs);
        controller.eyeColorComboBox.setItems(controller.eyeColorListObs);
        controller.hairstyleComboBox.setItems(controller.hairstyleListObs);

        controller.genderComboBox.setValue("Male");
        controller.earsComboBox.setValue("Human");
        controller.eyeColorComboBox.setValue("Blue");
        controller.hairstyleComboBox.setValue("Short");

        LoginController.usernam = "Dr. D";


        JSONObject obj = new JSONObject();
        JSONArray arrayPlayer = new JSONArray();
        JSONParser jp = new JSONParser();
        Object p;
        try {
            FileReader readFile = new FileReader("src/main/resources/Player.json");
            BufferedReader read = new BufferedReader(readFile);
            p = jp.parse(read);
            if (p instanceof JSONArray) {
                arrayPlayer = (JSONArray) p;
            }
            array = arrayPlayer;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Test // getPath
    public void testGetPath(){

        controller.getPath();
        assertEquals(controller.p, pTest);

    }

    @Test // applyFeatures
    public void testApplyFeatures(){

        controller.applyFeatures(event);
        assertEquals(controller.p, pTest);

    }

    @Test // createChar
    public void testEmptyCharacterFieldsCharacterName() throws IOException {
        controller.nameAvatar.setText("");
        controller.createChar(TEST_EVENT);
        assertEquals("Please, in order to create your character, enter BOTH a name, and a deletion key.", controller.creationErrorLabel.getText());
    }

    @Test // createChar
    public void testEmptyCharacterFieldsCharacterDeletionKey() throws IOException {
        controller.deletionKeyAvatar.setText("");
        controller.createChar(TEST_EVENT);
        assertEquals("Please, in order to create your character, enter BOTH a name, and a deletion key.", controller.creationErrorLabel.getText());
    }

    @Test //createChar
    public void testNameNotAvailable() throws NameNotAvailableException, EmptyCharNameException, TooManyCharException, IOException {
        User.addCharacter(TEST_NAMEAVATAR, TEST_DELETIONKEYAVATAR, "Male", "Human", "Blue", "Short");
        controller.createChar(TEST_EVENT);
        assertEquals("Sorry, but this name has just been taken by someone else.", controller.creationErrorLabel.getText());
    }

    @Test //createChar
    public void testTooManyCharacters() throws NameNotAvailableException, EmptyCharNameException, TooManyCharException, IOException {
        User.addCharacter("AAA", "AAA", "Male", "Human", "Blue", "Short");
        User.addCharacter("BBB", "BBB", "Male", "Human", "Blue", "Short");
        User.addCharacter("CCC", "CCC", "Male", "Human", "Blue", "Short");
        User.addCharacter("DDD", "DDD", "Male", "Human", "Blue", "Short");
        controller.createChar(TEST_EVENT);
        assertEquals("You cannot add more than 4 characters!", controller.creationErrorLabel.getText());
    }

    @After
    public void afterClass() throws Exception{

        JSONObject obj = new JSONObject();
        JSONArray arrayP = new JSONArray();
        JSONParser jp = new JSONParser();
        Object p;
        try {
            FileReader readFile = new FileReader("src/main/resources/Player.json");
            BufferedReader read = new BufferedReader(readFile);
            p = jp.parse(read);
            if (p instanceof JSONArray) {
                arrayP = (JSONArray) p;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        arrayP.clear();
        arrayP = array;
        try {
            File file = new File("src/main/resources/Player.json");
            FileWriter fisier = new FileWriter(file.getAbsoluteFile());
            fisier.write(arrayP.toJSONString());
            fisier.flush();
            fisier.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
