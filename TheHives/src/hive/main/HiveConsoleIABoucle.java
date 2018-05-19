/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.main;

import hive.model.GameProgress;
import hive.model.game.DefaultGame;
import hive.model.game.Game;
import hive.model.game.rules.GameStatus;
import hive.model.game.rules.HiveFunctions;
import hive.model.players.Player;
import hive.model.players.decisions.IADecision;
import hive.model.players.decisions.Level;
import java.util.Scanner;

/**
 *
 * @author Coralie
 */
public class HiveConsoleIABoucle {

    /**
     * @param args the command line arguments
     */
    
    
    static void printWhich(int i){
        switch (i) {
            case 0:
                System.out.println("Hard vs Medium");
                break;
            case 10:
                System.out.println("Medium vs Hard");
                break;
            case 20:
                System.out.println("Easy vs Hard");
                break;
            case 30:
                System.out.println("Hard vs Easy");
                break;
            case 40:
                System.out.println("Medium vs Easy");
                break;
            case 50:
                System.out.println("Easy vs Medium");
                break;
            case 60:
                System.out.println("Hard vs EHARD");
                break;
            default:
                break;
        }
    }
    
    public static void main(String[] args) {
        // choisir les décisions qu'il faut ICI
        // si il y a un humain, s'inspirer du shéma de HiveConsoleHuman dans le corps du while
        // (il faut setAction avant de doAction() quand c'est à un humain de jouer)
        int i = 60;
        Game game;
        while (i < 70) {
            if (i < 10) {
                game = DefaultGame.get(new IADecision(Level.HARD)/*white*/, new IADecision(Level.MEDIUM)/*black*/);
            } else if (i < 20) {
                game = DefaultGame.get(new IADecision(Level.MEDIUM)/*white*/, new IADecision(Level.HARD)/*black*/);
            } else if (i < 30) {
                game = DefaultGame.get(new IADecision(Level.EASY)/*white*/, new IADecision(Level.HARD)/*black*/);
            } else if (i < 40) {
                game = DefaultGame.get(new IADecision(Level.HARD)/*white*/, new IADecision(Level.EASY)/*black*/);
            } else if (i < 50) {
                game = DefaultGame.get(new IADecision(Level.MEDIUM)/*white*/, new IADecision(Level.EASY)/*black*/);
            } else if (i < 60){
                game = DefaultGame.get(new IADecision(Level.EASY)/*white*/, new IADecision(Level.MEDIUM)/*black*/);
            }else {
                game = DefaultGame.get(new IADecision(Level.HARD)/*white*/, new IADecision(Level.EHARD)/*black*/);
            }
            
            printWhich(i);
            GameProgress progress = new GameProgress(game);
            Player player = null;
            while (game.rules.getStatus(game.state) == GameStatus.CONTINUES && HiveFunctions.nbTurns(game.state)<150) {
                player = game.state.turn.getCurrent();

                progress.doAction();
                if(HiveFunctions.nbTurns(game.state) % 20 == 0)
                    System.out.println("Turn : " + HiveFunctions.nbTurns(game.state));

                // mettre un sleep ici ?
            }
            System.out.println("Turn : " + HiveFunctions.nbTurns(game.state));
            switch(game.rules.getStatus(game.state))
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
            default:
                System.out.println("Personne n'a gagné");
            }
            

            //System.out.println(game.state.board);
            i++;
        }
    }
}
