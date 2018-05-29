/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import hive.controller.StatistiqueGesture;
import hive.controller.plateau.PlateauController;
import hive.controller.plateau.handlers.IAPlayerHandler;
import hive.model.Match;
import hive.model.board.Cell;
import hive.model.board.Tile;
import hive.model.game.Game;
import hive.model.players.TeamColor;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author jonathan
 */
public class InterfacePlateau extends Interface {

    BorderPane borderPane;
    public NodePlateauMain mainGauche;
    public NodePlateauMain mainDroite;
    public NodeRuche ruche;
    PlateauController gameController;
    private final StackPane centerPane;
    ScrollPane scrollPane;
    VBox centerMainG;
    VBox centerMainD;
    Match match;

    HiveBouton boutonHome;
    HiveBouton boutonSave;
    HiveBouton boutonReplay;
    HiveBouton boutonAnnuler;
    HiveBouton boutonRecommencer;
    HiveBouton boutonConseil;
    HiveBouton boutonRegle;

    BorderPane pane;
    HBox gauche;
    HBox centre;
    String j1;
    String j2;

    boolean onDrag = false;

    public InterfacePlateau(Scene scene, Stage stage, Controller controller, Match match, CacheImage c) {
        super(scene, stage, controller, c);

        this.match = match;
        this.controller = controller;
        borderPane = new BorderPane();
        centerPane = new StackPane();
        scrollPane = new ScrollPane();
        centerMainG = new VBox();
        centerMainD = new VBox();

        BorderPane tool = setTool();

        borderPane.prefWidthProperty().bind(scene.widthProperty());
        borderPane.prefHeightProperty().bind(scene.heightProperty());

        gameController = new PlateauController(match, this);

        mainGauche = new NodePlateauMain(gameController.game.state.players.get(0).collection, match.getPlayerData1().name, c, gameController, this, TeamColor.WHITE);
        mainDroite = new NodePlateauMain(gameController.game.state.players.get(1).collection, match.getPlayerData2().name, c, gameController, this, TeamColor.BLACK);

        Image bimMainauche = c.getImage("Design/FenetrePlateau/poseJetona.png");
        BackgroundSize bsiMainGauche = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage baimMainGauche = new BackgroundImage(bimMainauche, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bsiMainGauche);
        Background backgroundMainGauche = new Background(baimMainGauche);

        mainGauche.pions.setBackground(backgroundMainGauche);

        Image bimMainDroite = c.getImage("Design/FenetrePlateau/poseJetona.png");
        BackgroundSize bsiMainDroite = new BackgroundSize(100, 100, true, true, true, true);
        BackgroundImage baimMainDroite = new BackgroundImage(bimMainDroite, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, bsiMainDroite);
        Background backgroundMainDroite = new Background(baimMainDroite);

        mainDroite.pions.setBackground(backgroundMainDroite);

        centerMainD.getChildren().add(mainDroite);
        centerMainG.getChildren().add(mainGauche);

        Image bimPlateau = c.getImage("Design/FenetrePlateau/Plateau.png");
        BackgroundSize bsiPlateau = new BackgroundSize(100, 100, true, true, false, true);
        BackgroundImage baimPlateau = new BackgroundImage(bimPlateau, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, bsiPlateau);
        Background backgroundPlateau = new Background(baimPlateau);

        centerPane.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        scrollPane.setBackground(backgroundPlateau);

        ruche = new NodeRuche(c, gameController);
        ruche.setHandler(this);

        StackPane.setAlignment(ruche, Pos.TOP_CENTER);
        centerPane.getChildren().add(ruche);
        scrollPane.setContent(centerPane);

        scrollPane.setHvalue(0.5);
        scrollPane.setVvalue(0.5);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        HBox bottom = new HBox(5);
        BorderPane.setMargin(bottom, new Insets(0, 0, 20, 0));

        HiveBouton boutonCentrer = new HiveBouton(c.getImage("Design/FenetrePlateau/Recentrer.png"), scene);
        boutonCentrer.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            recentrer();
        });

        HiveBouton boutonTailleAug = new HiveBouton(c.getImage("Design/FenetrePlateau/+.png"), scene);
        boutonTailleAug.addEventHandler(MouseEvent.MOUSE_CLICKED, (value) -> {
            recentrer();
            this.ruche.majTaille(5);
            recentrer();
        });

        HiveBouton boutonTailleDim = new HiveBouton(c.getImage("Design/FenetrePlateau/-.png"), scene);
        boutonTailleDim.addEventHandler(MouseEvent.MOUSE_CLICKED, (value) -> {
            recentrer();
            this.ruche.majTaille(- 5);
            recentrer();
        });

        bottom.setAlignment(Pos.CENTER);

        bottom.getChildren().add(boutonTailleDim);
        bottom.getChildren().add(boutonCentrer);
        bottom.getChildren().add(boutonTailleAug);

        BorderPane.setMargin(scrollPane, new Insets(20, 20, 10, 20));
        BorderPane.setMargin(centerMainG, new Insets(20, 20, 10, 20));
        BorderPane.setMargin(centerMainD, new Insets(20, 20, 10, 20));

        borderPane.setCenter(scrollPane);
        borderPane.setTop(tool);
        borderPane.setLeft(centerMainG);
        borderPane.setRight(centerMainD);
        borderPane.setBottom(bottom);

        this.panePrincipale.getChildren().add(borderPane);

        majJoueurCourant(TeamColor.WHITE);

        gameController.start();

    }

    public void update() {
        ruche.updateTab(); //TODO !!!
        majJoueurCourant(TeamColor.WHITE);
        mainGauche.update(match.game.state.players.get(0).collection);
        mainDroite.update(match.game.state.players.get(1).collection);
    }

    private BorderPane setTool() {
        width = (int) scene.getWidth();

        String repertoire = "Design/FenetrePlateau/";

        pane = new BorderPane();
        pane.prefWidthProperty().bind(scene.widthProperty());

        gauche = new HBox(5);
        centre = new HBox(5);

        boutonSave = new HiveBouton(c.getImage(repertoire + "BoutonDisquette.png"), scene);
        boutonHome = new HiveBouton(c.getImage(repertoire + "bouttonRetourMenu.png"), scene);
        boutonAnnuler = new HiveBouton(c.getImage(repertoire + "FlecheUndo.png"), scene);
        boutonConseil = new HiveBouton(c.getImage(repertoire + "Ampoule.png"), scene);
        boutonReplay = new HiveBouton(c.getImage(repertoire + "FlecheRedo.png"), scene);
        boutonRegle = new HiveBouton(c.getImage(repertoire + "Boutonlivre.png"), scene);
        boutonRecommencer = new HiveBouton(c.getImage(repertoire + "BoutonRestart.png"), scene);

        boutonHome.setOnMouseClicked(value -> {
            Stage quitStage = new Stage();
            quitStage.initModality(Modality.APPLICATION_MODAL);
            NodePopup root = new NodePopup(controller, quitStage, match, gameController);
            quitStage.setScene(new Scene(root));
            quitStage.show();

        });

        boutonSave.setOnMouseClicked(value -> {
            Stage savaStage = new Stage();
            savaStage.initModality(Modality.APPLICATION_MODAL);
            NodePopupSave root = new NodePopupSave(controller, savaStage, match);
            savaStage.setScene(new Scene(root));
            savaStage.show();
        });

        boutonRegle.setOnMouseClicked(value
                -> {

            Stage primaryStage = new Stage();
            Parent root = new Group();
            Scene s = new Scene(root, 800, 600);
            primaryStage.setScene(s);
            primaryStage.setTitle("Regles");
            primaryStage.sizeToScene();
            primaryStage.getScene().setRoot(new InterfaceRegles(primaryStage.getScene(), primaryStage, controller, c, true));
            primaryStage.show();

        });

        boutonRecommencer.setOnMouseClicked(value
                -> {
            gameController.restart();
        });

        boutonAnnuler.setOnMouseClicked(value
                -> {
            gameController.undo();
        });

        boutonReplay.setOnMouseClicked(value
                -> {
            gameController.redo();
        });

        boutonConseil.setOnMouseClicked(value
                -> {
            if(gameController.isIAvsIA())
            {
                gameController.playPause();
            }
            else
            {
                gameController.help();
            }
        });

        pane.setPadding(new Insets(5, 5, 0, 5));

        Group g = new Group();
        g.getChildren().add(centre);
        pane.setLeft(gauche);
        pane.setRight(droite);
        pane.setCenter(g);

        Tooltip retourMenuTip = new Tooltip("Retour au menu");
        Tooltip sauvegarderTip = new Tooltip("Sauvergarder");
        Tooltip recommencerTip = new Tooltip("Recommencer");
        Tooltip regleTip = new Tooltip("Règles");
        Tooltip annulerTip = new Tooltip("Annuler un coup");
        Tooltip conseilTip = new Tooltip("Conseil");
        Tooltip replayTip = new Tooltip("Refaire le coup annuler");

        gauche.getChildren().add(boutonHome);
        Tooltip.install(boutonHome, retourMenuTip);
        gauche.getChildren().add(boutonSave);
        Tooltip.install(boutonSave, sauvegarderTip);
        gauche.getChildren().add(boutonRecommencer);
        Tooltip.install(boutonRecommencer, recommencerTip);
        droite.getChildren().add(0, boutonRegle);
        Tooltip.install(boutonRegle, regleTip);
        centre.getChildren().add(boutonAnnuler);
        Tooltip.install(boutonAnnuler, annulerTip);
        centre.getChildren().add(boutonConseil);
        Tooltip.install(boutonConseil, conseilTip);
        centre.getChildren().add(boutonReplay);
        Tooltip.install(boutonReplay, replayTip);

        return pane;

    }

    public NodePlateauMain getInterfacePlateauMain(TeamColor color) {
        return color == TeamColor.BLACK ? mainDroite : mainGauche;
    }

    public void majTileMain(Tile tile, int nbTiles) {
        if (tile.color == TeamColor.BLACK) {
            mainDroite.maj();
        } else {
            mainGauche.maj();
        }
    }

    public void majJoueurCourant(TeamColor color) {
        mainDroite.setIsCourant(color == TeamColor.BLACK);
        mainGauche.setIsCourant(color == TeamColor.WHITE);
    }

    @Override
    public void setTextWithCurrentLanguage() {
        //pas de texte dans cette interface => rien a mettre a jour
    }

    public void finPartie(String gagnant, String perdant) {
        TeamColor couleur;
        if (gagnant.equals(match.getPlayerData(TeamColor.BLACK).name)) {
            couleur = TeamColor.BLACK;
        } else {
            couleur = TeamColor.WHITE;

        }
        FinPartie f = new FinPartie(scene, primaryStage, controller, c, gameController, this, gagnant, couleur);
        this.panePrincipale.getChildren().add(f);
        if (gagnant != null && perdant != null) //TODO : comment gérer ?
        {
            StatistiqueGesture.setWinScoreFor(gagnant, perdant);
            StatistiqueGesture.setLoseScoreFor(perdant, gagnant);
        }
        Timeline a = new Timeline(new KeyFrame(Duration.millis(5000), (event) -> {
            f.view.setVisible(false);
        }));
        a.play();

    }

    public void message(String titre, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titre);
        alert.setHeaderText(message);
        alert.show();
    }

    private void recentrer() {
        scrollPane.setHvalue(0.5);
        scrollPane.setVvalue(0.5);
    }

    public void surlignerDestinationsPossibles(ArrayList<Cell> cells) {
        if (controller.settingsGesture.get("aide").equals("true")) {
            ruche.surlignerDestinationsPossibles(cells);
        }
    }
}
