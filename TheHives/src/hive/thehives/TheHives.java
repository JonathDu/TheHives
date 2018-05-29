/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.thehives;

import hive.controller.Controller;
import hive.vue.CacheImage;
import java.awt.Dimension;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Adeline
 */
public class TheHives extends Application
{

    public int HEIGHT = 700;
    public int WIDTH = 800;
    Dimension screenSize;
    Group root;
    Scene scene;
    Stage primaryStage;
    CacheImage cache;

    @Override
    public void start(Stage _primaryStage) throws Exception
    {          
        cache = new CacheImage();
        root = new Group();
        scene = new Scene(root, WIDTH, HEIGHT);
        screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        primaryStage = _primaryStage;

        setMouseImage();
        setPrimaryStage();

        primaryStage.show();

        Controller controller = new Controller(primaryStage, scene, cache, screenSize);
    }

    private void setMouseImage()
    {
        Image souris = cache.getImage("souris.png");
        ImageCursor sourisIm = new ImageCursor(souris, souris.getWidth() / 2, souris.getHeight() / 2);
        scene.setCursor(sourisIm);
    }

    private void setPrimaryStage()
    {
        primaryStage.setHeight(HEIGHT);
        primaryStage.setWidth(WIDTH);
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(600);
        primaryStage.setTitle("The Hives");
        primaryStage.sizeToScene();
    }

    public static void main(String[] args)
    {
        Application.launch(TheHives.class, args);
    }
}
