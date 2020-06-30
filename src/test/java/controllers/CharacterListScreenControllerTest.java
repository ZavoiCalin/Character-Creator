package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import services.User;

import static org.junit.Assert.assertEquals;

public class CharacterListScreenControllerTest extends ApplicationTest {

    private CharacterListScreenController controller;

    String pTest="src/main/resources/images/f-hobbit-brown-short.png";

    private ActionEvent event = null;

    private User u;

    @Before
    public void setUp(){

        controller = new CharacterListScreenController();

        controller.player = new Label();
        controller.labelCh1 = new Label();
        controller.labelCh2 = new Label();
        controller.labelCh3 = new Label();
        controller.labelCh4 = new Label();
        controller.errorDeleteLabel = new Label();

        controller.avatarImage = new ImageView();
        controller.avatarImage1 = new ImageView();
        controller.avatarImage2 = new ImageView();
        controller.avatarImage3 = new ImageView();

        controller.l1 = new String();
        controller.l2 = new String();
        controller.l3 = new String();
        controller.l4 = new String();

        controller.p = new String();

        controller.deletionKey = new TextField();

        LoginController.usernam = "Dr. D";

    }

    @Test // getPath
    public void testGetPath(){

        controller.getPath("Female","Hobbit","Brown","Short");
        assertEquals(controller.p, pTest);
    }

    @Test // handleDelete
    public void handleDeleteWrongKey(){

        controller.handleDelete(event);
        assertEquals("This deletion key is either wrong or does not exist!", controller.errorDeleteLabel.getText());

    }

}
