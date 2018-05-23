/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller;

import hive.controller.plateauscene.game.GameController;
import hive.model.game.DefaultGame;
import hive.model.game.Game;
import hive.model.game.PrecalculatedGame;
import hive.model.players.decisions.Decision;
import hive.model.players.decisions.HumanDecision;
import hive.model.players.decisions.IADecision;
import hive.model.players.decisions.Level;
import hive.vue.CacheImage;
import hive.vue.InterfaceCharger;
import hive.vue.InterfaceCredits;
import hive.vue.InterfaceJoueurs;
import hive.vue.InterfaceMenu;
import hive.vue.InterfacePlateau;
import hive.vue.InterfaceRegles;
import hive.vue.InterfaceStatistiques;
import java.io.IOException;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author lucas
 */
public final class Controller {

    Scene currentScene;
    Stage primaryStage;
    CacheImage cacheImage;
    public String langue = "Français";
    public int pleinEcran = 0;
    public String typeTheme;
    public double old_height;
    public double old_width;

    public Controller(Stage primaryStage, Scene currentScene, CacheImage cacheImage) {
        this.currentScene = currentScene;
        this.primaryStage = primaryStage;
        this.cacheImage = cacheImage;
        goToMenu();
    }

    public void changeScene() {
        Image souris = cacheImage.getImage("souris.png");
        ImageCursor sourisIm = new ImageCursor(souris, souris.getWidth() / 2, souris.getHeight() / 2);
        currentScene.setCursor(sourisIm);

        primaryStage.setScene(currentScene);
        if(primaryStage.getWidth() >= java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth() && primaryStage.getHeight() >= java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight()){
            primaryStage.centerOnScreen();
        }
    }

    public void goToMenu() {
        currentScene = new Scene(new InterfaceMenu(primaryStage, this), primaryStage.getWidth(), primaryStage.getHeight());
        changeScene();
    }

    public void goToChoixJoueur() {
        currentScene = new Scene(new InterfaceJoueurs(primaryStage, this), primaryStage.getWidth(), primaryStage.getHeight());
        changeScene();
    }

    public void goToPlateau(String nomJoueur1, String nomJoueur2, Level levelJ1, Level levelJ2) {
        Game game = PrecalculatedGame.get(PrecalculatedGame.Id.DEFAULT, getDecision(levelJ1), getDecision(levelJ2));
        GameController plateauController = new GameController(game);
        currentScene = new Scene(new InterfacePlateau(primaryStage, this, plateauController, cacheImage, nomJoueur1, nomJoueur2), primaryStage.getWidth(), primaryStage.getHeight());
        String css = this.getClass().getResource("/hive/vue/style.css").toExternalForm();
        currentScene.getStylesheets().add(css);
        changeScene();
    }

    public void goToChargerPartie() throws IOException {
        currentScene = new Scene(new InterfaceCharger(primaryStage, this), primaryStage.getWidth(), primaryStage.getHeight());
        changeScene();
    }

    public void goToRegles() {
        currentScene = new Scene(new InterfaceRegles(primaryStage, this), primaryStage.getWidth(), primaryStage.getHeight());
        changeScene();
    }

    public void goToStat() {
        currentScene = new Scene(new InterfaceStatistiques(primaryStage, this), primaryStage.getWidth(), primaryStage.getHeight());
        changeScene();
    }

    public void goToCredits() {
        currentScene = new Scene(new InterfaceCredits(primaryStage, this), primaryStage.getWidth(), primaryStage.getHeight(), Color.LIGHTBLUE);
        changeScene();
    }

    private Decision getDecision(Level level) {
        return level == null ? new HumanDecision() : new IADecision(level);
    }
}
