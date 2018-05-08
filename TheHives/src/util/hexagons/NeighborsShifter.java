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
public abstract class NeighborsShifter
{
    abstract public Vector2i shift(Vector2i p, HexagonSide side);
}
