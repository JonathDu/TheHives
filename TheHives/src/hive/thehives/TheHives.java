/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.thehives;

import hive.model.board.Board;
import hive.model.board.TilesStack;
import hive.model.game.DefaultGame;
import hive.model.players.PlayerCollection;
import hive.vue.InterfaceJeu;
import hive.vue.InterfaceMenu;
import hive.vue.InterfaceJoueurs;
import java.awt.Dimension;
import javafx.application.Application;
import javafx.scene.Cursor;
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

    @Override
    public void start(Stage primaryStage) throws Exception {

        Board board = new Board(new Matrix<TilesStack>(14, 14));
        board.getHexagon(new Vector2i(2, 2));
        PlayerCollection player = new PlayerCollection();


        root = new Group();
        scene = new Scene(root, WIDTH, HEIGHT, Color.LIGHTBLUE);
        this.primaryStage = primaryStage;

        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(600);
        primaryStage.setMaxHeight((int) dimension.getHeight());
        primaryStage.setMaxWidth((int)dimension.getWidth());
        goToPlateau("Joueur1", "Joueur2");
        //goToMenu();

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
        this.scene = new Scene(new InterfaceMenu(HEIGHT, WIDTH, primaryStage, this), WIDTH, HEIGHT, Color.LIGHTBLUE);
        changeScene();

    }

    public void goToChoixJoueur() {
        this.scene = new Scene(new InterfaceJoueurs(HEIGHT, WIDTH, primaryStage, this), WIDTH, HEIGHT, Color.LIGHTBLUE);
        changeScene();
    }

    public void goToPlateau(String nomJoueur1, String nomJoueur2) {
        this.scene = new Scene(new InterfaceJeu(DefaultGame.getCollection(), this, primaryStage, nomJoueur1, nomJoueur2), WIDTH, HEIGHT, Color.LIGHTBLUE);
        changeScene();
    }

    public void changeScene() {
//        Image souris = new Image(getClass().getResourceAsStream("vue/images/souris.png"));
//        ImageCursor sourisIm = new ImageCursor(souris, souris.getWidth() / 2, souris.getHeight() / 2);
//        this.scene.setCursor(sourisIm);
        primaryStage.setScene(scene);
    }
}
