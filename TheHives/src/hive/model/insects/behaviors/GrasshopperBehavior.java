/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.insects.behaviors;

import hive.model.board.Cell;
import hive.model.board.Cells;
import hive.model.board.Honeycomb;
import hive.model.game.Game;
import hive.model.insects.InsectBehavior;
import java.util.ArrayList;
import util.hexagons.HexagonSide;
import util.hexagons.iterators.LineAtSideIterator;
import util.iterators.StoppingIterator;

/**
 *
 * @author Thomas
 */
public class GrasshopperBehavior implements InsectBehavior
{
    @Override
    public ArrayList<Cell> getPossibleDestinations(Game game, Cell cell)
    {
        assert cell.index == 0;
        
        ArrayList<Cell> list = new ArrayList<>();
        
        if(Cells.isCrushed(cell) || !Cells.isConnexWithout(cell, game.state.data.nb_tiles))
            return list;
        
        for(HexagonSide side : HexagonSide.values())
        {
            // start in line after the neighbor
            StoppingIterator line = new StoppingIterator(
                    new LineAtSideIterator(cell.comb, side),
                    hexagon -> !((Honeycomb)hexagon).stack().isEmpty());
            
            // the first neighbor must exist to jump over it
            if(!line.hasNext())
                continue;
            line.next();
            
            // looks for the end of the line
            while(line.hasNext())
                line.next();
            
            list.add(new Cell((Honeycomb)line.getStoppingValue()));
        }
        return list;
    }
}
