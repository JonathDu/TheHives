/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.plateau;

import hive.controller.plateau.handlers.IAPlayerHandler;
import hive.controller.plateau.handlers.FrameHandler;
import hive.controller.plateau.graphicaction.ActionGraphicUpdaterHelp;
import hive.controller.plateau.graphicaction.ActionGraphicUpdater;
import hive.controller.plateau.graphicaction.ActionGraphicUpdaterDeselect;
import hive.controller.plateau.graphicaction.ActionGraphicUpdaterWithoutSelect;
import hive.model.GameProgress;
import hive.model.board.Tile;
import hive.model.game.Game;
import hive.model.game.rules.GameStatus;
import hive.model.game.rules.HiveUtil;
import hive.model.insects.InsectType;
import hive.model.players.actions.Action;
import hive.model.players.actions.NoAction;
import hive.model.players.decisions.HumanDecision;
import hive.model.players.decisions.IA.Level;
import hive.model.players.decisions.IADecision;
import hive.vue.InterfacePlateau;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 *
 * @author lucas
 */
public class PlateauController
{

    public Game game;
    public GameProgress progress;
    public InterfacePlateau uiPlateau;

    public ActionBuilder builder;
    public Timeline timerJouerIA;
    public Timeline timerFrame;

    public int nbGames;

    public PlateauController(Game game, InterfacePlateau uiPlateau)
    {
        this.game = game;
        this.progress = new GameProgress(game);
        this.uiPlateau = uiPlateau;

        this.builder = new ActionBuilder();

        timerJouerIA = new Timeline(new KeyFrame(Duration.millis(200), new IAPlayerHandler(this)));
        timerJouerIA.setCycleCount(1);
        timerFrame = new Timeline(new KeyFrame(Duration.millis(60), new FrameHandler(this)));
        timerFrame.setCycleCount(Timeline.INDEFINITE);

        nbGames = 0;
    }

    private void resetBuilder()
    {
        switch (builder.getState())
        {
            case SOURCE_SELECTED:
                uiPlateau.ruche.deselectCell(builder.source.comb.pos);
                uiPlateau.ruche.desurlignerDestinationsPossibles(builder.possibleDestinations);
                break;
            case TILE_SELECTED:
                uiPlateau.getInterfacePlateauMain(game.state.turn.current.color).desurlignerTile(builder.tile);
                uiPlateau.ruche.desurlignerDestinationsPossibles(builder.possibleDestinations);
                break;
        }
        builder.setBegin();
    }

    public void start()
    {
        timerFrame.play();
    }

    public void playPause()
    {
            if (timerFrame.getStatus() == Animation.Status.PAUSED)
        {
            //TODO : popup "reprise du jeu"
            System.out.println("reprise du jeu");
            timerFrame.play();
        } else
        {
            //TODO : popup "pause"
            System.out.println("pause");
            timerFrame.pause();
        }
    }

    public void stop()
    {
        builder.setEnd();
        timerFrame.stop();
        timerJouerIA.stop();
    }

    public void restart() //TODO : changer les deux joueurs
    {
        timerFrame.stop();
        while (!game.state.data.trace.isEmpty())
        {
            undo();
        }
        game.state.data.undos.clear();
        nbGames++;
        game.state.turn.current = game.state.players.get(nbGames % 2);
        game.state.turn.opponent = game.state.players.get((nbGames + 1) % 2);
        uiPlateau.majJoueurCourant(game.state.turn.current.color);
        timerFrame.play();
    }

    public void doProducedAction()
    {
        assert game.state.turn.getCurrent().decision instanceof HumanDecision;
        Action action = builder.produce();
        ((HumanDecision) game.state.turn.getCurrent().decision).setAction(action);
        progress.doAction();
    }

    public void startOfTurnInfos()
    {
        switch (game.rules.getStatus(game.state))
        {
            case CONTINUES:
            {
                uiPlateau.majJoueurCourant(game.state.turn.getCurrent().color);
                if (game.state.turn.getCurrent().decision instanceof IADecision)
                {
                    return;
                }
                if (!currentPlayerCanPlay())
                {
                    if (!progress.game.state.data.trace.isEmpty())
                    {
                        ActionGraphicUpdaterDeselect gUpdaterDeselect = new ActionGraphicUpdaterDeselect(uiPlateau, game);
                        progress.game.state.data.trace.peek().accept(gUpdaterDeselect);
                    }
                    NoAction noAction = new NoAction();
                    ((HumanDecision) game.state.turn.getCurrent().decision).setAction(noAction);
                    progress.doAction();
                    startOfTurnInfos();
                    uiPlateau.message("Attention", "Vous ne pouvez pas jouer, passage au tour suivant");
                }
            }
            break;
            case CURRENT_WINS:
                uiPlateau.finPartie(game.state.turn.current.toString(), game.state.turn.opponent.toString());
                stop();
                break;
            case OPPONENT_WINS:
                uiPlateau.finPartie(game.state.turn.opponent.toString(), game.state.turn.current.toString());
                stop();
                break;
            case DRAW:
                uiPlateau.finPartie(null, null);
                stop();
                break;
        }
    }

    private boolean currentPlayerCanPlay()
    {
        boolean hasPlacement = false;
        for (InsectType type : InsectType.implemented_insects)
        {
            if (game.state.turn.getCurrent().collection.get(type) > 0)
            {
                if (!HiveUtil.getPlacements(game, type).isEmpty())
                {
                    hasPlacement = true;
                    break;
                }
            }
        }
        return hasPlacement || !HiveUtil.getDestinations(game).isEmpty();
    }

    public void undo()
    {
        if (game.state.data.trace.isEmpty())
        {
            return;
        }
        timerJouerIA.stop();
        resetBuilder();

        progress.undoAction();

        ActionGraphicUpdaterWithoutSelect gUpdater = new ActionGraphicUpdaterWithoutSelect(uiPlateau, progress.game);
        game.state.data.undos.peek().accept(gUpdater);

        startOfTurnInfos();
    }

    public void redo()
    {
        if (game.state.data.undos.isEmpty())
        {
            return;
        }
        timerJouerIA.stop();
        resetBuilder();

        if (!progress.game.state.data.trace.isEmpty())
        {
            ActionGraphicUpdaterDeselect gUpdaterDeselect = new ActionGraphicUpdaterDeselect(uiPlateau, game);
            progress.game.state.data.trace.peek().accept(gUpdaterDeselect);
        }

        progress.redoAction();

        ActionGraphicUpdater gUpdater = new ActionGraphicUpdater(uiPlateau, progress.game);
        game.state.data.trace.peek().accept(gUpdater);

        startOfTurnInfos();
    }

    public void help()
    {
        if (game.rules.getStatus(game.state) != GameStatus.CONTINUES || game.state.turn.getCurrent().decision instanceof IADecision)
        {
            return;
        }
        resetBuilder();

        IADecision ia = new IADecision(Level.HARD);
        Action action = ia.getAction(game);

        ActionGraphicUpdaterHelp gUpdater = new ActionGraphicUpdaterHelp(this);
        action.accept(gUpdater);
    }

    public boolean isIAvsIA()
    {
        return (game.state.players.get(0).decision instanceof IADecision) && (game.state.players.get(1).decision instanceof IADecision);
    }
}
