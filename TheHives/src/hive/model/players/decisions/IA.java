/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions;

import hive.model.game.Game;
import hive.model.players.actions.Action;

/**
 *
 * @author Coralie
 */
public interface IA {
    
    public Action SearchAction(Game state);
}
