/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.main;

import hive.model.game.DefaultGame;
import hive.model.GameProgress;
import hive.model.HiveInterfaceIA;
import hive.model.InterfaceIA;
import hive.model.board.Cell;
import hive.model.board.Tile;
import hive.model.game.PrecalculatedGame;
import hive.model.game.Game;
import hive.model.game.rules.GameStatus;
import hive.model.game.rules.HiveUtil;
import hive.model.game.utildata.PositionsPerInsectPerTeam;
import hive.model.insects.InsectType;
import hive.model.players.Player;
import hive.model.players.TeamColor;
import hive.model.players.decisions.IADecision;

import java.util.ArrayList;
import hive.model.players.decisions.IA.Level;
import java.util.Scanner;

/**
 *
 * @author Thomas
 */
public class HiveConsoleIA {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws InterruptedException
    {
        // choisir les décisions qu'il faut ICI
        // si il y a un humain, s'inspirer du shéma de HiveConsoleHuman dans le corps du while
        // (il faut setAction avant de doAction() quand c'est à un humain de jouer)
        Game game = PrecalculatedGame.get(PrecalculatedGame.Id.DEFAULT, new IADecision(Level.HARD), new IADecision(Level.HARD));

        GameProgress progress = new GameProgress(game);

        //Thread.sleep(10000); // 10s

        ArrayList<Tile> free = new ArrayList<>(25);
        ArrayList<Tile> blocked = new ArrayList<>(25);
        ArrayList<Tile> free_queen = new ArrayList<>(25);
        ArrayList<Tile> blocked_queen = new ArrayList<>(25);
        
        InterfaceIA ia = new HiveInterfaceIA();
        
        GameStatus status;
        while ((status = game.rules.getStatus(game.state)) == GameStatus.CONTINUES)
        {
            progress.doAction();
            
            System.out.println(game.state.board);
        }

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
