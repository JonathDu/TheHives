/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.thehives;

import hive.vue.InterfaceRegles;
import hive.vue.InterfaceCharger;
import hive.model.board.Board;
import hive.model.board.TilesStack;
import hive.model.game.DefaultGame;
import hive.model.players.PlayerCollection;
import hive.vue.CacheImage;
import hive.vue.InterfaceMenu;
import hive.vue.InterfaceJoueurs;
import hive.vue.InterfacePlateau;
import java.awt.Dimension;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.Matrix;
import util.Vector2i;

/**
 *
 * @author Adeline
 */
public class TheHives extends Application {

    /* le minimum est 400 et 600 */
    public int HEIGHT = 600;
    public int WIDTH = 800;
    Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
//    int HEIGHT = (int) dimension.getHeight();
//    int WIDTH  = (int)dimension.getWidth();
    Group root;
    Scene scene;
    Stage primaryStage;
    Stage stage;
    public String langue = "Français";
    public int pleinEcran = 0;
    @Override
    public void start(Stage primaryStage) throws Exception {

        Board board = new Board(new Matrix<TilesStack>(14, 14));
        board.getHexagon(new Vector2i(2, 2));
        PlayerCollection player = new PlayerCollection();


        root = new Group();
        scene = new Scene(root, WIDTH, HEIGHT, Color.LIGHTBLUE);
        this.primaryStage = primaryStage;
        primaryStage.setHeight(HEIGHT);
        primaryStage.setWidth(WIDTH);
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(600);
        primaryStage.setMaxHeight((int) dimension.getHeight()+10);
        primaryStage.setMaxWidth((int)dimension.getWidth()+10);
        //goToPlateau("Joueur1", "Joueur2");
        goToMenu();


        //PLEIN ECRAN

//        primaryStage.setFullScreen(true); //passer en affichage plein écran
//        primaryStage.setFullScreenExitHint("Sortie de plein écran - esc"); //changer le message qui s'affiche après le passage en mode plein écran
//        root.setCursor(Cursor.CROSSHAIR); //changer l'apparence du curseur de souris

        primaryStage.setTitle("The Hive");
        primaryStage.sizeToScene();
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(TheHives.class, args);
    }

    public void goToMenu() {
        this.scene = new Scene(new InterfaceMenu(primaryStage, this), primaryStage.getWidth(), primaryStage.getHeight(), Color.LIGHTBLUE);
        changeScene();

    }

    public void goToChoixJoueur() {
        this.scene = new Scene(new InterfaceJoueurs(primaryStage, this), primaryStage.getWidth(), primaryStage.getHeight(), Color.LIGHTBLUE);
        changeScene();
    }

    public void goToPlateau(String nomJoueur1, String nomJoueur2) {
        this.scene = new Scene(new InterfacePlateau(DefaultGame.getCollection(),DefaultGame.getCollection(), new CacheImage(),this, primaryStage, nomJoueur1, nomJoueur2), primaryStage.getWidth(), primaryStage.getHeight(), Color.LIGHTBLUE);
        changeScene();
    }

    public void changeScene() {
//        Image souris = new Image(getClass().getResourceAsStream("vue/images/souris.png"));
//        ImageCursor sourisIm = new ImageCursor(souris, souris.getWidth() / 2, souris.getHeight() / 2);
//        this.scene.setCursor(sourisIm);
        //scene.getStylesheets().add("/style.css");
        Double w = primaryStage.getWidth();
        Double h = primaryStage.getHeight();
        primaryStage.setScene(scene);
        

        primaryStage.setWidth(w);
        primaryStage.setHeight(h);
        
    }

    public void goToChargerPartie() throws IOException {
        
       // Executer executer = new Executer();
        /*
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(TheHives.class.getResource("/src/hive/thehives/choix.fxml"));
        AnchorPane choisir = (AnchorPane) loader.load();
        this.scene = new Scene(choisir);*/
        this.scene = new Scene(new InterfaceCharger(primaryStage, this), primaryStage.getWidth(), primaryStage.getHeight(), Color.LIGHTBLUE);
        changeScene();
    }

    public void goToRegles() {
        this.scene = new Scene(new InterfaceRegles(primaryStage, this), primaryStage.getWidth(), primaryStage.getHeight(), Color.LIGHTBLUE);
        changeScene();
    }
}
