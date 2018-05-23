/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import hive.thehives.TheHives;
import hive.vue.CacheImage;
import hive.vue.Preferences;
import java.util.ResourceBundle;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Adeline
 */
public class InterfaceCredits extends Parent {

    public InterfaceCredits(Stage primaryStage, Controller controller) {
        
        int height = (int) primaryStage.getHeight();
        int width = (int) primaryStage.getWidth();
        DropShadow shadow = new DropShadow();
        int tailleDeCase = width/8;
        int maxJoueur = (int) ((int) width/2.5);
        int minJoueur = maxJoueur/2;
       
        if(controller.pleinEcran==1){
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("Sortie de plein écran - esc");
        }
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
        
        StackPane Plein = new StackPane();
        Image plein = c.getImage("Design/MenuPrincipaux/pleinEcran.png");
        ImageView pleinIm = new ImageView(plein); 
        pleinIm.setFitHeight(tailleDeCase/2);
        pleinIm.setFitWidth(tailleDeCase/2*1.07);
        Plein.getChildren().add(pleinIm);
        Plein.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            controller.pleinEcran=1;
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("Sortie de plein écran - esc");    
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
        
        Label credits = new Label();
        Label ia = new Label();
        Label ihm = new Label();
        Label design = new Label();
        Label mdj = new Label();
        
        credits.setText(controller.gestionnaireLangage.getText("text_credit"));
        ia.setText(controller.gestionnaireLangage.getText("text_membres_IA"));
        ihm.setText(controller.gestionnaireLangage.getText("text_membres_IHM"));
        design.setText(controller.gestionnaireLangage.getText("text_membres_design"));
        mdj.setText(controller.gestionnaireLangage.getText("text_membres_moteur"));
        
        credits.setFont(new Font(police, width/35));
        credits.setAlignment(Pos.CENTER);
        credits.setMinSize(width/60, 30);
        credits.setMaxSize(width/2, 70);
        AnchorPane.setTopAnchor(credits, (double) height/10);
        AnchorPane.setLeftAnchor(credits, (double) tailleDeCase*2);
        AnchorPane.setRightAnchor(credits, (double) tailleDeCase*2);
        pane.getChildren().add(credits);
        
        StackPane bois = new StackPane();
        Image plateau = c.getImage("PlateauCentral.png");
        ImageView plateauIm = new ImageView(plateau); 
        plateauIm.setFitHeight((width*0.82)/1.6);
        plateauIm.setFitWidth(width*0.82);
        bois.getChildren().add(plateauIm);
        
        GridPane grille = new GridPane();
        int ligne = 100/6;
        int colonne = 100/1;
        Outils.fixerRepartition(grille, Outils.HORIZONTAL, ligne, ligne, ligne, ligne, ligne, ligne);
        Outils.fixerRepartition(grille, Outils.VERTICAL, colonne);
//        grille.prefHeightProperty().bind(primaryStage.heightProperty());
//        grille.prefWidthProperty().bind(primaryStage.widthProperty());
        grille.setMaxWidth(width*0.8);
        grille.setMinWidth(width*0.8);
        grille.setMaxHeight(height*0.7);
        grille.setMinHeight(height*0.7);
        double hauteurDeGrille = height*0.7;
        double hauteurDeLigne = hauteurDeGrille/6;
        
        ia.setFont(new Font(police, width/45));
        ia.setTextFill(Color.web("#fbe5b5"));
        ia.setAlignment(Pos.CENTER);
        StackPane ia_sp = new StackPane();
        ia_sp.getChildren().add(ia);
        grille.add(ia_sp, 0, 1);
        ihm.setFont(new Font(police, width/45));
        ihm.setTextFill(Color.web("#fbe5b5"));
        ihm.setAlignment(Pos.CENTER);
        StackPane ihm_sp = new StackPane();
        ihm_sp.getChildren().add(ihm);
        grille.add(ihm_sp, 0, 2);
        design.setFont(new Font(police, width/45));
        design.setTextFill(Color.web("#fbe5b5"));
        design.setAlignment(Pos.CENTER);
        StackPane design_sp = new StackPane();
        design_sp.getChildren().add(design);
        grille.add(design_sp, 0, 3);
        mdj.setFont(new Font(police, width/45));
        mdj.setTextFill(Color.web("#fbe5b5"));
        mdj.setAlignment(Pos.CENTER);
        StackPane mdj_sp = new StackPane();
        mdj_sp.getChildren().add(mdj);
        grille.add(mdj_sp, 0, 4);
        
        bois.getChildren().add(grille);
        
        AnchorPane.setTopAnchor(bois, (double) height/10);
        AnchorPane.setBottomAnchor(bois, (double) height/20);
        AnchorPane.setLeftAnchor(bois, (double) width*0.1);
        AnchorPane.setRightAnchor(bois, (double) width*0.1);
        pane.getChildren().add(bois);
        
        
        
        this.getChildren().add(pane);
    }
    
    public void majRetourPreference()
    {
    }
    
}
