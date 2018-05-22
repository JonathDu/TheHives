/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.plateauscene.game.GameController;
import hive.controller.plateauscene.game.mousehandlers.TileMainHandler;
import hive.model.board.Tile;
import hive.model.insects.InsectType;
import hive.model.players.PlayerCollection;
import hive.model.players.TeamColor;
import java.util.EnumMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author jonathan
 */
public class InterfacePlateauMain extends Parent {

    public VBox pions;
    Label nomJoueur;
    public boolean isCourant;
    private TeamColor couleur;
    EnumMap<InsectType, InterfacePions> pilesPions;

    CacheImage c;

    public InterfacePlateauMain(PlayerCollection col, Stage stage, String nomJoueur, CacheImage c, GameController plateauController, InterfacePlateau plateau, TeamColor color) {
        pions = new VBox();
        this.c = c;
        this.couleur = color;
        pions.setAlignment(Pos.TOP_CENTER);
        this.nomJoueur = new Label(nomJoueur);
        this.nomJoueur.setAlignment(Pos.BOTTOM_CENTER);
        pions.prefHeightProperty().bind(stage.heightProperty());
        BackgroundFill bf = new BackgroundFill(Color.GRAY, null, null);

        pilesPions = new EnumMap<InsectType, InterfacePions>(InsectType.class);
        pions.setPadding(new Insets(35));
        for (InsectType type : InsectType.implemented_insects) {
            pilesPions.put(type, new InterfacePions(color, col.get(type), type, c));
            pilesPions.get(type).addEventHandler(MouseEvent.MOUSE_RELEASED, new TileMainHandler(plateauController, plateau, color, type));
            pilesPions.get(type).addEventHandler(MouseEvent.MOUSE_PRESSED, new TileMainHandler(plateauController, plateau, color, type));
            pilesPions.get(type).addEventHandler(MouseEvent.MOUSE_CLICKED, new TileMainHandler(plateauController, plateau, color, type));
            pilesPions.get(type).addEventHandler(MouseEvent.MOUSE_DRAGGED, new TileMainHandler(plateauController, plateau, color, type));
            pions.getChildren().add(pilesPions.get(type));
        }

        pions.setBackground(new Background(bf));

        pions.getChildren().add(this.nomJoueur);
        pions.getChildren().get(0).setOpacity(1);
        this.getChildren().add(pions);
    }

    public void surlignerTile(Tile tile) {
        pilesPions.get(tile.type).setSelected(Color.rgb(246, 6, 189));
    }

    public void desurlignerTile(Tile tile) {
        pilesPions.get(tile.type).unsetSelected();

    }

    public void maj(Tile tile, int nbTiles) {
        pilesPions.get(tile.type).maj(this.couleur, nbTiles, tile.type);
    }
}
