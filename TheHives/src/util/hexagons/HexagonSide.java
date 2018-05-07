/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.hexagons;

/**
 *
 * @author Thomas
 */
public enum HexagonSide
{
    A,
    B,
    C,
    D,
    E,
    F;
    
    public HexagonSide getOpposite()
    {
        switch(this)
        {
        case A: return D;
        case B: return E;
        case C: return F;
        case D: return A;
        case E: return B;
        case F: return C;
        }
        return null;
    }
    
    public HexagonSide getAfter()
    {
        switch(this)
        {
        case A: return B;
        case B: return C;
        case C: return D;
        case D: return E;
        case E: return F;
        case F: return A;
        }
        return null;
    }
    
    public HexagonSide getBefore()
    {
        switch(this)
        {
        case A: return F;
        case B: return A;
        case C: return B;
        case D: return C;
        case E: return D;
        case F: return E;
        }
        return null;
    }
}
