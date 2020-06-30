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

public class AdminCharacterCreationControllerTest extends ApplicationTest {

    public static final String TEST_NAMEAVATAR1 = "Avatar name 1";

    public static final String TEST_DELETIONKEYAVATAR1 ="Deletion key 1";
    String pTest="src/main/resources/images/m-human-blue-short.png";

    public static final ActionEvent TEST_EVENT = null;

    private AdminCharacterCreationController controller;

    private static JSONArray array = new JSONArray();

    private ActionEvent event = null;

    @Before
    public void setUp() throws Exception{

        controller = new AdminCharacterCreationController();

        controller.genderComboBox1 = new ComboBox<String>();
        controller.earsComboBox1 = new ComboBox<String>();
        controller.eyeColorComboBox1 = new ComboBox<String>();
        controller.hairstyleComboBox1 = new ComboBox<String>();

        controller.avatarImage1 = new ImageView();
        controller.creationErrorLabel1 = new Label();

        controller.nameAvatar1 = new TextField();
        controller.deletionKeyAvatar1 = new TextField();

        controller.p = new String();

        controller.nameAvatar1.setText(TEST_NAMEAVATAR1);
        controller.deletionKeyAvatar1.setText(TEST_DELETIONKEYAVATAR1);

        controller.genderComboBox1.setItems(controller.genderListObs1);
        controller.earsComboBox1.setItems(controller.earsListObs1);
        controller.eyeColorComboBox1.setItems(controller.eyeColorListObs1);
        controller.hairstyleComboBox1.setItems(controller.hairstyleListObs1);

        controller.genderComboBox1.setValue("Male");
        controller.earsComboBox1.setValue("Human");
        controller.eyeColorComboBox1.setValue("Blue");
        controller.hairstyleComboBox1.setValue("Short");

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

    @Test //getPath
    public void testGetPath(){

        controller.getPath();
        assertEquals(controller.p, pTest);

    }

    @Test // applyFeatures1
    public void testApplyFeatures1(){

        controller.applyFeatures1(event);
        assertEquals(controller.p, pTest);

    }

    @Test //createChar1
    public void testEmptyCharacterFieldsCharacterName() throws IOException {
        controller.nameAvatar1.setText("");
        controller.createChar1(TEST_EVENT);
        assertEquals("Please, in order to create your character, enter BOTH a name, and a deletion key.", controller.creationErrorLabel1.getText());
    }

    @Test //createChar1
    public void testEmptyCharacterFieldsCharacterDeletionKey() throws IOException {
        controller.deletionKeyAvatar1.setText("");
        controller.createChar1(TEST_EVENT);
        assertEquals("Please, in order to create your character, enter BOTH a name, and a deletion key.", controller.creationErrorLabel1.getText());
    }

    @Test //createChar1
    public void testNameNotAvailable1() throws NameNotAvailableException, EmptyCharNameException, TooManyCharException, IOException {
        User.addCharacter(TEST_NAMEAVATAR1, TEST_DELETIONKEYAVATAR1, "Male", "Human", "Blue", "Short");
        controller.createChar1(TEST_EVENT);
        assertEquals("Sorry, but this name has just been taken by someone else.", controller.creationErrorLabel1.getText());
    }

    @Test //createChar1
    public void testTooManyCharacters1() throws NameNotAvailableException, EmptyCharNameException, TooManyCharException, IOException {
        User.addCharacter("AAA", "AAA", "Male", "Human", "Blue", "Short");
        User.addCharacter("BBB", "BBB", "Male", "Human", "Blue", "Short");
        User.addCharacter("CCC", "CCC", "Male", "Human", "Blue", "Short");
        User.addCharacter("DDD", "DDD", "Male", "Human", "Blue", "Short");
        controller.createChar1(TEST_EVENT);
        assertEquals("You cannot add more than 4 characters!", controller.creationErrorLabel1.getText());
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

