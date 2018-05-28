package hive.controller.plateau.handlers.mousehandlers;

import hive.controller.plateau.handlers.PlateauHandlerData;
import hive.controller.plateau.PlateauController;
import hive.model.board.Cell;
import hive.model.board.Honeycomb;
import hive.model.players.decisions.HumanDecision;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import util.Vector2i;

/**
 * Fait une action lorsque que l'on put ou move sur une case (rpz le haut de la
 * pile)
 *
 * @author Thomas
 */
public class SocleHandler extends PlateauHandlerData implements EventHandler<MouseEvent>
{

    Honeycomb combClicked;

    public SocleHandler(PlateauController controller, Vector2i pos)
    {
        super(controller);
        combClicked = game.state.board.getHexagon(pos);
    }

    @Override
    public void handle(MouseEvent event)
    {
        if (!(game.state.turn.getCurrent().decision instanceof HumanDecision))
        {
            return;
        }
        
        System.out.println("--- SOCLE ---");

        if (event.getEventType() == MouseEvent.MOUSE_CLICKED)
        {
            switch (controller.builder.getState())
            {
                case SOURCE_SELECTED:
                    if (new Cell(combClicked, controller.builder.source.level).equals(controller.builder.source))
                    {
                        uiPlateau.ruche.deselectCell(combClicked.pos);
                        uiPlateau.ruche.desurlignerDestinationsPossibles(controller.builder.possibleDestinations);
                        controller.builder.setBegin();
                        System.err.println("Même source : on annule la selection");
                    } else if (!controller.builder.possibleDestinations.contains(new Cell(combClicked)))
                    {
                        if (controller.builder.source.comb.value.peek().color == game.state.turn.current.color)
                        {
                            System.out.println("Changement de source");
                            uiPlateau.ruche.deselectCell(controller.builder.source.comb.pos);
                            uiPlateau.ruche.desurlignerDestinationsPossibles(controller.builder.possibleDestinations);

                            controller.builder.setBegin();
                            return;
                        } else
                        {
                            System.err.println("Destination impossible");
                        }
                    } else
                    {
                        System.out.println("Destination selectionnée");
                        moveOnBoard(new Cell(combClicked));
                        controller.startOfTurnInfos();
                    }
                    event.consume();
                    break;
                case TILE_SELECTED:
                    if (!controller.builder.possibleDestinations.contains(new Cell(combClicked)))
                    {
                        System.err.println("Placement impossible");
                        //on laisse passer l'event pour que TilePlateauHandler traite le coup ou on selectionne une source au lieu d'un placement
                    } else
                    {
                        System.out.println("Placement selectionné");
                        putOnBoard(new Cell(combClicked));
                        controller.startOfTurnInfos();
                        event.consume();
                    }
                    break;
            }
        }
    }

    public void moveOnBoard(Cell destination)
    {
        uiPlateau.ruche.deselectCell(controller.builder.source.comb.pos);
        uiPlateau.ruche.desurlignerDestinationsPossibles(controller.builder.possibleDestinations);

        controller.builder.setDestination(destination);
        controller.doProducedAction();

        uiPlateau.ruche.majSource(controller.builder.source);
        uiPlateau.ruche.majDestination(controller.builder.placement_or_destination);
    }

    public void putOnBoard(Cell placement)
    {
        uiPlateau.ruche.desurlignerDestinationsPossibles(controller.builder.possibleDestinations);

        controller.builder.setPlacement(placement);
        controller.doProducedAction();

        uiPlateau.majTileMain(controller.builder.tile, game.state.turn.getOpponent().collection.get(controller.builder.tile.type));
        uiPlateau.ruche.majPlacement(controller.builder.placement_or_destination);
    }

}
