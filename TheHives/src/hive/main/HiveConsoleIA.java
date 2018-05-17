/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.main;

import hive.model.game.DefaultGame;
import hive.model.GameProgress;
import hive.model.game.Game;
import hive.model.game.rules.GameStatus;
import hive.model.game.rules.HiveFunctions;
import hive.model.players.Player;
import hive.model.players.decisions.IADecision;
import hive.model.players.decisions.Level;

/**
 *
 * @author Thomas
 */
public class HiveConsoleIA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // choisir les décisions qu'il faut ICI
        // si il y a un humain, s'inspirer du shéma de HiveConsoleHuman dans le corps du while
        // (il faut setAction avant de doAction() quand c'est à un humain de jouer)
        Game game = DefaultGame.get(new IADecision(Level.HARD)/*white*/, new IADecision(Level.MEDIUM)/*black*/);

        GameProgress progress = new GameProgress(game);

        Player player = null;
        while (game.rules.getStatus(game.state) == GameStatus.CONTINUES) {
            player = game.state.turn.getCurrent();

            progress.doAction();

            
        }
        System.out.println("Turn : " + HiveFunctions.nbTurns(game.state));
        if (player == game.state.players.get(0)) {
            System.out.println("Joueur 1");
        } else {
            System.out.println("Joueur 2");
        }
        System.out.println(game.state.board);

    }
}
