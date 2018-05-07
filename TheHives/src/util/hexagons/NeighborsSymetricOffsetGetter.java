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
public abstract class NeighborsSymetricOffsetGetter extends NeighborsOffsetGetter
{
    @Override
    public Vector2i getOffset(HexagonSide side)
    {
        return isABC(side) ? getOffsetABC(side) : getOffsetDEF(side);
    }
    
    abstract public Vector2i getOffsetABC(HexagonSide side);
    
    public Vector2i getOffsetDEF(HexagonSide side)
    {
        assert !isABC(side);
        return getOffsetABC(side).opposite();
    }
    
    
    protected boolean isABC(HexagonSide side)
    {
        return side == HexagonSide.A || side == HexagonSide.B || side == HexagonSide.C;
    }
}
