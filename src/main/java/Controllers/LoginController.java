package Controllers;

import Exceptions.IncorrectCredentialsException;
import Services.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    public TextField userTextField;
    @FXML
    public PasswordField passwordTextField;
    @FXML
    public Button LoginButton;
    @FXML
    public Label ErrorMessage;

    public static String usernam;

    @Override
    public void initialize(URL location, ResourceBundle resources) { }

  /*  @FXML
    public void handleSignUpButtonAction(MouseEvent event) throws IOException
    {
        //Load Signup Client Screen
        FXMLLoader loader= new FXMLLoader(getClass().getClassLoader().getResource("SignUpClient.fxml"));
        Parent root=loader.load();
        SignUpClientController scene2Controller = loader.getController();
        //make close button invisible
        scene2Controller.closeBtn.setVisible(false);
        //load signup screen where de login picture is
        root.translateXProperty().set(-1 * 489);

        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        //incrementeaza coordonata X
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.millis(950), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> { parentContainer.getChildren().remove(anchorRoot); });
        timeline.play();
        //make close button visibile again
        timeline.setOnFinished(e ->scene2Controller.closeBtn.setVisible(true));



    }*/

    @FXML
    public void handleLoginButtonAction(ActionEvent event) throws IOException, IncorrectCredentialsException {
        try {
            //verify if the user is a Player
            if((User.loginCheckPlayer(userTextField.getText(),passwordTextField.getText()).equals("Player")))
            {
                //switch to home screen
                Parent home_page_parent = FXMLLoader.load(getClass().getClassLoader().getResource("CharacterListScreen.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();
                usernam = userTextField.getText();
            }
            //Verify if the user is a Admin
            if((User.loginCheckAdmin(userTextField.getText(),passwordTextField.getText()).equals("Admin")))
            {
                //switch to home screen
                Parent home_page_parent = FXMLLoader.load(getClass().getClassLoader().getResource("PlayerListScreen.fxml"));
                Scene home_page_scene = new Scene(home_page_parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(home_page_scene);
                app_stage.show();
                usernam = userTextField.getText();

            }
            //if the user is none of the above throw exception
            else
            {
                User.checkIncorrect();
            }

        }
        catch (IncorrectCredentialsException e)
        {
            //error if not all fields are completed
            ErrorMessage.setText(e.getMessage());
        }

    }


}
