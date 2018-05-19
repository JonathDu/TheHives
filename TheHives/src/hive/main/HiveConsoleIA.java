/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.main;

import hive.model.GameProgress;
import hive.model.game.PrecalculatedGame;
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
     * @throws java.lang.InterruptedException
     */

    public static void main(String[] args) throws InterruptedException
    {
        // choisir les décisions qu'il faut ICI
        // si il y a un humain, s'inspirer du shéma de HiveConsoleHuman dans le corps du while
        // (il faut setAction avant de doAction() quand c'est à un humain de jouer)
        Game game = PrecalculatedGame.get(PrecalculatedGame.Id.DEFAULT, new IADecision(Level.EHARD), new IADecision(Level.HARD));

        GameProgress progress = new GameProgress(game);

        //System.out.println(game.state.board);

        //Thread.sleep(10000); // 10s

        GameStatus status;
        while ((status = game.rules.getStatus(game.state)) == GameStatus.CONTINUES)
        {
            Player player = game.state.turn.getCurrent();
            System.out.println("Turn : " + HiveFunctions.nbTurns(game.state));
            if (player == game.state.players.get(0))
                System.out.println("Joueur 1");
            else
                System.out.println("Joueur 2");

            progress.doAction();
            System.out.println(game.state.board);
            
        }

        System.out.println(game.state.board);
        System.out.println("Tour " + HiveFunctions.nbTurns(game.state));

        switch(status)
        {
        case DRAW:
            System.out.println("Match nul");
            break;
        case CURRENT_WINS:
            System.out.println(game.state.turn.getCurrent().color + " gagne !");
            break;
        case OPPONENT_WINS:
            System.out.println(game.state.turn.getOpponent().color + " gagne !");
            break;
        }


    }
}
