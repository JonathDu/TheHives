/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import java.awt.Dimension;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import hive.model.game.Game;
import hive.thehives.TheHives;
import java.awt.Dimension;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Adeline
 */
public class InterfaceCharger extends Parent {


    public InterfaceCharger(Stage primaryStage, Controller controller) throws IOException {

        int height = (int) primaryStage.getHeight();
        int width = (int) primaryStage.getWidth();
        DropShadow shadow = new DropShadow();
        int tailleDeCase;
        if(width/8>height/6){
            tailleDeCase = height/6;
        }
        else{
            tailleDeCase = width/8;
        }
        int maxJoueur = (int) ((int) width/2.5);
        int minJoueur = maxJoueur/2;

        /*if(controller.pleinEcran==1){
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("Sortie de plein écran - esc");

        }*/

        
        String police = controller.getPolice();

        AnchorPane pane = new AnchorPane();
        pane.prefWidthProperty().bind(primaryStage.widthProperty());
        pane.prefHeightProperty().bind(primaryStage.heightProperty());

        CacheImage c = new CacheImage();
        Image fond;
        if(controller.typeTheme=="jour"){
            fond = c.getImage("Design/Fond/fondMontagne.png");
        }
        else{
            fond = c.getImage("Design/Fond/fondNuit.png");
        }
        ImageView fondIm = new ImageView(fond);
        fondIm.fitHeightProperty().bind(primaryStage.heightProperty());
        fondIm.fitWidthProperty().bind(primaryStage.widthProperty());
        AnchorPane.setRightAnchor(fondIm, (double) 0);
        AnchorPane.setLeftAnchor(fondIm, (double) 0);
        AnchorPane.setTopAnchor(fondIm, (double) 0);
        AnchorPane.setBottomAnchor(fondIm, (double) 0);
        pane.getChildren().add(fondIm);

        StackPane Preferences = new StackPane();
        Image preferences = c.getImage("Design/MenuPrincipaux/BouttonParametre.png");
        ImageView prefIm = new ImageView(preferences);
        prefIm.setFitHeight(tailleDeCase/2);
        prefIm.setFitWidth(tailleDeCase/2*1.07);
        Preferences.getChildren().add(prefIm);
        Preferences.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {


            Preferences p = new Preferences(primaryStage, controller, new CacheImage());
            pane.getChildren().add(p);
        });
        AnchorPane.setRightAnchor(Preferences, (double) tailleDeCase/2*1.07 + 15);
        AnchorPane.setTopAnchor(Preferences, (double) 5);
        pane.getChildren().add(Preferences);
        
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        double max_height = dimension.getHeight();
        double max_width = dimension.getWidth();
        StackPane Plein = new StackPane();
        Image plein = c.getImage("Design/MenuPrincipaux/pleinEcran.png");
        ImageView pleinIm = new ImageView(plein);
        pleinIm.setFitHeight(tailleDeCase/2);
        pleinIm.setFitWidth(tailleDeCase/2*1.07);
        Plein.getChildren().add(pleinIm);
        Plein.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
           if(controller.pleinEcran==0){
                primaryStage.setWidth(max_width);
                primaryStage.setHeight(max_height);
                controller.old_height=height;
                controller.old_width=width;
               try {
                   controller.goToChargerPartie();
               } catch (IOException ex) {
                   Logger.getLogger(InterfaceCharger.class.getName()).log(Level.SEVERE, null, ex);
               }
                controller.pleinEcran=1;
            }
            else{
                primaryStage.setWidth(controller.old_width);
                primaryStage.setHeight(controller.old_height);
               try {
                   controller.goToChargerPartie();
               } catch (IOException ex) {
                   Logger.getLogger(InterfaceCharger.class.getName()).log(Level.SEVERE, null, ex);
               }
                controller.pleinEcran=0;
            }
            //primaryStage.setFullScreen(true);
            //primaryStage.setFullScreenExitHint("Sortie de plein écran - esc");
        });
        AnchorPane.setRightAnchor(Plein, (double) 10);
        AnchorPane.setTopAnchor(Plein, (double) 5);
        pane.getChildren().add(Plein);

        StackPane Menu = new StackPane();
        Image menu = c.getImage("Design/FenetrePlateau/bouttonRetourMenu.png");
        ImageView menuIm = new ImageView(menu);
        menuIm.setFitHeight(tailleDeCase/2);
        menuIm.setFitWidth(tailleDeCase/2*1.07);
        Menu.getChildren().add(menuIm);
        Menu.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            controller.goToMenu();
        });
        AnchorPane.setLeftAnchor(Menu, (double) 5);
        AnchorPane.setTopAnchor(Menu, (double) 5);
        pane.getChildren().add(Menu);

        BorderPane choisir = new BorderPane();
        Label choix = new Label(); // Scegliere partita salvata, Gespeichertes Spiel wählen
        Button valider = new Button();

        valider.setText(controller.gestionnaireLangage.getText("text_valider"));
        choix.setText(controller.gestionnaireLangage.getText("text_choisir_partie"));

        
        choix.setFont(new Font(police, width/35));
        choix.setAlignment(Pos.CENTER);
        choix.setMinSize(width/60, 30);
        choix.setMaxSize(width/2, 70);

        final ComboBox parties = new ComboBox();
        for(int j=0; j< 10; j++){
            parties.getItems().add(j);
        }

        parties.setMaxSize(tailleDeCase*3, tailleDeCase/2);
        parties.setMinSize(tailleDeCase*3, tailleDeCase/2);
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


        StackPane valider_sp = new StackPane();
        valider.setFont(new Font(police, tailleDeCase*0.23));
        valider_sp.getChildren().add(valider);
        valider_sp.setMaxSize(tailleDeCase, 40);
        valider_sp.setMinSize(tailleDeCase, 40);
        valider_sp.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println(parties.getValue());
                Game game = controller.chargerGame("test.xml");
                controller.goToPlateau(game);
            }
        });
        if(height==max_height){
            AnchorPane.setBottomAnchor(valider_sp, (double) tailleDeCase*1.5);
        }else{
            AnchorPane.setBottomAnchor(valider_sp, (double) tailleDeCase);
        }
        //AnchorPane.setTopAnchor(valider, (double) height - 50);
        AnchorPane.setLeftAnchor(valider_sp, (double) width/2 -tailleDeCase);
        AnchorPane.setRightAnchor(valider_sp, (double) width/2 -tailleDeCase);
        pane.getChildren().add(valider_sp);

        this.getChildren().add(pane);
    }


    public void majRetourPreference()
    {
    }


}
