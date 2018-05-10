/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.actions;

/**
 *
 * @author Thomas
 */
public class NoAction implements Action
{

    @Override
    public void accept(ActionVisitor visitor)
    {
        visitor.visit(this);
    }
    
}
