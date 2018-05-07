/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.board;

import util.Factory;

/**
 *
 * @author Thomas
 */
public class EmptyTilesStackFactory implements Factory<TilesStack>
{
    @Override
    public TilesStack create()
    {
        return new TilesStack();
    }
}
