package javafx.scene.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.*;
import javafx.event.*;

import javax.swing.*;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Player Menu");
        Button ListChB=new Button("View list of Characters");
        Button CreateNChB=new Button("Create new Character");

        StackPane layout=new StackPane();
        //layout.getChildren().add(ListChB);
        layout.getChildren().addAll(ListChB, CreateNChB);
        Scene scene=new Scene(layout, 1000, 770);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
