/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.gamescene.game.handlers;

import hive.controller.gamescene.game.GameController;
import hive.model.board.Tile;
import hive.model.insects.InsectType;
import hive.model.players.TeamColor;
import hive.model.players.decisions.Decision;
import hive.model.players.decisions.HumanDecision;
import hive.vue.InterfacePlateau;
import hive.vue.InterfacePlateauMain;
import javafx.scene.input.MouseEvent;

/**
 * Appelé lorque que l'on clique sur une tile d'une des pioches du joueur
 *
 * @author jonathan
 */
public class TileMainHandler extends HandlerPlateau
{
    TeamColor color;
    InterfacePlateauMain uiMain;
    Tile tileClicked;

    public TileMainHandler(GameController controller, InterfacePlateau uiPlateau, TeamColor color, InsectType insectType)
    {
        super(controller, uiPlateau);
        tileClicked = new Tile(insectType, color);
        this.color = color;
    }

    @Override
    public void handle(MouseEvent event)
    {
        uiMain = uiPlateau.getInterfacePlateauMain(color);

        System.out.println("--- TILE MAIN ---");

        if (event.getEventType() == MouseEvent.MOUSE_CLICKED)
        {
            Decision decision = controller.progress.game.state.turn.getCurrent().decision;

            if (!(decision instanceof HumanDecision))
            {
                return;
            }

            switch (controller.builder.getState())
            {
                case BEGIN:
                    System.out.println("Tile selectionnée");

                    controller.builder.setTile(tileClicked);
                    controller.builder.setPossibleDestinations(game.rules.getPossiblePlacements(game.state, tileClicked));

                    uiMain.surlignerTile(action_tile);
                    uiPlateau.ruche.surlignerCells(controller.builder.possibleDestinations);
                    break;
                case SOURCE_SELECTED:
                    System.out.println("Tile selectionnée");

                    uiPlateau.ruche.deselectCell(action_source.comb.pos);
                    uiPlateau.ruche.desurlignerCells(controller.builder.possibleDestinations);

                    controller.builder.setTile(tileClicked);
                    controller.builder.setPossibleDestinations(game.rules.getPossiblePlacements(game.state, tileClicked));

                    uiMain.surlignerTile(action_tile);
                    uiPlateau.ruche.surlignerCells(controller.builder.possibleDestinations);
                    break;
                case TILE_SELECTED:
                    if (tileClicked.type != action_tile.type)
                    {
                        System.out.println("Changement de tile");

                        uiMain.desurlignerTile(action_tile);
                        uiPlateau.ruche.desurlignerCells(controller.builder.possibleDestinations);
                        controller.builder.setTile(tileClicked);
                        uiMain.surlignerTile(action_tile);
                    } else
                    {
                        System.err.println("Aucun changement : tile deja selectionnée");
                    }
                    break;
            }
        }
    }
}
