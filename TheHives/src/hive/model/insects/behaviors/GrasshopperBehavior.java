/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.insects.behaviors;

import hive.model.insects.behaviors.info.CombData;
import hive.model.board.Cell;
import hive.model.game.rules.HiveUtil;
import hive.model.board.Honeycomb;
import hive.model.board.TilesStack;
import hive.model.game.GameState;
import hive.model.insects.InsectBehavior;
import hive.model.insects.behaviors.info.CombInfo;
import hive.model.insects.behaviors.info.Info;
import java.util.function.Consumer;
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
    public void consumeDestinations(GameState state, Cell cell, Consumer<Cell> consumer)
    {
        assert cell.level == 0;
        
        if(HiveUtil.isCrushed(cell) || !HiveUtil.isConnexWithout(state, cell))
            return;
        
        consumeDestinations(cell, consumer);
    }
    
    public void consumeDestinations(Cell cell, Consumer<Cell> consumer)
    {
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
                line.next();
            
            consumer.accept(new Cell((Honeycomb)line.getStoppingValue()));
        }
    }
    
    // consumes on valid, invalid, and invalid_combs
    @Override
    public Info consumeDestinations(GameState state, Cell cell, Consumer<Cell> consumer, Consumer<CombData> info_giver)
    {
        assert cell.level == 0;
        
        if(HiveUtil.isCrushed(cell))
        {
            Info info = Info.IS_CRUSHED;
            if(!HiveUtil.isConnexWithout(state, cell))
                info.add(Info.IS_NOT_CONNEX_WITHOUT);
            return info;
        }
        
        // for each side
        for(HexagonSide side : HexagonSide.values())
        {
            CombInfo info = CombInfo.NONE;
            
            // start in line
            StoppingIterator<Hexagon<TilesStack>> line = new StoppingIterator<>(
                    new LineAtSideIterator<>(cell.comb, side),
                    hexagon -> !hexagon.value().isEmpty());
            
            // the first neighbor must exist to jump over it
            if(!line.hasNext())
            {
                info_giver.accept(new CombData((Honeycomb)cell.comb.getNeighbor(side), CombInfo.NEED_SMTHG_TO_JUMP));
                continue;
            }
            line.next();
            
            // looks for the end of the line
            while(line.hasNext())
                line.next();
            
            Honeycomb res = (Honeycomb)line.getStoppingValue();
            consumer.accept(new Cell(res));
            info_giver.accept(new CombData((Honeycomb)cell.comb.getNeighbor(side), CombInfo.CAN_JUMP));
        }
        return Info.OK;
    }

    @Override
    public boolean isFree(GameState state, Cell cell)
    {
        assert cell.level == 0;
        
        if(HiveUtil.isCrushed(cell) || !HiveUtil.isConnexWithout(state, cell))
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
                line.next();
            
            return true;
        }
        return false;
    }
    
    @Override
    public int hashCode()
    {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        return true;
    }
}
