/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.thehives.TheHives;
import java.io.IOException;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Adeline
 */
public class InterfaceCharger extends Parent {

    
    public InterfaceCharger(Stage primaryStage, TheHives i) throws IOException {
        
        if(i.pleinEcran==1){
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("Sortie de plein écran - esc");
        }
        
        AnchorPane pane = new AnchorPane();
        pane.prefWidthProperty().bind(primaryStage.widthProperty());
        pane.prefHeightProperty().bind(primaryStage.heightProperty());
        
        int height = (int) primaryStage.getHeight();
        int width = (int) primaryStage.getWidth();
        int tailleDeCase = width/8;
        
        Group utiles3 = new Group();
        Bouton preferences = new Bouton(primaryStage, i, "preferences", pane, "charger");
        Bouton sortie = new Bouton(primaryStage, i, "sortie", pane, "charger");
        Bouton ecran = new Bouton(primaryStage, i, "ecran", pane, "charger");
        utiles3.getChildren().addAll(preferences, sortie, ecran);
        AnchorPane.setRightAnchor(utiles3, (double) 0);
        AnchorPane.setTopAnchor(utiles3, (double) 0);
        pane.getChildren().add(utiles3);
        
        Group utiles1 = new Group();
        Bouton menu = new Bouton(primaryStage, i, "menu", pane, "charger");
        utiles1.getChildren().addAll(menu);
        
        AnchorPane.setLeftAnchor(utiles1, (double) 0);
        AnchorPane.setTopAnchor(utiles1, (double) 0);
        pane.getChildren().add(utiles1);
        
        BorderPane choisir = new BorderPane();
        Label choix = new Label(); // Scegliere partita salvata, Gespeichertes Spiel wählen
        Button valider = new Button();
        if(i.langue=="Français"){
            choix.setText("Choisissez la partie à charger");
            valider.setText("Valider");
        }
        else if(i.langue=="English"){
            choix.setText("Choose the game to load");
            valider.setText("Commit");
        }
        else if(i.langue=="Italiano"){
            choix.setText("Scegliere partita salvata");
            valider.setText("Invio");
        }
        else if(i.langue=="Русский"){
            choix.setText("Выберите игру для загрузки");
            valider.setText("Подтвердить");
        }
        else if(i.langue=="Deutsch"){
            choix.setText("Gespeichertes Spiel wählen");
            valider.setText("Bestätigen");
        }
        choix.setFont(new Font("Copperplate", width/35));
        choix.setAlignment(Pos.CENTER);
        choix.setMinSize(width/60, 30);
        choix.setMaxSize(width/2, 70);
        
        final ComboBox parties = new ComboBox();
        for(int j=0; j< 10; j++){
            parties.getItems().add(j);
        }
       
        AnchorPane.setTopAnchor(choix, (double) height/10);
        AnchorPane.setLeftAnchor(choix, (double) tailleDeCase*2);
        AnchorPane.setRightAnchor(choix, (double) tailleDeCase*2);
        //AnchorPane.setBottomAnchor(choix, (double) height/1.1);
        pane.getChildren().add(choix);
        
        AnchorPane.setTopAnchor(parties, (double) height/4);
        AnchorPane.setLeftAnchor(parties, (double) tailleDeCase*2);
        AnchorPane.setRightAnchor(parties, (double) tailleDeCase*2);
        //AnchorPane.setBottomAnchor(parties, (double) height/1.3);
        pane.getChildren().add(parties);
        
        
        DropShadow shadow = new DropShadow();
        valider.setFont(new Font("Copperplate", width/35));
        valider.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent event) -> {
            valider.setEffect(shadow);
        });
        valider.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent event) -> {
            valider.setEffect(null);
        });
        valider.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Enregistrer ! ");
            }
        });
        valider.setMinHeight(20);
        //valider.setMaxHeight(11);
        AnchorPane.setBottomAnchor(valider, (double) 90);
        //AnchorPane.setTopAnchor(valider, (double) height - 100);
        AnchorPane.setLeftAnchor(valider, (double) tailleDeCase*3);
        AnchorPane.setRightAnchor(valider, (double) tailleDeCase*3);
        

        pane.getChildren().add(valider);
        
        
        
        
        
        
        this.getChildren().add(pane);
    }

   

   
    
}
