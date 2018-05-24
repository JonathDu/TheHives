/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.plateauscene.game;

import hive.model.GameProgress;
import hive.model.game.Game;
import hive.vue.InterfacePlateau;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 *
 * @author lucas
 */
public class GameController
{

    public Game game;
    public InterfacePlateau uiPlateau;

    public ActionBuilder builder;
    public Timeline timerJouerIA;
    public Timeline timerFrame;

    public GameController(Game game, InterfacePlateau uiPlateau)
    {
        this.game = game;
        this.uiPlateau = uiPlateau;

        this.builder = new ActionBuilder();
        
        timerJouerIA = new Timeline(new KeyFrame(Duration.millis(200), new IAPlayerHandler(this)));
        timerJouerIA.setCycleCount(1);

        timerFrame = new Timeline(new KeyFrame(Duration.millis(500), new FrameHandler(this)));
        timerFrame.setCycleCount(Timeline.INDEFINITE);
    }
    
    public void start()
    {
        timerFrame.play();
    }
}
