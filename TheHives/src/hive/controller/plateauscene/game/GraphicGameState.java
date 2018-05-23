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
public class GraphicGameState
{

    public Game game;
    public InterfacePlateau uiPlateau;

    public Timeline timerJouerIA;
    public Timeline timerFrame;

    public GraphicGameState(Game game, InterfacePlateau uiPlateau)
    {
        this.game = game;
        this.uiPlateau = uiPlateau;

        timerJouerIA = new Timeline(new KeyFrame(Duration.millis(200), new IAPlayerHandler(new GameProgress(game), uiPlateau)));
        timerJouerIA.setCycleCount(1);

        timerFrame = new Timeline(new KeyFrame(Duration.millis(500), new FrameHandler(this)));
        timerFrame.setCycleCount(Timeline.INDEFINITE);

        timerFrame.play();
    }
}
