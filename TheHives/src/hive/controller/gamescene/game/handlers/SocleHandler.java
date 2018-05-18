package hive.controller.gamescene.game.handlers;

import hive.controller.gamescene.game.GameController;
import hive.model.board.Cell;
import hive.model.board.Honeycomb;
import hive.model.players.decisions.Decision;
import hive.model.players.decisions.HumanDecision;
import hive.vue.InterfacePlateau;
import javafx.scene.input.MouseEvent;
import util.Vector2i;

/**
 * Appelé lorsque que l'on veut put ou move sur une case (rpz le haut de la pile)
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
            Decision decision = game.state.turn.getCurrent().decision;

            if (!(decision instanceof HumanDecision))
            {
                return;
            }

            HumanDecision human_decision = (HumanDecision) decision;

            switch (controller.builder.getState())
            {
                case SOURCE_SELECTED:
                    if (new Cell(combClicked) == controller.builder.source)
                    {
                        System.out.println("Même source : aucune action");
                        return;
                    }
                    System.out.println("Destination selectionnée");
                    moveOnBoard(human_decision, new Cell(combClicked));
                    break;
                case TILE_SELECTED:
                    System.out.println("Placement selectionné");
                    putOnBoard(human_decision, new Cell(combClicked));
                    break;
            }
        }
    }
}
