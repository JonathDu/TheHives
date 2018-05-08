/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.board;

import java.util.Stack;

/**
 *
 * @author Thomas
 */
public class TilesStack extends Stack<Tile>
{
    public TilesStack()
    {
        super();
    }
    
    public TilesStack(Tile tile)
    {
        this();
        push(tile);
    }
}
