/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.plateau.handlers.mousehandlers;

import hive.controller.plateau.PlateauController;
import hive.model.board.Cell;
import hive.model.game.rules.HiveUtil;
import javafx.scene.input.MouseEvent;

/**
 * Fait une action lorsque l'on selectionne la source
 *
 * @author Thomas
 */
public class TilePlateauHandler extends PlateauHandler
{

    Cell cellClicked;

    public TilePlateauHandler(PlateauController controller, Cell cellClicked)
    {
        super(controller);
        this.cellClicked = cellClicked;
    }

    @Override
    public void handlePlateau(MouseEvent event)
    {
        System.out.println("--- TILE PLATEAU ---");

        if (event.getEventType() == MouseEvent.MOUSE_CLICKED)
        {
            switch (controller.builder.getState())
            {
                case BEGIN:
                    if (cellClicked.getTile().color != progress.game.state.turn.getCurrent().color)
                    {
                        return;
                    }
                    System.out.println("Source selectionnée");

                    controller.builder.setSource(cellClicked);
                    controller.builder.setDestinations(HiveUtil.getDestinations(game, cellClicked));

                    uiPlateau.ruche.selectPlayerCell(controller.builder.source.comb.pos);
                    uiPlateau.ruche.surlignerDestinationsPossibles(controller.builder.possibleDestinations);
                    event.consume();
                    break;
                case SOURCE_SELECTED:
                    if (cellClicked == controller.builder.source)
                    {
                        System.err.println("Même source : annulation de la selection");
                        uiPlateau.ruche.deselectCell(controller.builder.source.comb.pos);
                        uiPlateau.ruche.desurlignerDestinationsPossibles(controller.builder.possibleDestinations);
                        controller.builder.setBegin();
                        event.consume();
                    }
                    break;
                case TILE_SELECTED:
                    if (cellClicked.getTile().color == game.state.turn.getCurrent().color)
                    {
                        uiPlateau.getInterfacePlateauMain(game.state.turn.getCurrent().color).desurlignerTile(controller.builder.tile);
                        uiPlateau.ruche.desurlignerDestinationsPossibles(controller.builder.possibleDestinations);
                        controller.builder.setBegin();

                        controller.builder.setSource(cellClicked);
                        controller.builder.setDestinations(HiveUtil.getDestinations(game, cellClicked));

                        uiPlateau.ruche.selectPlayerCell(controller.builder.source.comb.pos);
                        uiPlateau.ruche.surlignerDestinationsPossibles(controller.builder.possibleDestinations);
                        System.out.println("Changement : on ne place pas, on selectionne une source");
                    }
                    event.consume();
                    break;
            }
        }
    }
}
