/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.plateauscene.game;

import hive.model.GameProgress;
import hive.model.board.Cell;
import hive.model.game.DefaultGame;
import hive.model.game.Game;
import hive.model.players.actions.Action;
import hive.model.players.actions.ActionVisitor;
import hive.model.players.actions.MoveAction;
import hive.model.players.actions.NoAction;
import hive.model.players.actions.PutAction;
import hive.model.players.decisions.HumanDecision;
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

        timerJouerIA = new Timeline(new KeyFrame(Duration.millis(10), new IAPlayerHandler(this)));
        timerJouerIA.setCycleCount(1);
        timerFrame = new Timeline(new KeyFrame(Duration.millis(60), new FrameHandler(this)));
        timerFrame.setCycleCount(Timeline.INDEFINITE);
    }
    
    private void resetBuilder()
    {
        if(builder.tile != null)
            uiPlateau.getInterfacePlateauMain(game.state.turn.current.color).desurlignerTile(builder.tile);
        if(builder.source != null)
            uiPlateau.ruche.deselectCell(builder.source.comb.pos);
        if(builder.possibleDestinations != null)
            uiPlateau.ruche.desurlignerDestinationsPossibles(builder.possibleDestinations);
        builder.setBegin();
    }

    public void start()
    {
        timerFrame.play();
    }
    
    public void restart()
    {
        timerFrame.stop();
        builder.setBegin();
        game = DefaultGame.get(new HumanDecision(), new HumanDecision());
        uiPlateau.update();
        timerFrame.play();
    }

    public void undo()
    {
        resetBuilder();
        GameProgress progress = new GameProgress(game);
        progress.undoAction();
        ActionGraphicUpdater gUpdater = new ActionGraphicUpdater(uiPlateau, progress.game);
        game.state.data.trace.peek().accept(gUpdater);
    }

    public void redo()
    {       
        resetBuilder();
        GameProgress progress = new GameProgress(game);
        progress.redoAction();
        ActionGraphicUpdater gUpdater = new ActionGraphicUpdater(uiPlateau, progress.game);
        game.state.data.undos.peek().accept(gUpdater);

    }

    public void help()
    {
        IADecision ia = new IADecision(Level.HARD);
        Action action = ia.getAction(game);
        
        resetBuilder();
        
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
