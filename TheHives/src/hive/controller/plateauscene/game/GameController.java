/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.plateauscene.game;

import hive.model.board.Cell;
import hive.model.game.DefaultGame;
import hive.model.game.Game;
import hive.model.players.actions.Action;
import hive.model.players.actions.ActionVisitor;
import hive.model.players.actions.MoveAction;
import hive.model.players.actions.NoAction;
import hive.model.players.actions.PutAction;
import hive.model.players.decisions.IA.Level;
import hive.model.players.decisions.IADecision;
import hive.vue.InterfacePlateau;
import java.util.ArrayList;
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
    
    public void restart()
    {
        timerFrame.stop();
        builder.setBegin();
        game = DefaultGame.get(game.state.players.get(0).decision, game.state.players.get(1).decision);
        uiPlateau.update();
        timerFrame.play();
    }

    public void undo()
    {

    }

    public void redo()
    {

    }

    public void help()
    {
        IADecision ia = new IADecision(Level.HARD);
        Action action = ia.getAction(game);
        
        if(builder.tile != null)
            uiPlateau.getInterfacePlateauMain(game.state.turn.current.color).desurlignerTile(builder.tile);
        if(builder.source != null)
            uiPlateau.ruche.deselectCell(builder.source.comb.pos);
        if(builder.possibleDestinations != null)
            uiPlateau.ruche.desurlignerDestinationsPossibles(builder.possibleDestinations);
        
        ActionGraphicAide gUpdater = new ActionGraphicAide(this);
        action.accept(gUpdater);
    }
}

class ActionGraphicAide implements ActionVisitor
{

    GameController gameController;

    public ActionGraphicAide(GameController gameController)
    {
        this.gameController = gameController;
    }

    @Override
    public void visit(PutAction action)
    {
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(action.where);

        gameController.builder.setTile(action.tile);
        gameController.builder.setDestinations(cells);

        gameController.uiPlateau.getInterfacePlateauMain(gameController.game.state.turn.current.color).surlignerTile(action.tile);
        gameController.uiPlateau.ruche.surlignerDestinationsPossibles(cells);
    }

    @Override
    public void visit(MoveAction action)
    {
        ArrayList<Cell> cells = new ArrayList<>();
        cells.add(action.destination);

        gameController.builder.setSource(action.source);
        gameController.builder.setDestinations(cells);

        gameController.uiPlateau.ruche.selectCell(action.source.comb.pos);
        gameController.uiPlateau.ruche.surlignerDestinationsPossibles(cells);
    }

    @Override
    public void visit(NoAction action)
    {
    }
}
