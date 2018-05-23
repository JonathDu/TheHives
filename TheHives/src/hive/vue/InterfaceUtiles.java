/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import hive.thehives.TheHives;
import javafx.application.Platform;

/**
 *
 * @author Adeline
 */
public class InterfaceUtiles extends Parent{

    public InterfaceUtiles(Stage primaryStage, Controller controller) {
        
        int height = (int) primaryStage.getHeight();
        int width = (int) primaryStage.getWidth();
        
        Image imageCase = new Image(getClass().getResourceAsStream("rsc/images/case.png"));
        DropShadow shadow = new DropShadow();
        int tailleDeCase = width/8;
        int tailleDeCase2 = tailleDeCase/2;
        
        Group Utiles = new Group();
        StackPane Sortie = new StackPane();
        ImageView caseImR = new ImageView(imageCase);
        caseImR.setFitHeight(tailleDeCase);
        caseImR.setFitWidth(tailleDeCase);
        Sortie.getChildren().add(caseImR);
        //Image imageSortie = new Image(getClass().getResourceAsStream("rsc/images/exit1.png"));
        //Image imageSortie = new Image(getClass().getResourceAsStream("rsc/images/exit2.png"));
        Image imageSortie = new Image(getClass().getResourceAsStream("rsc/images/exit3.png"));
        ImageView sortieIm = new ImageView(imageSortie);
        sortieIm.setFitHeight(tailleDeCase/2.5);
        sortieIm.setFitWidth(tailleDeCase/2.5);
        Sortie.getChildren().add(sortieIm);
        Sortie.setLayoutX(width-tailleDeCase);
        Sortie.setLayoutY(0);
        Sortie.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent event) -> {
            Sortie.setEffect(shadow);
        });
        Sortie.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent event) -> {
            Sortie.setEffect(null);
        });
        Sortie.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            System.out.println("Sortie ! ");
            //i.accueil();
            Platform.exit();
        });
        
            
        Utiles.getChildren().add(Sortie);
        StackPane PleinEcran = new StackPane();
        ImageView caseImPE = new ImageView(imageCase);
        caseImPE.setFitHeight(tailleDeCase);
        caseImPE.setFitWidth(tailleDeCase);
        PleinEcran.getChildren().add(caseImPE);
        Image imagePE = new Image(getClass().getResourceAsStream("rsc/images/full.png"));
        ImageView pleinEcranIm = new ImageView(imagePE);
        pleinEcranIm.setFitHeight(tailleDeCase/2.5);
        pleinEcranIm.setFitWidth(tailleDeCase/2.5);
        PleinEcran.getChildren().add(pleinEcranIm);
        PleinEcran.setLayoutX(width-tailleDeCase*1.5);
        PleinEcran.setLayoutY(tailleDeCase/1.33);
        PleinEcran.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent event) -> {
            PleinEcran.setEffect(shadow);
        });
        PleinEcran.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent event) -> {
            PleinEcran.setEffect(null);
        });
        PleinEcran.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            System.out.println("Plein Écran ! ");
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("Sortie de plein écran - esc");
        });
        Utiles.getChildren().add(PleinEcran);
        
        StackPane Preferences = new StackPane();
        ImageView caseImP = new ImageView(imageCase);
        caseImP.setFitHeight(tailleDeCase);
        caseImP.setFitWidth(tailleDeCase);
        Preferences.getChildren().add(caseImP);
        Image imagePreferences = new Image(getClass().getResourceAsStream("rsc/images/settings1.png"));
        //Image imageSortie = new Image(getClass().getResourceAsStream("Images/settings2.png"));
        //Image imageSortie = new Image(getClass().getResourceAsStream("Images/settings3.png"));
        ImageView PreferencesIm = new ImageView(imagePreferences);
        PreferencesIm.setFitHeight(tailleDeCase/2.5);
        PreferencesIm.setFitWidth(tailleDeCase/2.5);
        Preferences.getChildren().add(PreferencesIm);
        Preferences.setLayoutX(width-tailleDeCase*2);
        Preferences.setLayoutY(0);
        Preferences.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent event) -> {
            Preferences.setEffect(shadow);
        });
        Preferences.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent event) -> {
            Preferences.setEffect(null);
        });
        Preferences.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            System.out.println("Préférences ! ");
            //i.accueil();
        });
        Utiles.getChildren().add(Preferences);
        
        StackPane Menu = new StackPane();
        ImageView caseImM = new ImageView(imageCase);
        caseImM.setFitHeight(tailleDeCase);
        caseImM.setFitWidth(tailleDeCase);
        Menu.getChildren().add(caseImM);
        //Image imageMenu = new Image(getClass().getResourceAsStream("Images/menu1.png"));
        Image imageMenu = new Image(getClass().getResourceAsStream("rsc/images/menu2.png"));
        //Image imageMenu = new Image(getClass().getResourceAsStream("Images/settings3.png"));
        ImageView MenuIm = new ImageView(imageMenu);
        MenuIm.setFitHeight(tailleDeCase/2.5);
        MenuIm.setFitWidth(tailleDeCase/2.5);
        Menu.getChildren().add(MenuIm);
        Menu.setLayoutX(0);
        Menu.setLayoutY(0);
        Menu.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent event) -> {
            Menu.setEffect(shadow);
        });
        Menu.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent event) -> {
            Menu.setEffect(null);
        });
        Menu.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            System.out.println("Menu ! ");
            controller.goToMenu();
        });
        Utiles.getChildren().add(Menu);
        this.getChildren().add(Utiles);
        
    }
}
