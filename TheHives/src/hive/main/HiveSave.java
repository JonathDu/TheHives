/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.main;

import hive.model.game.PrecalculatedGame;
import hive.model.game.Game;
import hive.model.game.GameLoader;
import util.LoaderXML;
import hive.model.players.decisions.IADecision;
import hive.model.players.decisions.Level;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Thomas
 */
public class HiveSave
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, IOException
    {
        // choisir les décisions qu'il faut ICI
        // si il y a un humain, s'inspirer du shéma de HiveConsoleHuman dans le corps du while
        // (il faut setAction avant de doAction() quand c'est à un humain de jouer)
        Scanner sc = new Scanner(System.in);
        LoaderXML<Game> loader = new GameLoader();
        
        Game game = PrecalculatedGame.get(PrecalculatedGame.Id.GAME_A, new IADecision(Level.EASY), new IADecision(Level.EASY));
        
        String file_name;
        System.out.print("File name : ");
        file_name = sc.next();
        
        loader.loadInFile(game, file_name);
        Game res = loader.loadFromFile(file_name);
        
        System.out.println(game.hashCode());
        System.out.println(res.hashCode());
        System.out.println(game.state.board.hashCode());
        System.out.println(res.state.board.hashCode());
    }
}
