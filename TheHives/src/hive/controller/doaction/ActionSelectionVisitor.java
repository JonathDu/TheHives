/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller.doaction;

/**
 *
 * @author Thomas
 */
public interface ActionSelectionVisitor
{
    public void visit(PutActionSelection selection);
    public void visit(MoveActionSelection selection);
}
