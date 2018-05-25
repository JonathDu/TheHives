/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller;

import hive.model.GameProgress;
import hive.model.game.Game;
import hive.model.game.GameLoader;
import hive.model.game.PrecalculatedGame;
import hive.model.players.decisions.Decision;
import hive.model.players.decisions.HumanDecision;
import hive.model.players.decisions.IADecision;
import hive.model.players.decisions.Level;
import hive.vue.CacheImage;
import hive.vue.Interface;
import hive.vue.InterfaceCharger;
import hive.vue.InterfaceCredits;
import hive.vue.InterfaceJoueurs;
import hive.vue.InterfaceMenu;
import hive.vue.InterfacePlateau;
import hive.vue.InterfaceRegles;
import hive.vue.InterfaceStatistiques;
import hive.vue.Preferences;
import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.LoaderXML;

/**
 *
 * @author lucas
 */
public final class Controller
{

    Scene currentScene;
    Stage primaryStage;
    CacheImage cacheImage;
    public Dimension screenSize;
    public GestionnaireLangage gestionnaireLangage;
    public String typeTheme;

    public Controller(Stage _primaryStage, Scene _currentScene, CacheImage _cacheImage, Dimension _screenSize)
    {
        currentScene = _currentScene;
        primaryStage = _primaryStage;
        cacheImage = _cacheImage;
        screenSize = _screenSize;
        gestionnaireLangage = new GestionnaireLangage(Locale.FRENCH);
        typeTheme = "Jour";
        primaryStage.setScene(currentScene);
        goToMenu();
    }

    public void goToMenu()
    {
        currentScene.setRoot(new InterfaceMenu(primaryStage, this, cacheImage));
    }

    public void goToChoixJoueur()
    {
        currentScene.setRoot(new InterfaceJoueurs(primaryStage, this, cacheImage));
    }

    public void goToPlateau(String nomJoueur1, String nomJoueur2, Level levelJ1, Level levelJ2)
    {
        Game game = PrecalculatedGame.get(PrecalculatedGame.Id.DEFAULT, getDecision(levelJ1), getDecision(levelJ2));

        currentScene.setRoot(new InterfacePlateau(primaryStage, this, game, cacheImage, nomJoueur1, nomJoueur2));
        String css = this.getClass().getResource("/hive/vue/style.css").toExternalForm();
        currentScene.getStylesheets().add(css);
    }

    public void goToPlateau(Game game)
    {
        currentScene.setRoot(new InterfacePlateau(primaryStage, this, game, cacheImage, "TODOj1", "TODOj1"));
        String css = this.getClass().getResource("/hive/vue/style.css").toExternalForm();
        currentScene.getStylesheets().add(css);
    }

    public void goToChargerPartie() throws IOException
    {
        currentScene.setRoot(new InterfaceCharger(primaryStage, this, cacheImage));
    }

    public void goToRegles()
    {
        currentScene.setRoot(new InterfaceRegles(primaryStage, this, cacheImage));
    }

    public void goToStat()
    {
        currentScene.setRoot(new InterfaceStatistiques(primaryStage, this, cacheImage));
    }

    public void goToCredits()
    {
        currentScene.setRoot(new InterfaceCredits(primaryStage, this, cacheImage));
    }

    private Decision getDecision(Level level)
    {
        return level == null ? new HumanDecision() : new IADecision(level);
    }

    public Preferences getPreferences()
    {
        return new Preferences(primaryStage, this, cacheImage);
    }

    public void validerParametres(String nomLangue, boolean activerAide, String nomTheme)
    {
        Locale newLangue = gestionnaireLangage.langues.get(nomLangue);
        gestionnaireLangage.changerLangue(newLangue);
        typeTheme = nomTheme;
        ((Interface) currentScene.getRoot()).majRetourPreference();
    }

    public Game chargerGame(String fileName)
    {
        LoaderXML<Game> loader = new GameLoader();
        Game game = null;
        try
        {
            game = loader.loadFromFile("savefiles/" + fileName);
        } catch (FileNotFoundException ex)
        {
            System.err.println("PAS DE FICHIER TROUVE");
        }
        return game;
    }

    public void enregistrerGame(Game game, String fileName)
    {
        LoaderXML<Game> loader = new GameLoader();
        try
        {
            loader.loadInFile(game, "savefiles/" + fileName);
        } catch (IOException ex)
        {
            System.err.println("PAS DE FICHIER TROUVE");
        }
    }

    public String getPolice()
    {
        return "Papyrus";
    }

    public void undo(Game game)
    {
        GameProgress progress = new GameProgress(game);
        progress.undoAction();
    }

    public void redo(Game game)
    {
        GameProgress progress = new GameProgress(game);
        progress.doAction();
    }

    public ArrayList<String> getSavedFileNames()
    {
        File folder = new File("savefiles/");
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> files = new ArrayList<>();
        for (File file : listOfFiles)
        {
            if (file.isFile())
            {
                files.add(file.getName());
            }
        }
        return files;
    }

}
