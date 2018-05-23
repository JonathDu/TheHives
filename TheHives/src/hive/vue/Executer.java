/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import vue.ControleurAcceuil;

/**
 *
 * @author Adeline
 */
public class Executer extends Application {

    private Stage primaryStage;
    private Stage dialogStage;
    private BorderPane fond;
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("The Hive");
        //this.primaryStage.setFullScreen(true);
        this.primaryStage.setWidth(1600);
        this.primaryStage.setHeight(900);
        //this.primaryStage.getIcons().add(new Image("Ressources/dosCarte.jpg"));
        
        initRootLayout();
        
        choisir();  
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Executer.class.getResource("fond.fxml"));
            fond = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(fond);
            
scene.getStylesheets().add("style.css");
            
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void choisir() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Executer.class.getResource("choix.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();
            
            // Set person overview into the center of root layout.
            fond.setCenter(personOverview);
            
            // Give the controller access to the main app.
            ControleurAcceuil controller = loader.getController();
            controller.setMainApp(this);
            
            //Image imageC = new Image("/Ressources/SourisEpee.png");
            //primaryStage.getScene().setCursor(new ImageCursor(imageC , 100, 100));

            //Utils.playSound("MainTheme.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    
}
