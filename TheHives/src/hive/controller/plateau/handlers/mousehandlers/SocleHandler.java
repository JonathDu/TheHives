package hive.controller.plateau.handlers.mousehandlers;

import hive.controller.plateau.PlateauController;
import hive.controller.plateau.graphicaction.ActionGraphicUpdater;
import hive.model.board.Cell;
import hive.model.board.Honeycomb;
import javafx.event.Event;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import util.Vector2i;

/**
 * Fait une action lorsque que l'on put ou move sur une case (rpz le haut de la
 * pile)
 *
 * @author Thomas
 */
public class SocleHandler extends PlateauHandler
{

    Honeycomb combClicked;

    public SocleHandler(PlateauController controller, Vector2i pos)
    {
        super(controller);
        combClicked = game.state.board.getHexagon(pos);
    }

    @Override
    public void handlePlateau(Event event)
    {
        System.out.println("--- SOCLE ---");

        if (event.getEventType() == DragEvent.DRAG_DROPPED || event.getEventType() == MouseEvent.MOUSE_CLICKED || event.getEventType() == MouseEvent.MOUSE_RELEASED)
        {
            if (event.getEventType() == DragEvent.DRAG_DROPPED)
            {
                System.out.println("yo");
                DragEvent e = (DragEvent) event;
                Dragboard db = e.getDragboard();
                boolean success = false;
                if (db.hasString())
                {
                    success = true;
                }
                /* let the source know whether the string was successfully 
            * transferred and used */
                e.setDropCompleted(success);

                event.consume();
            }
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
                            uiPlateau.ruche.desurlignerDestinationsPossibles(controller.builder.possibleDestinations);
                            uiPlateau.ruche.deselectCell(combClicked.pos);
                            controller.builder.setBegin();
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
                        uiPlateau.ruche.desurlignerDestinationsPossibles(controller.builder.possibleDestinations);
                        uiPlateau.getInterfacePlateauMain(game.state.turn.getCurrent().color).desurlignerTile(controller.builder.tile);
                        controller.builder.setBegin();
                        System.err.println("Placement impossible"); //on laisse passer l'event pour que TilePlateauHandler traite le coup ou on selectionne une source au lieu d'un placement
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

        ActionGraphicUpdater gUpdater = new ActionGraphicUpdater(uiPlateau, game);
        game.state.data.trace.peek().accept(gUpdater);
    }

    public void putOnBoard(Cell placement)
    {
        uiPlateau.ruche.desurlignerDestinationsPossibles(controller.builder.possibleDestinations);

        controller.builder.setPlacement(placement);
        controller.doProducedAction();

        ActionGraphicUpdater gUpdater = new ActionGraphicUpdater(uiPlateau, game);
        game.state.data.trace.peek().accept(gUpdater);
    }

}
