/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.hexagons.iterators;

import util.hexagons.Hexagon;
import util.hexagons.HexagonSide;

/**
 *
 * @author Thomas
 * @param <E>
 */
public class Neighbor<E>
{
    public Hexagon<E> hexagon;
    public HexagonSide from;
    
    public Neighbor(Hexagon<E> hexagon, HexagonSide from)
    {
        this.hexagon = hexagon;
        this.from = from;
    }
}
