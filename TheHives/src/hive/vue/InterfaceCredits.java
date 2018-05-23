/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import java.awt.Dimension;
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
        int tailleDeCase;
        if(width/8>height/6){
            tailleDeCase = height/6;
        }
        else{
            tailleDeCase = width/8;
        }
        int maxJoueur = (int) ((int) tailleDeCase*3.2);
        int minJoueur = maxJoueur/2;
       
        if(controller.pleinEcran==1){
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("Sortie de plein écran - esc");
        }
        String police;
        if(controller.langue == "Russe"){
            police = "Copperplate";
        }
        else{
            police = "Papyrus";
        }
        
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
            Preferences p = new Preferences(primaryStage, controller, "credits");
            pane.getChildren().add(p);
        });
        AnchorPane.setRightAnchor(Preferences, (double) tailleDeCase/2*1.07 + 25);
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
                controller.goToCredits();
                controller.pleinEcran=1;
            }
            else{
                primaryStage.setWidth(controller.old_width);
                primaryStage.setHeight(controller.old_height);
                controller.goToCredits();
                controller.pleinEcran=0;
            }
            //primaryStage.setFullScreen(true);
            //primaryStage.setFullScreenExitHint("Sortie de plein écran - esc");
        });
        AnchorPane.setRightAnchor(Plein, (double) 20);
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
        if(controller.langue=="Français"){
            credits.setText("Crèdits");
            ia.setText("Intelligence artificielle :  Coralie Durhône, Quentin Guerre-Berthelot");
            ihm.setText("Interactions homme-machine :  Adelina Prokhorova, Jonathan Dubois");
            design.setText("Design :  Coralie Durhône, Lenny Ancel");
            mdj.setText("Moteur de jeu :  Thomas Coggia, Lucas Touron");
        }
        else if(controller.langue=="English"){
            credits.setText("Credits");
            ia.setText("Artificial intelligence :  Coralie Durhône, Quentin Guerre-Berthelot");
            ihm.setText("Human–computer interaction :  Adelina Prokhorova, Jonathan Dubois");
            design.setText("Design :  Coralie Durhône, Lenny Ancel");
            mdj.setText("Game engine :  Thomas Coggia, Lucas Touron");
        }
        else if(controller.langue=="Italiano"){
            credits.setText("Crediti");
            ia.setText("Intelligenza artificiale :  Coralie Durhône, Quentin Guerre-Berthelot");
            ihm.setText("Interfaccia uomo-macchina :  Adelina Prokhorova, Jonathan Dubois");
            design.setText("Design :  Coralie Durhône, Lenny Ancel");
            mdj.setText("Motore di gioco :  Thomas Coggia, Lucas Touron");
        }
        else if(controller.langue=="Русский"){
            credits.setText("Разработчики");
            ia.setText("Искусственный интеллект :  Coralie Durhône, Quentin Guerre-Berthelot");
            ihm.setText("Человеко-компьютерный интерфейс :  Adelina Prokhorova, Jonathan Dubois");
            design.setText("Дизайн :  Coralie Durhône, Lenny Ancel");
            mdj.setText("Игровой движок :  Thomas Coggia, Lucas Touron");
        }
        else if(controller.langue=="Deutsch"){
            credits.setText("Credits");
            ia.setText("Künstliche Intelligenz :  Coralie Durhône, Quentin Guerre-Berthelot");
            ihm.setText("Mensch-Computer-Interaktion :  Adelina Prokhorova, Jonathan Dubois");
            design.setText("Design :  Coralie Durhône, Lenny Ancel");
            mdj.setText("Spiel-Engine :  Thomas Coggia, Lucas Touron");
        }
        credits.setFont(new Font(police, tailleDeCase*0.22));
        credits.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(credits, (double) tailleDeCase*0.6);
        AnchorPane.setLeftAnchor(credits, (double) tailleDeCase*2);
        AnchorPane.setRightAnchor(credits, (double) tailleDeCase*2);
        pane.getChildren().add(credits);
        
        StackPane bois = new StackPane();
        Image plateau = c.getImage("PlateauCentral.png");
        ImageView plateauIm = new ImageView(plateau); 
        plateauIm.setFitHeight((tailleDeCase*6.56)/1.6);
        plateauIm.setFitWidth(tailleDeCase*6.56);
        bois.getChildren().add(plateauIm);
        
        GridPane grille = new GridPane();
        int ligne = 100/6;
        int colonne = 100/1;
        Outils.fixerRepartition(grille, Outils.HORIZONTAL, ligne, ligne, ligne, ligne, ligne, ligne);
        Outils.fixerRepartition(grille, Outils.VERTICAL, colonne);
//        grille.prefHeightProperty().bind(primaryStage.heightProperty());
//        grille.prefWidthProperty().bind(primaryStage.widthProperty());
        grille.setMaxWidth(tailleDeCase*6.4);
        grille.setMinWidth(tailleDeCase*6.4);
        grille.setMaxHeight(tailleDeCase*4.2);
        grille.setMinHeight(tailleDeCase*4.2);
        double hauteurDeGrille = tailleDeCase*4.2;
        double hauteurDeLigne = hauteurDeGrille/6;
        
        ia.setFont(new Font(police, tailleDeCase*0.17));
        ia.setTextFill(Color.web("#fbe5b5"));
        ia.setAlignment(Pos.CENTER);
        StackPane ia_sp = new StackPane();
        ia_sp.getChildren().add(ia);
        grille.add(ia_sp, 0, 1);
        ihm.setFont(new Font(police, tailleDeCase*0.17));
        ihm.setTextFill(Color.web("#fbe5b5"));
        ihm.setAlignment(Pos.CENTER);
        StackPane ihm_sp = new StackPane();
        ihm_sp.getChildren().add(ihm);
        grille.add(ihm_sp, 0, 2);
        design.setFont(new Font(police, tailleDeCase*0.17));
        design.setTextFill(Color.web("#fbe5b5"));
        design.setAlignment(Pos.CENTER);
        StackPane design_sp = new StackPane();
        design_sp.getChildren().add(design);
        grille.add(design_sp, 0, 3);
        mdj.setFont(new Font(police, tailleDeCase*0.17));
        mdj.setTextFill(Color.web("#fbe5b5"));
        mdj.setAlignment(Pos.CENTER);
        StackPane mdj_sp = new StackPane();
        mdj_sp.getChildren().add(mdj);
        grille.add(mdj_sp, 0, 4);
        
        bois.getChildren().add(grille);
        
        AnchorPane.setTopAnchor(bois, (double) tailleDeCase*0.6);
        AnchorPane.setBottomAnchor(bois, (double) tailleDeCase*0.3);
        AnchorPane.setLeftAnchor(bois, (double) tailleDeCase*0.8);
        AnchorPane.setRightAnchor(bois, (double) tailleDeCase*0.8);
        pane.getChildren().add(bois);
        
        
        
        this.getChildren().add(pane);
    }
    
}
