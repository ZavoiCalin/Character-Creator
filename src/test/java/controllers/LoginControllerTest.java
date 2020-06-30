package controllers;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import services.User;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class LoginControllerTest extends ApplicationTest {

    public static final String TEST_USERTEXTFIELD = "Nonexistent username";

    public static final String TEST_PASSWORDTEXTFIELD = "Nonexistent password";

    private LoginController controller;

    private User u;

    private ActionEvent event = null;

    @Before
    public void setUp(){

        controller = new LoginController();

        controller.userTextField = new TextField();

        controller.passwordTextField = new PasswordField();

        controller.ErrorMessage = new Label();

        controller.userTextField.setText(TEST_USERTEXTFIELD);

        controller.passwordTextField.setText(TEST_PASSWORDTEXTFIELD);


    }

    @Test //handleLoginButtonAction
    public void testHandleLoginIncorrectCredentials() throws IOException {

        controller.handleLoginButtonAction(event);
        assertEquals("Boi, enter a correct username and p-word;)", controller.ErrorMessage.getText());

    }

}
