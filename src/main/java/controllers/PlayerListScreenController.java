package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import services.User;

import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class PlayerListScreenController implements Initializable {

    public String l1, l2, l3, l4;

    @FXML
    public Label admin;

    @FXML
    public Pane pane1;

    @FXML
    public Pane pane2;

    @FXML
    public Pane pane3;

    @FXML
    public Pane pane4;

    @FXML
    public Pane pane5;

    @FXML
    public Label label1;

    @FXML
    public Label label2;

    @FXML
    public Label label3;

    @FXML
    public Label label4;

    @FXML
    public Label label5;

    @FXML
    public Button select1;

    @FXML
    public Button select2;

    @FXML
    public Button select3;

    @FXML
    public Button select4;

    @FXML
    public Button select5;

    public void buttonPl1(ActionEvent event) {

    }

    public void buttonPl2(ActionEvent event) {

    }

    public void buttonPl3(ActionEvent event) {

    }

    public void buttonPl4(ActionEvent event) {

    }

    public void buttonPl5(ActionEvent event) {

    }
    /*
    public void displayPl() {
        //try {
        JSONArray array = new JSONArray();
        array = User.displayPlayers();
        Iterator<JSONArray> iterator = array.iterator();
        for (int i = 0; i < array.size(); i++) {
            JSONArray obj3 = iterator.next();
            if (i == 0) {
                label1.setText((String) obj3.get("Username:"));
                l1 = (String) obj3.get("Username:");
                System.out.println(l1);
            }
            if (i == 1) {
                label2.setText((String) obj3.get("Username:"));
                l2 = (String) obj3.get("Username:");
                System.out.println(l2);
            }
            if (i == 2) {
                label3.setText((String) obj3.get("Username:"));
                l3 = (String) obj3.get("Username:");
                System.out.println(l3);
            }
            if (i == 3) {
                label4.setText((String) obj3.get("Username:"));
                l4 = (String) obj3.get("Username:");
                System.out.println(l4);
            }
        }
    }*/

    @Override
    public void initialize (URL location, ResourceBundle resources){
        admin.setText(LoginController.usernam);
    }
}
