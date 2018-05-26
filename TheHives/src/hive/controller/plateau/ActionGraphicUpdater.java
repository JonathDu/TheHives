/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.plateau;

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
public class ActionGraphicUpdater implements ActionVisitor
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