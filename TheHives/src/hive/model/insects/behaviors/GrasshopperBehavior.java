/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.insects.behaviors;

import hive.model.board.Cell;
import hive.model.game.rules.HiveFunctions;
import hive.model.board.Honeycomb;
import hive.model.board.TilesStack;
import hive.model.game.GameState;
import hive.model.insects.InsectBehavior;
import java.util.ArrayList;
import util.hexagons.Hexagon;
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
    public ArrayList<Cell> getPossibleDestinations(GameState state, Cell cell)
    {
        assert cell.level == 0;
        
        ArrayList<Cell> list = new ArrayList<>();
        
        if(HiveFunctions.isCrushed(cell) || !HiveFunctions.isConnexWithout(cell, state.data.nb_combs))
            return list;
        
        // for each side
        for(HexagonSide side : HexagonSide.values())
        {
            // start in line
            StoppingIterator<Hexagon<TilesStack>> line = new StoppingIterator<>(
                    new LineAtSideIterator<>(cell.comb, side),
                    hexagon -> !hexagon.value().isEmpty());
            
            // the first neighbor must exist to jump over it
            if(!line.hasNext())
                continue;
            line.next();
            
            // looks for the end of the line
            while(line.hasNext())
            {
                line.next();
            }
            
            list.add(new Cell((Honeycomb)line.getStoppingValue()));
        }
        return list;
    }

    @Override
    public boolean isFree(GameState state, Cell cell)
    {
        assert cell.level == 0;
        
        if(HiveFunctions.isCrushed(cell) || !HiveFunctions.isConnexWithout(cell, state.data.nb_combs))
            return false;
        
        // for each side
        for(HexagonSide side : HexagonSide.values())
        {
            // start in line
            StoppingIterator<Hexagon<TilesStack>> line = new StoppingIterator<>(
                    new LineAtSideIterator<>(cell.comb, side),
                    hexagon -> !hexagon.value().isEmpty());
            
            // the first neighbor must exist to jump over it
            if(!line.hasNext())
                continue;
            line.next();
            
            // looks for the end of the line
            while(line.hasNext())
            {
                line.next();
            }
            
            return true;
        }
        return false;
    }
}
