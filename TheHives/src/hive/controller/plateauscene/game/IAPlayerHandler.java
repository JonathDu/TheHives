/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.plateauscene.game;

import hive.model.GameProgress;
import hive.model.game.Game;
import hive.model.game.GameState;
import hive.model.players.actions.ActionVisitor;
import hive.model.players.actions.MoveAction;
import hive.model.players.actions.NoAction;
import hive.model.players.actions.PutAction;
import hive.model.players.decisions.IADecision;
import hive.vue.InterfacePlateau;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author lucas
 */
public class IAPlayerHandler extends PlateauHandlerData implements EventHandler<ActionEvent>
{
    public IAPlayerHandler(GameController controller)
    {
        super(controller);
    }

    @Override
    public void handle(ActionEvent event)
    {
        if (progress.game.state.turn.getCurrent().decision instanceof IADecision)
        {
            System.out.println("IA FAIT ACTION");
            progress.doAction();
            ActionGraphicUpdater gUpdater = new ActionGraphicUpdater(uiPlateau, progress.game);
            progress.game.state.data.trace.peek().accept(gUpdater);
        } else
        {
            throw new RuntimeException("IADecision attendu");
        }
    }
}

class ActionGraphicUpdater implements ActionVisitor
{

    InterfacePlateau uiPlateau;
    Game game;

    public ActionGraphicUpdater(InterfacePlateau uiPlateau, Game game)
    {
        this.uiPlateau = uiPlateau;
        this.game = game;
    }

    @Override
    public void visit(PutAction action)
    {
        uiPlateau.ruche.majPlacement(action.where);
        uiPlateau.majTileMain(action.tile, game.state.turn.getOpponent().collection.get(action.tile.type));
    }

    @Override
    public void visit(MoveAction action)
    {
        uiPlateau.ruche.majSource(action.source);
        uiPlateau.ruche.majDestination(action.destination);
    }

    @Override
    public void visit(NoAction action)
    {
    }
}
