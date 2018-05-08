/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.board;

import util.Vector2i;
import util.hexagons.HexagonSide;
import util.hexagons.NeighborsSymetricShifter;

/**
 *     A
 *     _
 * F /   \ B
 * E \ _ / C
 *     D
 * 
 *   _       _
 * /   \ _ /   \ _  . . .
 * \ _ /   \ _ /    . . .
 * /   \ _ /   \ _  . . .
 * \ _ /   \ _ /    . . .
 * /   \ _ /   \    . . .
 *  . . . . . . . . . . .
 *  . . . . . . . . . . .
 *  . . . . . . . . . . .
 * 
 * @author Thomas
 */
public class HiveNeighborsShifter extends NeighborsSymetricShifter
{
    @Override
    public Vector2i shiftABC(Vector2i p, HexagonSide side)
    {
        assert isABC(side);
        switch(side)
        {
        case A: return p.add(new Vector2i(0, -1));
        case B: return p.add(p.x % 2 == 0 ? new Vector2i(1, -1) : new Vector2i(1, 0));
        case C: return p.add(p.x % 2 == 0 ? new Vector2i(1, 0) : new Vector2i(1, 1));
        }
        return null;
    }
    
}
