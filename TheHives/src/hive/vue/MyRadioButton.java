/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import java.awt.Dimension;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Font;

/**
 *
 * @author Adeline
 */
class MyRadioBouton extends ToggleButton {

    Scene scene;
    Controller controller;

    MyRadioBouton(Scene scene, Controller controller) {
        this.scene = scene;
        this.controller = controller;

    }

    public ToggleButton creer(String type) {
        int height = (int) scene.getHeight();
        int width = (int) scene.getWidth();

        Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        double max_height = dimension.getHeight();
        double max_width = dimension.getWidth();

        int tailleDeCase;

        if (width / 8 > height / 6) {
            tailleDeCase = height / 6;
        } else {
            tailleDeCase = width / 8;
        }
        double hauteurDeGrille = tailleDeCase * 4.2;
        double hauteurDeLigne = hauteurDeGrille / 4;
        double largeurDeGrille = width - 50;
        double largeurDeColonne = largeurDeGrille / 3;

        double largeurBouton;
        double hauteurBouton;
        if (width > max_width * 0.75) {
            largeurBouton = largeurDeColonne * 0.6;
        } else {
            largeurBouton = largeurDeColonne;
        }
        hauteurBouton = largeurBouton / 7.2375;

        CacheImage c = new CacheImage();

        String police = "Papyrus";

        ToggleButton bouton = new ToggleButton("");

        if (type == "humains") {
            bouton.setUserData("Humains");
        } else if (type == "h_IA") {
            bouton.setUserData("Humain_IA");
        } else if (type == "IA_h") {
            bouton.setUserData("IA_Humain");
        } else if (type == "IAs") {
            bouton.setUserData("IAs");
        } else if (type == "facile") {
            bouton.setUserData("facile");

        } else if (type == "moyenne") {
            bouton.setUserData("moyenne");

        } else if (type == "difficile") {
            bouton.setUserData("difficile");

        } else if (type == "jour") {
            bouton.setUserData("jour");
            bouton.setText(controller.gestionnaireLangage.getText("text_jour"));
            bouton.setFont(new Font(police, tailleDeCase / 7));
            bouton.setMinSize(tailleDeCase * 0.8, 30);
            bouton.setMaxHeight(hauteurDeLigne * 0.5);
        } else if (type == "nuit") {
            bouton.setUserData("nuit");
            bouton.setText(controller.gestionnaireLangage.getText("text_nuit"));
            bouton.setFont(new Font(police, tailleDeCase / 7));
            bouton.setMinSize(tailleDeCase * 0.8, 30);
            bouton.setMaxHeight(hauteurDeLigne * 0.5);
        }
        return bouton;
    }
}
