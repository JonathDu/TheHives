/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.vue;

import hive.controller.Controller;
import hive.controller.plateau.PlateauController;
import hive.model.board.Tile;
import hive.model.game.Game;
import hive.model.players.TeamColor;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
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
    private StackPane centerPane;
    ScrollPane scrollPane;
    VBox centerMainG;
    VBox centerMainD;
    Game game;

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
    HBox droite;
    String j1;
    String j2;

    boolean onDrag = false;

    public InterfacePlateau(Stage stage, Controller controller, Game game, CacheImage c, String joueur1, String joueur2) {

        super(stage, controller, c);

        this.game = game;
        this.controller = controller;
        borderPane = new BorderPane();
        centerPane = new StackPane();
        scrollPane = new ScrollPane();
        centerMainG = new VBox();
        centerMainD = new VBox();

        BorderPane tool = setTool();

        borderPane.prefWidthProperty().bind(stage.widthProperty());
        borderPane.prefHeightProperty().bind(stage.heightProperty());

        gameController = new PlateauController(game, this);

        mainGauche = new NodePlateauMain(gameController.game.state.players.get(0).collection, stage, joueur1, c, gameController, this, TeamColor.WHITE);
        mainDroite = new NodePlateauMain(gameController.game.state.players.get(1).collection, stage, joueur2, c, gameController, this, TeamColor.BLACK);

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
        BorderPane.setMargin(bottom, new Insets(0, 0, 48, 0));

        HiveBouton boutonCentrer = new HiveBouton(c.getImage("Ampoule.png"), primaryStage);
        boutonCentrer.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
            recentrer();
        });

        HiveBouton boutonTailleAug = new HiveBouton(c.getImage("Ampoule.png"), primaryStage);
        boutonTailleAug.addEventHandler(MouseEvent.MOUSE_CLICKED, (value) -> {
            recentrer();
            this.ruche.majTaille(5);
            recentrer();
        });

        HiveBouton boutonTailleDim = new HiveBouton(c.getImage("Ampoule.png"), primaryStage);
        boutonTailleDim.addEventHandler(MouseEvent.MOUSE_CLICKED, (value) -> {
            recentrer();
            this.ruche.majTaille(- 5);
            recentrer();
        });

        bottom.setAlignment(Pos.CENTER);

        bottom.getChildren().add(boutonTailleAug);
        bottom.getChildren().add(boutonCentrer);
        bottom.getChildren().add(boutonTailleDim);

        BorderPane.setMargin(scrollPane, new Insets(20, 20, 10, 20));
        BorderPane.setMargin(centerMainG, new Insets(20, 20, 10, 20));
        BorderPane.setMargin(centerMainD, new Insets(20, 20, 10, 20));

        borderPane.setCenter(scrollPane);
        borderPane.setTop(tool);
        borderPane.setLeft(centerMainG);
        borderPane.setRight(centerMainD);
        borderPane.setBottom(bottom);
        setRucheHandler();

        this.panePrincipale.getChildren().add(borderPane);

        majJoueurCourant(TeamColor.WHITE);

        gameController.start();

    }

    public void update() {
        ruche.updateTab(); //TODO !!!
        majJoueurCourant(TeamColor.WHITE);
        mainGauche.update(game.state.players.get(0).collection);
        mainDroite.update(game.state.players.get(1).collection);
    }

    private void setRucheHandler() {
        ruche.setOnDragDetected((value) -> {
            this.onDrag = true;
        });
        ruche.setOnDragDone((value) -> {
            this.onDrag = false;
        });
        ruche.setOnMouseMoved((value) -> {
            if (this.onDrag) {
                System.out.println("value");
            }
        });
    }

    private BorderPane setTool() {
        width = (int) primaryStage.getWidth();

        String repertoire = "Design/FenetrePlateau/";

        pane = new BorderPane();
        pane.prefWidthProperty().bind(primaryStage.widthProperty());

        gauche = new HBox(5);
        droite = new HBox(5);
        centre = new HBox(5);

        boutonSave = new HiveBouton(c.getImage(repertoire + "BoutonDisquette.png"), primaryStage);
        boutonHome = new HiveBouton(c.getImage(repertoire + "bouttonRetourMenu.png"), primaryStage);
        boutonAnnuler = new HiveBouton(c.getImage(repertoire + "FlecheUndo.png"), primaryStage);
        boutonConseil = new HiveBouton(c.getImage(repertoire + "Ampoule.png"), primaryStage);
        boutonReplay = new HiveBouton(c.getImage(repertoire + "FlecheRedo.png"), primaryStage);
        boutonRegle = new HiveBouton(c.getImage(repertoire + "Boutonlivre.png"), primaryStage);
        boutonRecommencer = new HiveBouton(c.getImage(repertoire + "BoutonRestart.png"), primaryStage);

        boutonHome.setOnMouseClicked(value -> {
            Stage quitStage = new Stage();
            quitStage.initModality(Modality.APPLICATION_MODAL);
            NodePopup root = new NodePopup(controller, quitStage, game, gameController);
            quitStage.setScene(new Scene(root));
            quitStage.show();

        });

        boutonSave.setOnMouseClicked(value -> {
            Stage savaStage = new Stage();
            savaStage.initModality(Modality.APPLICATION_MODAL);
            NodePopupSave root = new NodePopupSave(controller, savaStage, game);
            savaStage.setScene(new Scene(root));
            savaStage.show();
        });

        boutonRegle.setOnMouseClicked(value
                -> {

            Stage primaryStage = new Stage();
            Parent root = new Group();
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.setTitle("Regles");
            primaryStage.show();
            
            primaryStage.setScene(new Scene(new InterfaceRegles(primaryStage, controller, c, true), 800, 600));
        });

        boutonRecommencer.setOnMouseClicked(value
                -> {
            gameController.restart();
            //this.finPartie("vbfidqodifg");
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
            gameController.help();
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
        droite.getChildren().add(boutonRegle);
        Tooltip.install(boutonRegle, regleTip);
        droite.getChildren().add(boutonPleinEcran);
        droite.getChildren().add(boutonPreference);
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
            mainDroite.maj(tile, nbTiles);
        } else {
            mainGauche.maj(tile, nbTiles);
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

    public void finPartie(String gagnant) {
        this.panePrincipale.getChildren().add(new FinPartie(primaryStage, controller, c, gameController, this, gagnant));
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

}
