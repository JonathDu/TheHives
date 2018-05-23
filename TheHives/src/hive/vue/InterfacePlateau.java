/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import hive.controller.plateauscene.game.GameController;
import hive.controller.plateauscene.game.GraphicGameState;
import hive.model.board.Tile;
import hive.model.players.TeamColor;
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
    GameController plateauController;
    public InterfacePlateauMain mainGauche;
    public InterfacePlateauMain mainDroite;
    public InterfaceRuche ruche;
    GraphicGameState graphicGameState;
    private Controller controller;

    public InterfacePlateau(Stage stage, Controller controller, GameController plateauController, CacheImage c, String joueur1, String joueur2) {
        Image fond = c.getImage("fondMontagne.png");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, false, true);
        BackgroundImage backgroundFond = new BackgroundImage(fond, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        Background background = new Background(backgroundFond);
        this.controller = controller;
        pane = new BorderPane();
        pane.prefWidthProperty().bind(stage.widthProperty());
        pane.prefHeightProperty().bind(stage.heightProperty());

        graphicGameState = new GraphicGameState(plateauController.progress.game, this);

        mainGauche = new InterfacePlateauMain(plateauController.progress.game.state.players.get(0).collection, stage, joueur1, c, plateauController, this, TeamColor.WHITE);
        mainDroite = new InterfacePlateauMain(plateauController.progress.game.state.players.get(1).collection, stage, joueur2, c, plateauController, this, TeamColor.BLACK);
//
//        Image bimMainauche = c.getImage("Design/FenetrePlateau/poseJetona.png");
//        BackgroundSize bsiMainGauche = new BackgroundSize(100, 100, true, true, true, false);
//        BackgroundImage baimMainGauche = new BackgroundImage(bimMainauche, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bsiMainGauche);
//        Background backgroundMainGauche = new Background(baimMainGauche);
//
//        mainGauche.pions.setStyle("-fx-background-image: url(\"hive/vue/rsc/images/Design/FenetrePlateau/poseJetona.png\");"
//                                + "-fx-background-repeat: stretch;"
//                                + "-fx-background-size: cover cover;"
//                                + ""
//                                + ""
//                                + ""
//                                + "");
//        //mainGauche.pions.setBackground(backgroundMainGauche);
//
//        Image bimMainDroite = c.getImage("Design/FenetrePlateau/poseJetona.png");
//        BackgroundSize bsiMainDroite = new BackgroundSize(100, 100, true, true, true, false);
//        BackgroundImage baimMainDroite = new BackgroundImage(bimMainDroite, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bsiMainDroite);
//        Background backgroundMainDroite = new Background(baimMainDroite);
//
//        mainDroite.pions.setBackground(backgroundMainDroite);
//
//        Image bimPlateau = c.getImage("Design/FenetrePlateau/PlateauCentral.png");
//        BackgroundSize bsiPlateau = new BackgroundSize(100, 100, true, true, true, false);
//        BackgroundImage baimPlateau = new BackgroundImage(bimPlateau, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bsiPlateau);
//        Background backgroundPlateau = new Background(baimPlateau);

        StackPane centerPane = new StackPane();
        ScrollPane scrollPane = new ScrollPane();

//        centerPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
//        scrollPane.setBackground(backgroundPlateau);

        ruche = new InterfaceRuche(c, plateauController);
        ruche.setHandler(this);

        StackPane.setAlignment(ruche, Pos.TOP_CENTER);
        centerPane.getChildren().add(ruche);
        scrollPane.setContent(centerPane);

        scrollPane.setHvalue(0.5);
        scrollPane.setVvalue(0.5);

        BorderPane.setMargin(scrollPane, new Insets(0, 20, 100, 20));
//        BorderPane.setMargin(mainDroite, new Insets(20, 20, 0, 20));
//        BorderPane.setMargin(mainGauche, new Insets(20, 20, 0, 20));

        pane.setCenter(scrollPane);
        pane.setLeft(mainGauche);
        pane.setRight(mainDroite);
        pane.setTop(new InterfacePlateauTool(c, stage, controller, joueur1, joueur2));

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

    public void majJoueurCourant(TeamColor color) {
        mainDroite.setIsCourant(color == TeamColor.WHITE);
        mainGauche.setIsCourant(color == TeamColor.BLACK);

    }
}
