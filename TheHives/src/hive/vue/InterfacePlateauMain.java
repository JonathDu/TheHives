/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.gamescene.game.GameController;
import hive.controller.gamescene.game.handlers.TileMainHandler;
import hive.model.board.Tile;
import static hive.model.insects.InsectType.*;
import hive.model.players.PlayerCollection;
import hive.model.players.TeamColor;
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

    VBox pions;
    Label nomJoueur;
    public boolean isCourant;

    public InterfacePlateauMain(PlayerCollection col,Stage stage, String nomJoueur, CacheImage c,  GameController controller, InterfacePlateau plateau, TeamColor color) {
        pions = new VBox();
        pions.setAlignment(Pos.TOP_CENTER);
        this.nomJoueur = new Label(nomJoueur);
        this.nomJoueur.setAlignment(Pos.BOTTOM_CENTER);
        pions.prefHeightProperty().bind(stage.heightProperty());
        BackgroundFill bf = new BackgroundFill(Color.GRAY, null, null);

        pions.setBackground(new Background(bf));
        InterfacePions pileQueenBee = new InterfacePions(color, col.get(QUEEN_BEE), QUEEN_BEE, c);
        InterfacePions pileGrassHopper = new InterfacePions(color, col.get(GRASSHOPPER), GRASSHOPPER, c);
        InterfacePions pileBeetle = new InterfacePions(color, col.get(BEETLE), BEETLE, c);

        pileQueenBee.addEventHandler(MouseEvent.MOUSE_CLICKED, new TileMainHandler(controller, plateau, color, QUEEN_BEE));
        pileGrassHopper.addEventHandler(MouseEvent.MOUSE_CLICKED, new TileMainHandler(controller, plateau, color, GRASSHOPPER));
        pileBeetle.addEventHandler(MouseEvent.MOUSE_CLICKED, new TileMainHandler(controller, plateau, color, BEETLE));

        pions.getChildren().add(pileQueenBee);
        pions.getChildren().add(pileGrassHopper);
        pions.getChildren().add(pileBeetle);

        pions.getChildren().add(this.nomJoueur);
        pions.getChildren().get(0).setOpacity(1);
        this.getChildren().add(pions);
    }

    public void surlignerTile(Tile tile) {

    }

    public void desurlignerTile(Tile tile) {

    }
}
