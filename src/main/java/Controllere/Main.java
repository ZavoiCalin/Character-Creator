package Controllere;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class Main extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
       // scene = new Scene(loadFXML("primary"));
        //scene.getStylesheets().add(Main.class.getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Player Menu");
        stage.show();
    }


    static void setRoot(String fxml) throws Exception {
        scene.setRoot(loadFXML(fxml));
    }


    private static Parent loadFXML(String fxml) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}