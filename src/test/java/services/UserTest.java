package services;

import controllers.CharacterCreationController;
import controllers.LoginController;
import exceptions.*;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class UserTest extends ApplicationTest {

    public static final String TEST_USER = "Username";

    public static final String TEST_PASSW = "Password";

    private User service;

    public static final String TEST_ENCODEDPASSWORD = "/MpNjJKYomNBPh9mdMZ9nA==";  // Encoded "GLHF"

    private ActionEvent event = null;

    private CharacterCreationController controller;

    private static JSONArray jsonArray = new JSONArray();

    @Before
    public void setUp() {

        service = new User();

        controller = new CharacterCreationController();

        service.user = new String();
        service.passw = new String();

        controller.nameAvatar = new TextField();
        controller.deletionKeyAvatar = new TextField();

        controller.nameAvatar.setText("asd");
        controller.deletionKeyAvatar.setText("asd");

        controller.genderComboBox = new ComboBox<>();
        controller.earsComboBox = new ComboBox<>();
        controller.eyeColorComboBox = new ComboBox<>();
        controller.hairstyleComboBox = new ComboBox<>();

        controller.genderComboBox.setValue("Female");
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
            jsonArray = arrayPlayer;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Test(expected = IncorrectCredentialsException.class) //checkIncorrect
    public void testCheckIncorrect() throws IncorrectCredentialsException{

        service.checkIncorrect();

    }

    @Test(expected = EmptyCharNameException.class) //checkIfFieldsAreEmpty
    public void testCheckIfFieldsAreEmpty() throws EmptyCharNameException {

        service.checkIfFieldsAreEmpty("asd","asd" ,"Male","Human" ,"Green" ,null);

    }

    @Test
    public void setTestEncodePassword(){

        assertEquals(TEST_ENCODEDPASSWORD, User.encodePassword(User.key, User.Vector, "GLHF"));

    }

    @Test // loginCheckPlayer
    public void testLoginCheckPlayer(){

        assertEquals("Player", User.loginCheckPlayer("Dr. D","GLHF"));

    }

    @Test // loginCheckAdmin
    public void testLoginCheckAdmin(){

        assertEquals("Admin", User.loginCheckAdmin("admin","admin"));

    }

    @Test(expected = WrongDeletionKeyException.class) //deletionKey
    public void testWrongDeletionKey() throws WrongDeletionKeyException, EmptyCharNameException, TooManyCharException {
        try {
            User.addCharacter("sda", "sda", "Male", "Human", "Blue", "Short");
        }
        catch(NameNotAvailableException e){}
        User.deletionkey("xy");
    }

    @Test(expected = WrongDeletionKeyException.class) //deletionKeyAdmin
    public void testWrongDeletionKeyAdmin() throws WrongDeletionKeyException, EmptyCharNameException, TooManyCharException {
        try {
            User.addCharacter("sda", "sda", "Male", "Human", "Blue", "Short");
        }
        catch(NameNotAvailableException e){}
        User.deletionkeyadmin("xy");
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
        arrayP = jsonArray;
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


