/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.gamescene.game.handlers;

import hive.controller.gamescene.game.GameController;
import hive.model.board.Tile;
import hive.model.game.Game;
import hive.model.insects.InsectType;
import hive.model.players.TeamColor;
import hive.model.players.decisions.Decision;
import hive.model.players.decisions.HumanDecision;
import hive.vue.InterfacePlateau;
import hive.vue.InterfacePlateauMain;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Appelé lorque que l'on clique sur une tile d'une des pioches du joueur
 *
 * @author jonathan
 */
public class TileMainHandler implements EventHandler<MouseEvent>
{

    //TODO : différencier quel joueur click ???
    GameController controller;
    InterfacePlateau uiPlateau;
    InterfacePlateauMain uiMain;
    Tile tile;
    Game game;

    public TileMainHandler(GameController controller, InterfacePlateau uiPlateau, InterfacePlateauMain uiMain, TeamColor color, InsectType insectType)
    {
        this.controller = controller;
        this.uiPlateau = uiPlateau;
        this.uiMain = uiMain;
        this.tile = new Tile(insectType, color);
        this.game = controller.progress.game;
    }

    @Override
    public void handle(MouseEvent event)
    {
        System.out.println("--- TILE MAIN ---");

        if (event.getEventType() == MouseEvent.MOUSE_CLICKED)
        {
            Decision decision = controller.progress.game.state.turn.getCurrent().decision;
            if (decision instanceof HumanDecision)
            {
                switch (controller.builder.getState())
                {
                    case BEGIN:
                        System.out.println("Tile selectionnée");
                        
                         /* ACTION BUILDER */
                        controller.builder.setTile(tile);
                        controller.builder.setPossibleDestinations(game.rules.getPossiblePlacements(game.state, tile));
                        
                        /* MAJ GRPAHIQUE */
                        uiMain.surlignerTile(tile); // MAJ graphique : surligne la tile selectionnée
                        uiPlateau.ruche.surlignerCells(controller.builder.possibleDestinations);// MAJ graphique : les destinations possibles
                        break;
                    case TILE_SELECTED:
                        if (tile.type != controller.builder.tile.type)
                        {
                            System.out.println("Changement de tile");
                            uiMain.desurlignerTile(controller.builder.tile);
                            controller.builder.setTile(tile);
                            uiMain.surlignerTile(tile);
                        } else
                        {
                            System.err.println("Aucun changement : tile deja selectionnée");
                        }
                        break;
                }
            }
        } else if (true) // autre evenement ? mouseOver ?
        {
            // information about the tile ? IA or not
            // etc
        }
    }
}
