package hive.controller.plateauscene.game.mousehandlers;

import hive.controller.plateauscene.game.GameController;
import hive.model.board.Cell;
import hive.model.board.Honeycomb;
import hive.model.players.actions.Action;
import hive.model.players.decisions.HumanDecision;
import hive.vue.InterfacePlateau;
import javafx.scene.input.MouseEvent;
import util.Vector2i;

/**
 * Fait une action lorsque que l'on put ou move sur une case (rpz le haut de la
 * pile)
 *
 * @author Thomas
 */
public class SocleHandler extends HandlerPlateau
{

    Honeycomb combClicked;

    public SocleHandler(GameController controller, InterfacePlateau uiPlateau, Vector2i pos)
    {
        super(controller, uiPlateau);
        combClicked = game.state.board.getHexagon(pos);
    }

    @Override
    public void handle(MouseEvent event)
    {
        System.out.println("--- SOCLE ---");

        if (event.getEventType() == MouseEvent.MOUSE_CLICKED)
        {
            if (!(game.state.turn.getCurrent().decision instanceof HumanDecision))
            {
                return;
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
                        System.err.println("Destination impossible");
                    } else
                    {
                        System.out.println("Destination selectionnée");
                        moveOnBoard(new Cell(combClicked));
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
        playProducedAction();

        uiPlateau.ruche.majSource(controller.builder.source);
        uiPlateau.ruche.majDestination(controller.builder.placement_or_destination);
    }

    public void putOnBoard(Cell placement)
    {
        uiPlateau.ruche.desurlignerDestinationsPossibles(controller.builder.possibleDestinations);

        controller.builder.setPlacement(placement);
        playProducedAction();

        uiPlateau.majTileMain(controller.builder.tile, controller.progress.game.state.turn.getOpponent().collection.get(controller.builder.tile.type));
        uiPlateau.ruche.majPlacement(controller.builder.placement_or_destination);
    }

    private void playProducedAction()
    {
        assert game.state.turn.getCurrent().decision instanceof HumanDecision;
        Action action = controller.builder.produce();
        ((HumanDecision) game.state.turn.getCurrent().decision).setAction(action);
        controller.progress.doAction();
    }
}
