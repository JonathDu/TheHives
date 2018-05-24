/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import hive.controller.plateauscene.game.GameController;
import hive.controller.plateauscene.game.GameController;
import hive.model.board.Tile;
import hive.model.game.Game;
import hive.model.players.TeamColor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
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
public class InterfacePlateau extends Interface {

    BorderPane borderPane;
    public NodePlateauMain mainGauche;
    public NodePlateauMain mainDroite;
    public NodeRuche ruche;
    GameController gameController;
    private StackPane centerPane;
    ScrollPane scrollPane;
    BorderPane centerMainG;
    BorderPane centerMainD;

    public InterfacePlateau(Stage stage, Controller controller, Game game, CacheImage c, String joueur1, String joueur2) {
        super(stage, controller, c);
        
        this.controller = controller;
        borderPane = new BorderPane();
        centerPane = new StackPane();
        scrollPane = new ScrollPane();
        centerMainG = new BorderPane();
        centerMainD = new BorderPane();

        gameController = new GameController(game, this);

        mainGauche = new NodePlateauMain(gameController.game.state.players.get(0).collection, stage, joueur1, c, gameController, this, TeamColor.WHITE);
        mainDroite = new NodePlateauMain(gameController.game.state.players.get(1).collection, stage, joueur2, c, gameController, this, TeamColor.BLACK);

        Image bimMainauche = c.getImage("Design/FenetrePlateau/poseJetona.png");
        BackgroundSize bsiMainGauche = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage baimMainGauche = new BackgroundImage(bimMainauche, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bsiMainGauche);
        Background backgroundMainGauche = new Background(baimMainGauche);

        mainGauche.pions.setBackground(backgroundMainGauche);

        Image bimMainDroite = c.getImage("Design/FenetrePlateau/poseJetona.png");
        BackgroundSize bsiMainDroite = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage baimMainDroite = new BackgroundImage(bimMainDroite, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bsiMainDroite);
        Background backgroundMainDroite = new Background(baimMainDroite);

        mainDroite.pions.setBackground(backgroundMainDroite);

        centerMainD.prefHeightProperty().bind(stage.heightProperty());
        centerMainG.prefHeightProperty().bind(stage.heightProperty());

        centerMainD.setCenter(mainDroite);
        centerMainG.setCenter(mainGauche);

        Image bimPlateau = c.getImage("Design/FenetrePlateau/PlateauCentral.png");
        BackgroundSize bsiPlateau = new BackgroundSize(100, 100, true, true, false, true);
        BackgroundImage baimPlateau = new BackgroundImage(bimPlateau, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bsiPlateau);
        Background backgroundPlateau = new Background(baimPlateau);

        centerPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        scrollPane.setBackground(backgroundPlateau);
        borderPane.prefWidthProperty().bind(stage.widthProperty());
        borderPane.prefHeightProperty().bind(stage.heightProperty());

        ruche = new NodeRuche(c, gameController);
        ruche.setHandler(this);

        StackPane.setAlignment(ruche, Pos.TOP_CENTER);
        centerPane.getChildren().add(ruche);
        scrollPane.setContent(centerPane);

        scrollPane.setHvalue(0.5);
        scrollPane.setVvalue(0.5);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        BorderPane.setMargin(scrollPane, new Insets(20, 20, 48, 20));
        BorderPane.setMargin(centerMainG, new Insets(20, 20, 48, 20));
        BorderPane.setMargin(centerMainD, new Insets(20, 20, 48, 20));

        borderPane.setCenter(scrollPane);
        borderPane.setLeft(centerMainG);
        borderPane.setRight(centerMainD);
        borderPane.setTop(new NodePlateauTool(c, stage, controller, joueur1, joueur2, gameController.game, this.boutonPleinEcran, this.boutonPreference));

        borderPane.setBackground(background);
        this.panePrincipale.getChildren().add(borderPane);
        
        gameController.start();
    }

    public NodePlateauMain getInterfacePlateauMain(TeamColor color) {
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
        mainDroite.setIsCourant(color == TeamColor.BLACK);
        mainGauche.setIsCourant(color == TeamColor.WHITE);
    }

    public void majRetourPreference()
    {
    }
}
