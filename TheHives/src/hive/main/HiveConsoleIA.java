/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.main;

import hive.model.game.DefaultGame;
import hive.model.GameProgress;
import hive.model.board.Tile;
import hive.model.board.Cell;
import hive.model.game.Game;
import hive.model.game.rules.GameStatus;
import hive.model.insects.InsectBehavior;
import hive.model.insects.InsectType;
import hive.model.players.Player;
import hive.model.players.TeamColor;
import hive.model.players.actions.Action;
import hive.model.players.actions.MoveAction;
import hive.model.players.actions.PutAction;
import hive.model.players.decisions.HumanDecision;
import java.util.ArrayList;
import java.util.Scanner;
import util.Vector2i;

/**
 *
 * @author Thomas
 */
public class HiveConsoleIA
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // choisir les décisions qu'il faut ICI
        // si il y a un humain, s'inspirer du shéma de HiveConsoleHuman dans le corps du while
        // (il faut setAction avant de doAction() quand c'est à un humain de jouer)
        Game game = DefaultGame.get(new HumanDecision(), new HumanDecision());
        
        
        GameProgress progress = new GameProgress(game);

        Scanner sc = new Scanner(System.in);
        int i = 0;
        while (game.rules.getStatus(game.state) == GameStatus.CONTINUES)
        {
            Player player = game.state.turn.getCurrent();
            if (player == game.state.players.get(0))
                System.out.println("Joueur 1");
            else
                System.out.println("Joueur 2");
            
            progress.doAction();
            
            System.out.println(game.state.board);
            
            // mettre un sleep ici ?
        }
    }
}
