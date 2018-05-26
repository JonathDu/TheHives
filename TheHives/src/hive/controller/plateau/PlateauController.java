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
import hive.controller.plateau.graphicaction.ActionGraphicUpdaterIADeselect;
import hive.controller.plateau.graphicaction.ActionGraphicUpdaterIASelect;
import hive.model.GameProgress;
import hive.model.board.Tile;
import hive.model.game.DefaultGame;
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
    public InterfacePlateau uiPlateau;

    public ActionBuilder builder;
    public Timeline timerJouerIA;
    public Timeline timerFrame;

    public PlateauController(Game game, InterfacePlateau uiPlateau)
    {
        this.game = game;
        this.uiPlateau = uiPlateau;

        this.builder = new ActionBuilder();

        timerJouerIA = new Timeline(new KeyFrame(Duration.millis(200), new IAPlayerHandler(this)));
        timerJouerIA.setCycleCount(1);
        timerFrame = new Timeline(new KeyFrame(Duration.millis(60), new FrameHandler(this)));
        timerFrame.setCycleCount(Timeline.INDEFINITE);
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

    public void restart()
    {
        timerFrame.stop();
        while(!game.state.data.trace.isEmpty())
        {
            undo();
        }
        game.state.data.undos.clear();
        timerFrame.play();
    }

    public void doProducedAction()
    {
        assert game.state.turn.getCurrent().decision instanceof HumanDecision;
        Action action = builder.produce();
        ((HumanDecision) game.state.turn.getCurrent().decision).setAction(action);
        GameProgress progress = new GameProgress(game);
        progress.doAction();
    }

    public void startOfTurnInfos()
    {
        switch (game.rules.getStatus(game.state))
        {
            case CONTINUES:
            {
                System.out.println("Tour suivant");
                uiPlateau.majJoueurCourant(game.state.turn.getCurrent().color);
                if (game.state.turn.getCurrent().decision instanceof IADecision)
                {
                    return;
                }
                if (!currentPlayerCanPlay())
                {
                    System.out.println("Vous ne pouvez pas jouer => tour suivant");
                    NoAction noAction = new NoAction();
                    ((HumanDecision) game.state.turn.getCurrent().decision).setAction(noAction);
                    GameProgress progress = new GameProgress(game);
                    progress.doAction();
                    startOfTurnInfos();
                    //TODO : popup "vous ne pouvez pas jouer : passage au tour suivant"
                }
                if (game.rules.queenMustBePut(game.state))
                {
                    System.out.println("Vous devez poser la reine");
                    Tile tileClicked = new Tile(InsectType.QUEEN_BEE, game.state.turn.getCurrent().color);
                    builder.setTile(tileClicked);
                    builder.setDestinations(HiveUtil.getPlacements(game, tileClicked.type));
                    uiPlateau.getInterfacePlateauMain(builder.tile.color).surlignerTile(builder.tile);
                    uiPlateau.ruche.surlignerDestinationsPossibles(builder.possibleDestinations);
                    //TODO : popup "il faut poser la reine"
                }
            }
            break;
            case CURRENT_WINS:
                System.out.println("Le joueur courrant a gagné");
                stop();
                //TODO : popup "le joueur courrant a gagné"
                break;
            case OPPONENT_WINS:
                System.out.println("Le joueur opposé a gagné");
                stop();
                //TODO : popup "le joueur courrant a gagné"
                break;
            case DRAW:
                System.out.println("Plus personne ne peut joueur : match nul");
                stop();
                //TODO : popup "plus personne ne peut joueur : match nul"
                break;
        }
    }

    private boolean currentPlayerCanPlay()
    {
        return !game.state.data.placements.isEmpty() || !HiveUtil.getDestinations(game).isEmpty(); //TODO : ne marche pas
    }

    public void undo()
    {
        if (game.state.data.trace.isEmpty() || game.rules.getStatus(game.state) != GameStatus.CONTINUES)
        {
            return;
        }
        timerJouerIA.stop();
        resetBuilder();

        GameProgress progress = new GameProgress(game);
        progress.undoAction();

        ActionGraphicUpdater gUpdater = new ActionGraphicUpdater(uiPlateau, progress.game);
        game.state.data.undos.peek().accept(gUpdater);

        ActionGraphicUpdaterIADeselect gUpdaterDeselect = new ActionGraphicUpdaterIADeselect(uiPlateau, game);
        progress.game.state.data.undos.peek().accept(gUpdaterDeselect);

        startOfTurnInfos();
    }

    public void redo()
    {
        if (game.state.data.undos.isEmpty() || game.rules.getStatus(game.state) != GameStatus.CONTINUES)
        {
            return;
        }
        timerJouerIA.stop();
        resetBuilder();
        GameProgress progress = new GameProgress(game);

        if (game.state.turn.getCurrent().decision instanceof IADecision)
        {
            if (game.state.data.trace.size() > 2)
            {
                ActionGraphicUpdaterIADeselect gUpdaterDeselect = new ActionGraphicUpdaterIADeselect(uiPlateau, game);
                progress.game.state.data.trace.get(progress.game.state.data.trace.size() - 2).accept(gUpdaterDeselect);
            }
        }

        progress.redoAction();

        ActionGraphicUpdater gUpdater = new ActionGraphicUpdater(uiPlateau, progress.game);
        game.state.data.trace.peek().accept(gUpdater);

        if (game.state.turn.getOpponent().decision instanceof IADecision)
        {
            ActionGraphicUpdaterIASelect gUpdaterSelect = new ActionGraphicUpdaterIASelect(uiPlateau, game);
            progress.game.state.data.trace.peek().accept(gUpdaterSelect);
        }

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
