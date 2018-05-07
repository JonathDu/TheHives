/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todelete;

import p.util.Position;
import util.Vector2;

/**
 * A définir
 * @author Thomas
 */
public class OperationCarre
{
    static public Position carreToPosition(Vector2<Double> click, Vector2<Integer> dim)
    {
        return new Position(dim.x / click.x.intValue(), dim.y / click.y.intValue());
    }
}
