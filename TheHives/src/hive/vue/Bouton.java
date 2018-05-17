/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.thehives.TheHives;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Adeline
 */
class Bouton  extends Parent {

    public Bouton(Stage primaryStage, TheHives i, String type) {
        
        int height = (int) primaryStage.getHeight();
        int width = (int) primaryStage.getWidth();
        
        Image imageCase = new Image(getClass().getResourceAsStream("rsc/images/case.png"));
        DropShadow shadow = new DropShadow();
        int tailleDeCase = width/8;
        
        StackPane bouton = new StackPane();
        ImageView caseIm = new ImageView(imageCase);
        caseIm.setFitHeight(tailleDeCase);
        caseIm.setFitWidth(tailleDeCase);
        bouton.getChildren().add(caseIm);
        
        if(type=="menu"){
            
            //Image imageMenu = new Image(getClass().getResourceAsStream("Images/menu1.png"));
            Image image = new Image(getClass().getResourceAsStream("rsc/images/menu2.png"));
            //Image imageMenu = new Image(getClass().getResourceAsStream("Images/settings3.png"));
            ImageView Im = new ImageView(image);
            Im.setFitHeight(tailleDeCase/2.5);
            Im.setFitWidth(tailleDeCase/2.5);
            bouton.getChildren().add(Im);
            bouton.setLayoutX(0);
            bouton.setLayoutY(0);
            /*bouton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent event) -> {
                bouton.setEffect(shadow);
            });
            bouton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent event) -> {
                bouton.setEffect(null);
            });*/
            bouton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                System.out.println("Menu ! ");
                i.goToMenu();
            });
            
            
        }
        else if(type=="preferences"){
            Image image = new Image(getClass().getResourceAsStream("rsc/images/settings1.png"));
            //Image imageSortie = new Image(getClass().getResourceAsStream("Images/settings2.png"));
            //Image imageSortie = new Image(getClass().getResourceAsStream("Images/settings3.png"));
            ImageView Im = new ImageView(image);
            Im.setFitHeight(tailleDeCase/2.5);
            Im.setFitWidth(tailleDeCase/2.5);
            bouton.getChildren().add(Im);
            bouton.setLayoutX(width-tailleDeCase*2);
            bouton.setLayoutY(0);
            /*bouton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent event) -> {
                bouton.setEffect(shadow);
            });
            bouton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent event) -> {
                bouton.setEffect(null);
            });*/
            bouton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                System.out.println("Préférences ! ");
                //i.accueil();
            });
        }
        else if(type=="sortie"){
            //Image imageSortie = new Image(getClass().getResourceAsStream("rsc/images/exit1.png"));
            //Image imageSortie = new Image(getClass().getResourceAsStream("rsc/images/exit2.png"));
            Image image = new Image(getClass().getResourceAsStream("rsc/images/exit3.png"));
            ImageView Im = new ImageView(image);
            Im.setFitHeight(tailleDeCase/2.5);
            Im.setFitWidth(tailleDeCase/2.5);
            bouton.getChildren().add(Im);
            bouton.setLayoutX(width-tailleDeCase);
            bouton.setLayoutY(0);
            /*bouton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent event) -> {
                bouton.setEffect(shadow);
            });
            bouton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent event) -> {
                bouton.setEffect(null);
            });*/
            bouton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                System.out.println("Sortie ! ");
                //i.accueil();
                Platform.exit();
            });
        }
        else if(type=="ecran"){
            Image image = new Image(getClass().getResourceAsStream("rsc/images/full.png"));
            ImageView Im = new ImageView(image);
            Im.setFitHeight(tailleDeCase/2.5);
            Im.setFitWidth(tailleDeCase/2.5);
            bouton.getChildren().add(Im);
            bouton.setLayoutX(width-tailleDeCase*1.5);
            bouton.setLayoutY(tailleDeCase/1.33);
           /* bouton.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent event) -> {
                bouton.setEffect(shadow);
            });
            bouton.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent event) -> {
                bouton.setEffect(null);
            });*/
            bouton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                System.out.println("Plein Écran ! ");
                primaryStage.setFullScreen(true);
                primaryStage.setFullScreenExitHint("Sortie de plein écran - esc");
            });
        }
        
        
        
        
        
        this.getChildren().add(bouton);
        
        
    }
}
