package controllers;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class AdminCharacterListScreenControllerTest extends ApplicationTest {

    private AdminCharacterListScreenController controller;

    String pTest="src/main/resources/images/m-elf-green-long.png";

    @Before
    public void setUp(){

        controller = new AdminCharacterListScreenController();

        controller.selectedPlayer = new Label();
        controller.labelAd1 = new Label();
        controller.labelAd2 = new Label();
        controller.labelAd3 = new Label();
        controller.labelAd4 = new Label();
        controller.desc1 = new Label();
        controller.desc2 = new Label();
        controller.desc3 = new Label();
        controller.desc4 = new Label();

        controller.avatarImage11 = new ImageView();
        controller.avatarImage22 = new ImageView();
        controller.avatarImage33 = new ImageView();
        controller.avatarImage44 = new ImageView();

        controller.lab1 = new String();
        controller.lab2 = new String();
        controller.lab3 = new String();
        controller.lab4 = new String();

        controller.p = new String();

    }

    @Test // getPath
    public void testGetPath(){

        controller.getPath("Male","Elf","Green","Long");
        assertEquals(controller.p, pTest);
    }


}
