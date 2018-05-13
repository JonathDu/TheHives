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
public class HexagonsGraph<H>
{
    H center;
    
    public HexagonsGraph()
    {
        center = null;
    }
    
    public HexagonsGraph(H center)
    {
        this.center = center;
    }
    
    public H getCenter()
    {
        return center;
    }
    
    void setCenter(H center)
    {
        this.center = center;
    }
    
    public boolean isEmpty()
    {
        return center == null;
    }
}
