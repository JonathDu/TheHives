/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.board;

import util.Vector2i;
import util.hexagons.HexagonSide;
import util.hexagons.NeighborsShifter;

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
public class HiveNeighborsShifter extends NeighborsShifter
{
    @Override
    public Vector2i shift(Vector2i p, HexagonSide side)
    {
        switch(side)
        {
        case A: return p.add(new Vector2i(0, -1));
        case B: return p.add(new Vector2i(1, p.x % 2 == 0 ? -1 : 0));
        case C: return p.add(new Vector2i(1, p.x % 2 == 0 ? 0 : 1));
        case D: return p.add(new Vector2i(0, 1));
        case E: return p.add(new Vector2i(-1, p.x % 2 == 0 ? 0 : 1));
        case F: return p.add(new Vector2i(-1, p.x % 2 == 0 ? -1 : 0));
        }
        return null;
    }

    @Override
    public int hashCode()
    {
        int hash = 17;
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
