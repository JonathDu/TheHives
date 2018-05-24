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
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
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
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author jonathan
 */
public class InterfacePlateau extends Interface {

    BorderPane borderPane;
    GameController plateauController;
    public NodePlateauMain mainGauche;
    public NodePlateauMain mainDroite;
    public NodeRuche ruche;
    GraphicGameState graphicGameState;
    private StackPane centerPane;
    ScrollPane scrollPane;
    BorderPane centerMainG;
    BorderPane centerMainD;

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

    public InterfacePlateau(Stage stage, Controller controller, GameController plateauController, CacheImage c, String joueur1, String joueur2) {
        super(stage, controller, c);

        this.controller = controller;
        borderPane = new BorderPane();
        centerPane = new StackPane();
        scrollPane = new ScrollPane();
        centerMainG = new BorderPane();
        centerMainD = new BorderPane();

        graphicGameState = new GraphicGameState(plateauController.progress.game, this);

        mainGauche = new NodePlateauMain(plateauController.progress.game.state.players.get(0).collection, stage, joueur1, c, plateauController, this, TeamColor.WHITE);
        mainDroite = new NodePlateauMain(plateauController.progress.game.state.players.get(1).collection, stage, joueur2, c, plateauController, this, TeamColor.BLACK);

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

        ruche = new NodeRuche(c, plateauController);
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
        borderPane.setTop(setTool());

        borderPane.setBackground(background);
        this.panePrincipale.getChildren().add(borderPane);

    }

    private BorderPane setTool() {
        width = (int) primaryStage.getWidth();

        String repertoire = "Design/FenetrePlateau/";

        pane = new BorderPane();
        pane.prefWidthProperty().bind(primaryStage.widthProperty());

        gauche = new HBox();
        droite = new HBox();
        centre = new HBox();

        boutonSave = new HiveBouton(c.getImage(repertoire + "BoutonDisquette.png"), width);
        boutonHome = new HiveBouton(c.getImage(repertoire + "bouttonRetourMenu.png"), width);
        boutonAnnuler = new HiveBouton(c.getImage(repertoire + "FlecheUndo.png"), width);
        boutonConseil = new HiveBouton(c.getImage(repertoire + "Ampoule.png"), width);
        boutonReplay = new HiveBouton(c.getImage(repertoire + "FlecheRedo.png"), width);
        boutonRegle = new HiveBouton(c.getImage(repertoire + "Boutonlivre.png"), width);
 

        boutonHome.setOnMouseClicked(value -> {
            Stage primaryStage = new Stage();
            primaryStage.initModality(Modality.APPLICATION_MODAL);
            NodePopup root = new NodePopup("Etes vous sur de vouloir quitter la partie ?", "Quitter", "Sauvegarder et quitter", "Annuler");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

            root.valider.addEventHandler(MouseEvent.MOUSE_CLICKED, (event) -> {
                controller.goToMenu();
                primaryStage.close();
            });

            //controller.goToMenu();
        });

        boutonSave.setOnMouseClicked(value -> {
            controller.enregistrerGame(plateauController.progress.game, "test.xml");
        });

        boutonRegle.setOnMouseClicked(value -> {

            Stage primaryStage = new Stage();
            Parent root;
            root = new InterfaceRegles(primaryStage, controller, c, true);
            primaryStage.setTitle("Regles");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();
        });

        Group g = new Group();
        g.getChildren().add(centre);
        pane.setLeft(gauche);
        pane.setRight(droite);
        pane.setCenter(g);

        gauche.getChildren().add(boutonHome);
        gauche.getChildren().add(boutonSave);
        droite.getChildren().add(boutonRegle);
        droite.getChildren().add(boutonPreference);
        droite.getChildren().add(boutonPleinEcran);
        centre.getChildren().add(boutonAnnuler);
        centre.getChildren().add(boutonConseil);
        centre.getChildren().add(boutonReplay);
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

    public void majRetourPreference() {
    }
}
