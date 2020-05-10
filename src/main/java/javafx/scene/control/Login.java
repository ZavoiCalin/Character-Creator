package javafx.scene.control;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.stage.Stage;



public class Login extends Application {

    Stage window;

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage){

        window = primaryStage;
        window.setTitle("Login Window");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10));

        Text welcomeTxt = new Text("Welcome!");
        welcomeTxt.setFont(Font.font("Tahoma", FontWeight.LIGHT, 25));
        grid.add(welcomeTxt, 0, 0);

        Label lblUser = new Label("Username: ");
        grid.add(lblUser, 0 , 1);

        TextField txtUser = new TextField();
        txtUser.setPromptText("username");
        grid.add(txtUser, 1, 1);

        Label lblPassword = new Label("Password: ");
        grid.add(lblPassword, 0 , 2);

        PasswordField pwBox = new PasswordField();
        pwBox.setPromptText("password");
        grid.add(pwBox, 1, 2);

        Button loginBtn = new Button("Login");
        grid.add(loginBtn, 1, 3);
        loginBtn.setOnAction(e->{
            System.out.println("Login button pressed!");
        });

        Scene scene = new Scene(grid, 500, 500);
        window.setScene(scene);
        window.show();

    }

}
