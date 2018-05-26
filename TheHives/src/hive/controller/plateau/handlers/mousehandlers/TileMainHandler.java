/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.plateau.handlers.mousehandlers;

import hive.controller.plateau.handlers.PlateauHandlerData;
import hive.controller.plateau.PlateauController;
import hive.model.board.Tile;
import hive.model.game.rules.HiveUtil;
import hive.model.insects.InsectType;
import hive.model.players.TeamColor;
import hive.model.players.decisions.HumanDecision;
import hive.vue.NodePlateauMain;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Appelé lorque que l'on clique sur une tile d'une des pioches du joueur
 *
 * @author jonathan
 */
public class TileMainHandler extends PlateauHandlerData implements EventHandler<MouseEvent>
{

    TeamColor color;
    NodePlateauMain uiMain;
    Tile tileClicked;

    public TileMainHandler(PlateauController controller, TeamColor color, InsectType insectType)
    {
        super(controller);
        tileClicked = new Tile(insectType, color);
        this.color = color;
    }

    @Override
    public void handle(MouseEvent event)
    {
        if (!(game.state.turn.getCurrent().decision instanceof HumanDecision))
        {
            return;
        }
        
        if (tileClicked.color != game.state.turn.getCurrent().color)
        {
            System.err.println("Vous n'avez pas selectionné un pion de votre couleur");
            return;
        }
        uiMain = uiPlateau.getInterfacePlateauMain(color);

        System.out.println("--- TILE MAIN ---");

        if (event.getEventType() == MouseEvent.MOUSE_CLICKED)
        {
            switch (controller.builder.getState())
            {
                case BEGIN:
                    System.out.println("Tile selectionnée");

                    controller.builder.setTile(tileClicked);
                    controller.builder.setDestinations(HiveUtil.getPlacements(game, tileClicked.type));

                    uiMain.surlignerTile(controller.builder.tile);
                    uiPlateau.ruche.surlignerDestinationsPossibles(controller.builder.possibleDestinations);
                    event.consume();
                    break;
                case SOURCE_SELECTED:
                    System.out.println("Tile selectionnée");

                    uiPlateau.ruche.deselectCell(controller.builder.source.comb.pos);
                    uiPlateau.ruche.desurlignerDestinationsPossibles(controller.builder.possibleDestinations);

                    controller.builder.setTile(tileClicked);
                    controller.builder.setDestinations(HiveUtil.getPlacements(game, tileClicked.type));

                    uiMain.surlignerTile(controller.builder.tile);
                    uiPlateau.ruche.surlignerDestinationsPossibles(controller.builder.possibleDestinations);
                    event.consume();
                    break;
                case TILE_SELECTED:
                    if (tileClicked.type != controller.builder.tile.type)
                    {
                        System.out.println("Changement de tile");

                        uiMain.desurlignerTile(controller.builder.tile);
                        uiPlateau.ruche.desurlignerDestinationsPossibles(controller.builder.possibleDestinations);
                        controller.builder.setTile(tileClicked);
                        controller.builder.setDestinations(HiveUtil.getPlacements(game, tileClicked.type));
                        uiMain.surlignerTile(controller.builder.tile);
                        uiPlateau.ruche.surlignerDestinationsPossibles(controller.builder.possibleDestinations);
                    } else
                    {
                        System.err.println("Annuler la selection de la tile");
                        uiMain.desurlignerTile(tileClicked);
                        uiPlateau.ruche.desurlignerDestinationsPossibles(controller.builder.possibleDestinations);
                        controller.builder.setBegin();
                    }
                    event.consume();
                    break;
            }
        }
    }
}
