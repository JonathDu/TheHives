/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.plateau.graphicaction;

import hive.model.game.Game;
import hive.model.players.actions.ActionVisitor;
import hive.model.players.actions.MoveAction;
import hive.model.players.actions.NoAction;
import hive.model.players.actions.PutAction;
import hive.vue.InterfacePlateau;

/**
 *
 * @author lucas
 */
public class ActionGraphicUpdaterWithoutSelect implements ActionVisitor
{

    InterfacePlateau uiPlateau;
    Game game;

    public ActionGraphicUpdaterWithoutSelect(InterfacePlateau uiPlateau, Game game)
    {
        this.uiPlateau = uiPlateau;
        this.game = game;
    }

    @Override
    public void visit(PutAction action)
    {
        uiPlateau.getInterfacePlateauMain(game.state.turn.getCurrent().color).maj(action.tile, game.state.turn.getCurrent().collection.get(action.tile.type));
        uiPlateau.getInterfacePlateauMain(game.state.turn.getOpponent().color).maj(action.tile, game.state.turn.getOpponent().collection.get(action.tile.type));
        uiPlateau.ruche.majPlacement(action.where);
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

