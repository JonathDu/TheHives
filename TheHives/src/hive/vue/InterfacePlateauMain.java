/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.model.game.DefaultGame;
import static hive.model.insects.InsectType.*;
import hive.model.players.PlayerCollection;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 * @author jonathan
 */
public class InterfacePlateauMain extends Parent {

    VBox pions;
    Label nomJoueur;

    public InterfacePlateauMain(PlayerCollection col, String nomJoueur, Color couleur, CacheImage c) {
        pions = new VBox();
        pions.setAlignment(Pos.TOP_CENTER);
        this.nomJoueur = new Label(nomJoueur);
        this.nomJoueur.setAlignment(Pos.BOTTOM_CENTER);
        
        col.get(QUEEN_BEE);
        
        
        
        
        BackgroundFill bf = new BackgroundFill(Color.GRAY, null, null);

        
        pions.setOpacity(1);
        pions.setBackground(new Background(bf));
        pions.setPrefHeight(100);
        pions.getChildren().add(new InterfacePions(couleur, col.get(QUEEN_BEE), QUEEN_BEE, c));
        pions.getChildren().add(new InterfacePions(couleur, col.get(GRASSHOPPER), GRASSHOPPER, c));
        pions.getChildren().add(new InterfacePions(couleur, col.get(BEETLE), BEETLE, c));

        
        pions.getChildren().add(this.nomJoueur);
        
        pions.getChildren().get(0).setOpacity(1);
        this.getChildren().add(pions);
    }
}
