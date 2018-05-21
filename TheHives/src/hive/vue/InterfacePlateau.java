/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.gamescene.game.GameController;
import hive.model.board.Tile;
import hive.model.game.DefaultGame;
import hive.model.players.PlayerCollection;
import hive.model.players.TeamColor;
import hive.model.players.decisions.HumanDecision;
import hive.thehives.TheHives;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author jonathan
 */
public class InterfacePlateau extends Parent {

    BorderPane pane;
    GameController controller;
    public InterfacePlateauMain mainGauche;
    public InterfacePlateauMain mainDroite;
    public InterfaceRuche ruche;

    public InterfacePlateau(PlayerCollection colJ1, PlayerCollection colJ2, CacheImage c, TheHives i, Stage stage, String joueur1, String joueur2) {

        Image fond = c.getImage("fondMontagne.png");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundFond = new BackgroundImage(fond, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        Background background = new Background(backgroundFond);

        pane = new BorderPane();
        pane.prefWidthProperty().bind(stage.widthProperty());
        pane.prefHeightProperty().bind(stage.heightProperty());
        controller = new GameController(DefaultGame.get(new HumanDecision(), new HumanDecision()));

        this.mainGauche = new InterfacePlateauMain(colJ1, stage, joueur1, c, controller, this, TeamColor.WHITE);
        this.mainDroite = new InterfacePlateauMain(colJ2, stage, joueur2, c, controller, this, TeamColor.BLACK);

        Image bimMainauche = c.getImage("poseJetona.png");
        BackgroundSize bsiMainGauche = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage baimMainGauche = new BackgroundImage(bimMainauche, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bsiMainGauche);
        Background backgroundMainGauche = new Background(baimMainGauche);

        this.mainGauche.pions.setBackground(backgroundMainGauche);

        Image bimMainDroite = c.getImage("poseJetona.png");
        BackgroundSize bsiMainDroite = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage baimMainDroite = new BackgroundImage(bimMainDroite, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bsiMainDroite);
        Background backgroundMainDroite = new Background(baimMainDroite);

        this.mainDroite.pions.setBackground(backgroundMainDroite);

        Image bimPlateau = c.getImage("PlateauCentral.png");
        BackgroundSize bsiPlateau = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage baimPlateau = new BackgroundImage(bimPlateau, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bsiPlateau);
        Background backgroundPlateau = new Background(baimPlateau);

        StackPane centerPane = new StackPane();
        ScrollPane scrollPane = new ScrollPane();


        centerPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        scrollPane.setBackground(backgroundPlateau);

        
        
        ruche = new InterfaceRuche(c, controller);
        ruche.setHandler(this);

        StackPane.setAlignment(ruche, Pos.TOP_CENTER);
        centerPane.getChildren().add(ruche);
        scrollPane.setContent(centerPane);

        scrollPane.setHvalue(0.5);
        scrollPane.setVvalue(0.5);

        BorderPane.setMargin(scrollPane, new Insets(20, 20, 100, 20));
        BorderPane.setMargin(mainDroite, new Insets(20, 20, 0, 20));
        BorderPane.setMargin(mainGauche, new Insets(20, 20, 0, 20));

        pane.setTop(new InterfacePlateauTool(c, stage));
        pane.setCenter(scrollPane);
        pane.setLeft(mainGauche);
        pane.setRight(mainDroite);
        pane.setBackground(background);
        this.getChildren().add(pane);
    }

    public InterfacePlateauMain getInterfacePlateauMain(TeamColor color) {
        return color == TeamColor.BLACK ? mainDroite : mainGauche;
    }

    public void majTileMain(Tile tile, int nbTiles) {
        if (tile.color == TeamColor.BLACK) {
            mainDroite.maj(tile, nbTiles);
        } else {
            mainGauche.maj(tile, nbTiles);
        }
    }

}
