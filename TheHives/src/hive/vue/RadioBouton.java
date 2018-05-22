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
        double hauteurDeLigne = hauteurDeGrille/3.5;
        
        CacheImage c = new CacheImage();
        
        String police;
        if(i.langue == "Russe"){
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
            bouton.setFont(new Font(police, tailleDeCase/7));
            bouton.setMinSize(width/10, 30);
            bouton.setMaxHeight(hauteurDeLigne*0.5);
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
            bouton.setFont(new Font(police, tailleDeCase/7));
            bouton.setMinSize(width/10, 30);
            bouton.setMaxHeight(hauteurDeLigne*0.5);
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
            bouton.setFont(new Font(police, tailleDeCase/7));
            bouton.setMinSize(width/10, 30);
            bouton.setMaxHeight(hauteurDeLigne*0.5);
        }else if(type=="jour"){
            bouton.setUserData("jour");
            if(i.langue=="Français"){
            bouton.setText("Jour");
            }
            else if(i.langue=="English"){
                bouton.setText("Day");
            }
            else if(i.langue=="Italiano"){
                bouton.setText("Giorno");
            }
            else if(i.langue=="Русский"){
                bouton.setText("День");
            }
            else if(i.langue=="Deutsch"){
                bouton.setText("Tag");
            }
            bouton.setFont(new Font(police, tailleDeCase/7));
            bouton.setMinSize(width/10, 30);
            bouton.setMaxHeight(hauteurDeLigne*0.5);
        }else if(type=="nuit"){
            bouton.setUserData("nuit");
            if(i.langue=="Français"){
            bouton.setText("Nuit");
            }
            else if(i.langue=="English"){
                bouton.setText("Night");
            }
            else if(i.langue=="Italiano"){
                bouton.setText("Notte");
            }
            else if(i.langue=="Русский"){
                bouton.setText("Ночь");
            }
            else if(i.langue=="Deutsch"){
                bouton.setText("Nacht");
            }
            bouton.setFont(new Font(police, tailleDeCase/7));
            bouton.setMinSize(width/10, 30);
            bouton.setMaxHeight(hauteurDeLigne*0.5);
        }
        return bouton;
    
    
    
    
    }
    
}
