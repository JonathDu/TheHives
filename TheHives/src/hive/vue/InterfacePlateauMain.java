/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.gamescene.game.GameController;
import hive.controller.gamescene.game.TileHandler;
import hive.model.board.Tile;
import hive.model.game.DefaultGame;
import static hive.model.insects.InsectType.*;
import hive.model.players.PlayerCollection;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyProperty;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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

    public InterfacePlateauMain(PlayerCollection col, String nomJoueur, Color couleur, CacheImage c, ReadOnlyProperty property, GameController controller) {
        pions = new VBox();
        pions.setAlignment(Pos.TOP_CENTER);
        this.nomJoueur = new Label(nomJoueur);
        this.nomJoueur.setAlignment(Pos.BOTTOM_CENTER);
        pions.prefHeightProperty().bind(property);
        
        
        
        
        
        BackgroundFill bf = new BackgroundFill(Color.GRAY, null, null);

        
        pions.setOpacity(1);
        pions.setBackground(new Background(bf));
        //pions.setPrefHeight(100);
        InterfacePions pileQueenBee = new InterfacePions(couleur, col.get(QUEEN_BEE), QUEEN_BEE, c);
        InterfacePions pileGrassHopper = new InterfacePions(couleur, col.get(GRASSHOPPER), GRASSHOPPER, c);
        InterfacePions pileBeetle = new InterfacePions(couleur, col.get(BEETLE), BEETLE, c);
        
        pileQueenBee.addEventHandler(MouseEvent.MOUSE_CLICKED, new TileHandler(controller, this, QUEEN_BEE));
        pileGrassHopper.addEventHandler(MouseEvent.MOUSE_CLICKED, new TileHandler(controller, this, GRASSHOPPER));
        pileBeetle.addEventHandler(MouseEvent.MOUSE_CLICKED, new TileHandler(controller, this, BEETLE));
        
        
        
        pions.getChildren().add(pileQueenBee);
        pions.getChildren().add(pileGrassHopper);
        pions.getChildren().add(pileBeetle);

        
        pions.getChildren().add(this.nomJoueur);
        
        pions.getChildren().get(0).setOpacity(1);
        this.getChildren().add(pions);
    }
    
    public void surlignerTile(Tile tile)
    {
        
    }
    
    public void desurlignerTile(Tile tile)
    {
        
    }
}
