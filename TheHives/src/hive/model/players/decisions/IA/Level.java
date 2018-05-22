/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions.IA;

/**
 *
 * @author Coralie
 */
public enum Level {
    EASY(0), MEDIUM(1), HARD(2), EHARD(3);
    int level;

    private Level(int level) {
        this.level = level;
    }
}
