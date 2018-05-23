/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import java.awt.Dimension;
import hive.thehives.TheHives;
import java.util.ResourceBundle;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Adeline
 */
class MyRadioBouton extends ToggleButton{
    Stage primaryStage;
    Controller controller;


    MyRadioBouton(Stage primaryStage, Controller controller) {
        this.primaryStage = primaryStage;
        this.controller = controller;

    }

    public ToggleButton creer(String type){
        int height = (int) primaryStage.getHeight();
        int width = (int) primaryStage.getWidth();
        
        
        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        
        double max_height = dimension.getHeight();
        double max_width = dimension.getWidth();
        
        int tailleDeCase;
        
        if(width/8>height/6){
            tailleDeCase = height/6;
        }
        else{
            tailleDeCase = width/8;
        }
        double hauteurDeGrille = tailleDeCase*4.2;
        double hauteurDeLigne = hauteurDeGrille/4;
        double largeurDeGrille = width-50;
        double largeurDeColonne = largeurDeGrille/3; 

        double largeurBouton;
        double hauteurBouton;
        if(width>max_width*0.75){
            largeurBouton = largeurDeColonne*0.6;
        }
        else
            largeurBouton = largeurDeColonne;
        hauteurBouton=largeurBouton/7.2375;
        /*if(width>max_width/2){
            largeurBouton = width/5;
            hauteurBouton=largeurBouton/7.2375;
        }
        else if(height > max_height/2){
           
        }
        else{
            largeurBouton = tailleDeCase*2.5;
            hauteurBouton = largeurBouton/3;
        }*/
        
        //= width/5;
        
        CacheImage c = new CacheImage();

        String police = controller.getPolice();

        ToggleButton bouton = new ToggleButton("");

        if(type=="humains"){
            bouton.setUserData("Humains");
            Image humains = c.getImage("plusDeBoutons/plusDeBoutons/BoutonHumainVsHumain.png");
            ImageView humainsIm = new ImageView(humains);
            humainsIm.setFitHeight(hauteurDeLigne);
            humainsIm.setFitWidth(hauteurDeLigne);
            bouton.setGraphic(humainsIm);
        }
        else if(type=="h_IA"){
            bouton.setUserData("Humain_IA");
            Image h_ia = c.getImage("plusDeBoutons/plusDeBoutons/BoutonIAVsHumain.png");
            ImageView h_iaIm = new ImageView(h_ia);
            h_iaIm.setFitHeight(hauteurDeLigne);
            h_iaIm.setFitWidth(hauteurDeLigne);
            bouton.setGraphic(h_iaIm);
        }
        else if(type=="IAs"){
            bouton.setUserData("IAs");
            Image ia_ia = c.getImage("plusDeBoutons/plusDeBoutons/BoutonPersoRobotVsRobo.png");
            ImageView ia_iaIm = new ImageView(ia_ia);
            ia_iaIm.setFitHeight(hauteurDeLigne);
            ia_iaIm.setFitWidth(hauteurDeLigne);
            bouton.setGraphic(ia_iaIm);
        }else if(type=="facile1" || type=="facile2"){
            bouton.setUserData("facile");

            bouton.setText(controller.gestionnaireLangage.getText("text_facile"));
            bouton.setFont(new Font(police, tailleDeCase/7));
            bouton.setMinSize(width/10, 30);
            bouton.setMaxHeight(hauteurDeLigne*0.5);
        }else if(type=="moyenne1" || type=="moyenne2"){
            bouton.setUserData("moyenne");
            bouton.setText(controller.gestionnaireLangage.getText("text_moyen"));
            bouton.setFont(new Font(police, tailleDeCase/7));
            bouton.setMinSize(width/10, 30);
            bouton.setMaxHeight(hauteurDeLigne*0.5);
        }else if(type=="difficile1" || type=="difficile2"){
            bouton.setUserData("difficile");
            bouton.setText(controller.gestionnaireLangage.getText("text_difficile"));
            bouton.setFont(new Font(police, tailleDeCase/7));
            bouton.setMinSize(width/10, 30);
            bouton.setMaxHeight(hauteurDeLigne*0.5);
        }else if(type=="jour"){
            bouton.setUserData("jour");
            bouton.setText(controller.gestionnaireLangage.getText("text_jour"));
            bouton.setFont(new Font(police, tailleDeCase/7));
            bouton.setMinSize(tailleDeCase*0.8, 30);
            bouton.setMaxHeight(hauteurDeLigne*0.5);
        }else if(type=="nuit"){
            bouton.setUserData("nuit");
            bouton.setText(controller.gestionnaireLangage.getText("text_nuit"));
            bouton.setFont(new Font(police, tailleDeCase/7));
            bouton.setMinSize(tailleDeCase*0.8, 30);
            bouton.setMaxHeight(hauteurDeLigne*0.5);
        }
        return bouton;
    }
}