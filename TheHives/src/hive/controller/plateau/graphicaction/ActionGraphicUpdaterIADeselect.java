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
public class ActionGraphicUpdaterIADeselect implements ActionVisitor
{

    InterfacePlateau uiPlateau;
    Game game;

    public ActionGraphicUpdaterIADeselect(InterfacePlateau uiPlateau, Game game)
    {
        this.uiPlateau = uiPlateau;
        this.game = game;
    }

    @Override
    public void visit(PutAction action)
    {
        uiPlateau.ruche.deselectCell(action.where.comb.pos);

    }

    @Override
    public void visit(MoveAction action)
    {
        uiPlateau.ruche.deselectCell(action.destination.comb.pos);
    }

    @Override
    public void visit(NoAction action)
    {
    }
}

