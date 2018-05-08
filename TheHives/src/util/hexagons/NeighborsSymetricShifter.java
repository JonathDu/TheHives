/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.hexagons;

import util.Vector2i;

/**
 *
 * @author Thomas
 */
public abstract class NeighborsSymetricShifter extends NeighborsShifter
{
    @Override
    public Vector2i shift(Vector2i p, HexagonSide side)
    {
        return isABC(side) ? shiftABC(p, side) : shiftDEF(p, side);
    }
    
    abstract public Vector2i shiftABC(Vector2i p, HexagonSide side);
    
    public Vector2i shiftDEF(Vector2i p, HexagonSide side)
    {
        assert !isABC(side);
        return shiftABC(p, side.getOpposite());
    }
    
    
    protected boolean isABC(HexagonSide side)
    {
        return side == HexagonSide.A || side == HexagonSide.B || side == HexagonSide.C;
    }
}
