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
import hive.model.game.rules.HiveUtil;
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
            default:
                break;
        }
    }
    
    public static void main(String[] args) {
        // choisir les décisions qu'il faut ICI
        // si il y a un humain, s'inspirer du shéma de HiveConsoleHuman dans le corps du while
        // (il faut setAction avant de doAction() quand c'est à un humain de jouer)
        int i = 20;
        Game game;
        while (i < 60) {
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
            } else {
                game = DefaultGame.get(new IADecision(Level.EASY)/*white*/, new IADecision(Level.MEDIUM)/*black*/);
            }
            printWhich(i);
            GameProgress progress = new GameProgress(game);
            Player player = null;
            while (game.rules.getStatus(game.state) == GameStatus.CONTINUES) {
                player = game.state.turn.getCurrent();

                progress.doAction();

                // mettre un sleep ici ?
            }
            System.out.println("Turn : " + HiveUtil.nbTurns(game.state));
            if (game.rules.getStatus(game.state) != GameStatus.CURRENT_WINS) {
                if (player == game.state.players.get(0)) {
                    System.out.println("Joueur 1 a gagné");
                } else {
                    System.out.println("Joueur 2 a gagné");
                }
            } else {
                if (player == game.state.players.get(0)) {
                    System.out.println("Joueur 1 s'est fait perdre");
                } else {
                    System.out.println("Joueur 2 s'est fait perdre");
                }
            }

            //System.out.println(game.state.board);
            i++;
        }
    }
}
