/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.game.rules;

import hive.model.board.Board;
import hive.model.board.Cell;
import hive.model.board.Cells;
import hive.model.board.Honeycomb;
import hive.model.game.Game;
import hive.model.players.TeamColor;
import java.util.ArrayList;
import util.Vector2i;
import util.hexagons.iterators.NeighborsIterator;

/**
 *
 * @author Thomas
 */
public class HivePutRules implements PutRules
{

    @Override
    public ArrayList<Cell> getPossibleDestinations(Game game)
    {
        ArrayList<Cell> list = new ArrayList<>();
        Board board = game.state.board;
        
        if(game.state.data.nb_tiles == 0)
        {
            // return center
            list.add(new Cell(game.state.board.getCenter()));
            return list;
        }
        else if(game.state.data.nb_tiles == 1)
        {
            // return center neighbors
            NeighborsIterator neighbors = new NeighborsIterator(game.state.board.getCenter());
            while(neighbors.hasNext())
                list.add(new Cell((Honeycomb)neighbors.next().hexagon));
            return list;
        }
        
        // naive version
        for(int y = 0; y < board.getData().sizeY(); ++y)
        {
            for(int x = 0; x < board.getData().sizeX(); ++x)
            {
                Honeycomb comb = board.getHexagon(new Vector2i(x, y));
                if(comb.stack().isEmpty() && Cells.hasNeighbors(comb) && Cells.neighborsHaveSameColor(comb, game.state.turn.getCurrent().color))
                    list.add(new Cell(comb));
            }
        }
        return list;
    }

    @Override
    public int getMaxQueenTurn(Game game)
    {
        return 4;
    }
    
}
