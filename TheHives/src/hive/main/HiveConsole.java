/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.main;

import hive.model.game.DefaultGame;
import hive.model.GameProgress;
import hive.model.board.Tile;
import hive.model.board.TilePosition;
import hive.model.game.Game;
import hive.model.insects.InsectType;
import hive.model.players.Player;
import hive.model.players.actions.PutAction;
import hive.model.players.decisions.HumanDecision;
import java.util.Scanner;
import util.Vector2i;

/**
 *
 * @author Thomas
 */
public class HiveConsole
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Game game = DefaultGame.get(new HumanDecision(), new HumanDecision());
        GameProgress progress = new GameProgress(game);
        
        Scanner sc = new Scanner(System.in);
        int i = 0;
        while(i++ < 5)
        {
            Player player = game.state.turn.getCurrent();
            if(player == game.state.players.get(0))
                System.out.println("Joueur 1");
            else
                System.out.println("Joueur 2");

            assert player.decision instanceof HumanDecision;
            HumanDecision decision = (HumanDecision) player.decision;
            
            System.out.println("Position : ");
            Vector2i pos = new Vector2i(sc.nextInt(), sc.nextInt());
            
            decision.setAction(new PutAction(new TilePosition(game.state.board.getHexagon(pos), 0), new Tile(InsectType.BEETLE, player.color)));
            progress.doAction();
        
            System.out.println(game.state.board);
        }
    }
    
}
