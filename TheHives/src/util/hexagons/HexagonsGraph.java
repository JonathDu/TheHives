/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.hexagons;

/**
 *
 * @author Thomas
 * @param <E>
 */
public class HexagonsGraph<E>
{
    Hexagon<E> center;
    
    public HexagonsGraph()
    {
        center = null;
    }
    
    public HexagonsGraph(Hexagon<E> center)
    {
        this.center = center;
    }
    
    Hexagon<E> getCenter()
    {
        return center;
    }
    
    void setCenter(Hexagon<E> center)
    {
        this.center = center;
    }
    
    boolean isEmpty()
    {
        return center == null;
    }
}
