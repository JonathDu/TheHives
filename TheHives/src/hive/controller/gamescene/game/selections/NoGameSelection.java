/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.gamescene.game.selections;

import hive.controller.gamescene.game.selectors.GameSelector;

/**
 *
 * @author Thomas
 */
public class NoGameSelection implements GameSelection
{

    @Override
    public void accept(GameSelector visitor)
    {
        visitor.visit(this);
    }
    
}
