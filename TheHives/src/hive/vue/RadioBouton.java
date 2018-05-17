/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.thehives.TheHives;
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
    TheHives i;
    
    
    RadioBouton(Stage primaryStage, TheHives i) {
        this.primaryStage = primaryStage;
        this.i = i;
        
    }
    
    public ToggleButton creer(String type){   
        int height = (int) primaryStage.getHeight();
        int width = (int) primaryStage.getWidth();
        int tailleDeCase = width/8;
        double hauteurDeGrille = height*0.7;
        double hauteurDeLigne = hauteurDeGrille/6;
        ToggleButton bouton = new ToggleButton("");
            
        if(type=="humain1" || type=="humain2"){
            bouton.setUserData("Humain");
            Image imageHumain = new Image("hive/vue/rsc/images/humain5.png");
            ImageView humainIm = new ImageView(imageHumain);
            humainIm.setFitHeight(hauteurDeLigne);
            humainIm.setFitWidth(hauteurDeLigne);
            bouton.setGraphic(humainIm);
            bouton.setFont(new Font("Copperplate", tailleDeCase/5));
            /*if(type=="humain1"){
                bouton.setLayoutX(width/2-width/4);
                bouton.setLayoutY(height/4);
            }
            else{
                bouton.setLayoutX(width/2-width/4);
                bouton.setLayoutY(height/1.8);
            }*/
        }
        else if(type=="IA1" || type=="IA2"){
            bouton.setUserData("IA");
            Image imageIA = new Image("hive/vue/rsc/images/ai2.png");
            ImageView IAIm = new ImageView(imageIA);
            IAIm.setFitHeight(hauteurDeLigne);
            IAIm.setFitWidth(hauteurDeLigne);
            bouton.setGraphic(IAIm);
            bouton.setFont(new Font("Copperplate", tailleDeCase/5));
            /*if(type=="IA1"){
                bouton.setLayoutX(width/2+width/4-width/10);
                bouton.setLayoutY(height/4);
            }
            else{
                bouton.setLayoutX(width/2+width/4-width/10);
                bouton.setLayoutY(height/1.8);
            }*/
        }else if(type=="facile1" || type=="facile2"){
            bouton.setUserData("facile");
            if(i.langue=="Français"){
                bouton.setText("Facile");
            }
            else if(i.langue=="English"){
                bouton.setText("Easy");
            }
            else if(i.langue=="Italiano"){
                bouton.setText("Facile");
            }
            else if(i.langue=="Русский"){
                bouton.setText("Легкий");
            }
            else if(i.langue=="Deutsch"){
                bouton.setText("Einfach");
            }
            bouton.setFont(new Font("Copperplate", tailleDeCase/7));
            bouton.setMinSize(width/10, 30);
            bouton.setMaxHeight(hauteurDeLigne*0.5);
            /*if(type=="facile1"){
                bouton.setLayoutX(width/2-width/4);
                bouton.setLayoutY(height/4+width/9);
            }
            else{
                bouton.setLayoutX(width/2-width/4);
                bouton.setLayoutY(height/1.8+width/9);
            }*/
        }else if(type=="moyenne1" || type=="moyenne2"){
            bouton.setUserData("moyenne");
            if(i.langue=="Français"){
                bouton.setText("Moyenne");
            }
            else if(i.langue=="English"){
                bouton.setText("Medium");
            }
            else if(i.langue=="Italiano"){
                bouton.setText("Media");
            }
            else if(i.langue=="Русский"){
                bouton.setText("Средний");
            }
            else if(i.langue=="Deutsch"){
                bouton.setText("Normal");
            }
            bouton.setFont(new Font("Copperplate", tailleDeCase/7));
            bouton.setMinSize(width/10, 30);
            bouton.setMaxHeight(hauteurDeLigne*0.5);
            /*if(type=="facile1"){
                bouton.setLayoutX(width/2);
                bouton.setLayoutY(height/4+width/9);
            }
            else{
                bouton.setLayoutX(width/2);
                bouton.setLayoutY(height/1.8+width/9);
            }*/
        }else if(type=="difficile1" || type=="difficile2"){
            bouton.setUserData("difficile");
            if(i.langue=="Français"){
            bouton.setText("Difficile");
            }
            else if(i.langue=="English"){
                bouton.setText("Hard");
            }
            else if(i.langue=="Italiano"){
                bouton.setText("Difficile");
            }
            else if(i.langue=="Русский"){
                bouton.setText("Сложный");
            }
            else if(i.langue=="Deutsch"){
                bouton.setText("Schwer");
            }
            bouton.setFont(new Font("Copperplate", tailleDeCase/7));
            bouton.setMinSize(width/10, 30);
            bouton.setMaxHeight(hauteurDeLigne*0.5);
            /*if(type=="difficile1"){
                bouton.setLayoutX(width/2+width/4);
                bouton.setLayoutY(height/4+width/9);
            }
            else{
                bouton.setLayoutX(width/2+width/4);
                bouton.setLayoutY(height/1.8+width/9);
            }*/
        }
        return bouton;
    
    
    
    
    }
    
}
