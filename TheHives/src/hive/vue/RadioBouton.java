/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import java.awt.Dimension;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author Adeline
 */
class RadioBouton extends ToggleButton{
    Stage primaryStage;
    Controller controller;


    RadioBouton(Stage primaryStage, Controller controller) {
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

        String police;
        if(controller.langue == "Russe"){
            police = "Copperplate";
        }
        else{
            police = "Papyrus";
        }

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
            Image b_IA = c.getImage("niveau/Facile.png");
            if(controller.langue=="Français"){
                b_IA = c.getImage("niveau/Facile.png");
            }
            else if(controller.langue=="English"){
                b_IA = c.getImage("niveau/Easy.png");
            }
            else if(controller.langue=="Italiano"){
                b_IA = c.getImage("niveau/Facile.png");
            }
            else if(controller.langue=="Русский"){
                b_IA = c.getImage("niveau/Легкий.png");
            }
            else if(controller.langue=="Deutsch"){
                b_IA = c.getImage("niveau/Einfach.png");
            }
            /*bouton.setFont(new Font(police, tailleDeCase/7));
            bouton.setMinSize(tailleDeCase*0.8, 30);
            bouton.setMaxHeight(hauteurDeLigne*0.5);*/
            ImageView b_IAIm = new ImageView(b_IA);
            b_IAIm.setFitHeight(hauteurBouton);//largeurBouton/7.2375);
            b_IAIm.setFitWidth(largeurBouton);
            bouton.setGraphic(b_IAIm);
        }else if(type=="moyenne1" || type=="moyenne2"){
            bouton.setUserData("moyenne");
            Image b_IA = c.getImage("niveau/Moyenne.png");
            if(controller.langue=="Français"){
                b_IA = c.getImage("niveau/Moyenne.png");
            }
            else if(controller.langue=="English"){
                b_IA = c.getImage("niveau/Medium.png");
            }
            else if(controller.langue=="Italiano"){
                b_IA = c.getImage("niveau/Media.png");
            }
            else if(controller.langue=="Русский"){
                b_IA = c.getImage("niveau/Средний.png");
            }
            else if(controller.langue=="Deutsch"){
                b_IA = c.getImage("niveau/Normal.png");
            }
            ImageView b_IAIm = new ImageView(b_IA);
            b_IAIm.setFitHeight(hauteurBouton);
            b_IAIm.setFitWidth(largeurBouton);
            bouton.setGraphic(b_IAIm);
        }else if(type=="difficile1" || type=="difficile2"){
            bouton.setUserData("difficile");
            Image b_IA = c.getImage("niveau/Difficile.png");
            if(controller.langue=="Français"){
                b_IA = c.getImage("niveau/Difficile.png");
            }
            else if(controller.langue=="English"){
                b_IA = c.getImage("niveau/Hard.png");
            }
            else if(controller.langue=="Italiano"){
                b_IA = c.getImage("niveau/Difficile.png");
            }
            else if(controller.langue=="Русский"){
                b_IA = c.getImage("niveau/Сложный.png");
            }
            else if(controller.langue=="Deutsch"){
                b_IA = c.getImage("niveau/Schwer.png");
            }
            ImageView b_IAIm = new ImageView(b_IA);
            b_IAIm.setFitHeight(hauteurBouton);
            b_IAIm.setFitWidth(largeurBouton);
            bouton.setGraphic(b_IAIm);
        }else if(type=="jour"){
            bouton.setUserData("jour");
            if(controller.langue=="Français"){
            bouton.setText("Jour");
            }
            else if(controller.langue=="English"){
                bouton.setText("Day");
            }
            else if(controller.langue=="Italiano"){
                bouton.setText("Giorno");
            }
            else if(controller.langue=="Русский"){
                bouton.setText("День");
            }
            else if(controller.langue=="Deutsch"){
                bouton.setText("Tag");
            }
            bouton.setFont(new Font(police, tailleDeCase/7));
            bouton.setMinSize(tailleDeCase*0.8, 30);
            bouton.setMaxHeight(hauteurDeLigne*0.5);
        }else if(type=="nuit"){
            bouton.setUserData("nuit");
            if(controller.langue=="Français"){
            bouton.setText("Nuit");
            }
            else if(controller.langue=="English"){
                bouton.setText("Night");
            }
            else if(controller.langue=="Italiano"){
                bouton.setText("Notte");
            }
            else if(controller.langue=="Русский"){
                bouton.setText("Ночь");
            }
            else if(controller.langue=="Deutsch"){
                bouton.setText("Nacht");
            }
            bouton.setFont(new Font(police, tailleDeCase/7));
            bouton.setMinSize(tailleDeCase*0.8, 30);
            bouton.setMaxHeight(hauteurDeLigne*0.5);
        }
        return bouton;




    }

}
