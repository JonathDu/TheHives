/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.board;

import util.Vector2i;
import util.hexagons.HexagonSide;
import util.hexagons.NeighborsSymetricOffsetGetter;

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
public class NeighborsOffsetGetter extends NeighborsSymetricOffsetGetter
{
    @Override
    public Vector2i getOffsetABC(HexagonSide side)
    {
        assert isABC(side);
        switch(side)
        {
        case A: return new Vector2i(0, -1);
        case B: return new Vector2i(1, -1);
        case C: return new Vector2i(1, 0);
        }
        return null;
    }
    
}
