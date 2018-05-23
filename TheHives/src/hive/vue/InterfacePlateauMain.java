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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
    public ImageView afficheTour;
    private ImageView panneau;

    CacheImage c;

    public InterfacePlateauMain(PlayerCollection col, Stage stage, String nomJoueur, CacheImage c, GameController plateauController, InterfacePlateau plateau, TeamColor color) {
        pions = new VBox();
        this.c = c;
        this.couleur = color;
        StackPane affichageJoueur = new StackPane();
        pions.setAlignment(Pos.TOP_CENTER);
        this.nomJoueur = new Label(nomJoueur);
        this.nomJoueur.setAlignment(Pos.BOTTOM_CENTER);
        pions.prefHeightProperty().bind(stage.heightProperty());
        BackgroundFill bf = new BackgroundFill(Color.GRAY, null, null);

        pilesPions = new EnumMap<InsectType, InterfacePions>(InsectType.class);
        pions.setPadding(new Insets(35));
        for (InsectType type : InsectType.implemented_insects) {
            pilesPions.put(type, new InterfacePions(color, col.get(type), type, c));
            pilesPions.get(type).addEventHandler(MouseEvent.MOUSE_CLICKED, new TileMainHandler(plateauController, plateau, color, type));
            pions.getChildren().add(pilesPions.get(type));
        }

        panneau = new ImageView(c.getImage("Design/FenetrePlateau/nom.png"));
        afficheTour = new ImageView(c.getImage("bee.png"));
        afficheTour.setFitWidth(30);
        afficheTour.setPreserveRatio(true);
        afficheTour.setSmooth(true);
        StackPane.setAlignment(afficheTour, Pos.TOP_LEFT);
        StackPane.setAlignment(panneau, Pos.CENTER);
        panneau.setFitWidth(150);
        panneau.setFitHeight(60);
        panneau.setSmooth(true);
        this.nomJoueur.setTextFill(Color.WHITE);
        this.nomJoueur.setWrapText(true);
        this.nomJoueur.setFont(new Font(20));
        affichageJoueur.getChildren().add(panneau);
        affichageJoueur.getChildren().add(this.nomJoueur);
        affichageJoueur.getChildren().add(this.afficheTour);

        affichageJoueur.setPadding(new Insets(30, 0, 30, 0));

        pions.setBackground(new Background(bf));

        pions.getChildren().add(affichageJoueur);
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

    public void setIsCourant(boolean c) {
        afficheTour.setVisible(c);
        if (c) {
            panneau.setEffect(new DropShadow(10, Color.GOLD));
        } else {
            panneau.setEffect(new DropShadow(10, Color.TRANSPARENT));
        }

    }
    
    public void majRetourPreference()
    {
    }
}
