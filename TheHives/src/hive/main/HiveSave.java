/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.main;

import hive.model.game.DefaultGame;
import hive.model.GameProgress;
import hive.model.HiveInterfaceIA;
import hive.model.board.Board;
import hive.model.board.TilesStack;
import hive.model.game.PrecalculatedGame;
import hive.model.game.Game;
import hive.model.game.LoaderXML;
import hive.model.game.rules.GameStatus;
import hive.model.game.rules.HiveUtil;
import hive.model.game.utildata.UtilData;
import hive.model.players.Player;
import hive.model.players.actions.Action;
import hive.model.players.decisions.HumanDecision;
import hive.model.players.decisions.IADecision;
import hive.model.players.decisions.Level;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;
import util.Matrix;

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
        LoaderXML<Matrix<TilesStack>> loader = new LoaderXML<>();
        
        Game game = PrecalculatedGame.get(PrecalculatedGame.Id.GAME_A, new IADecision(Level.EASY), new IADecision(Level.EASY));
        
        String file_name;
        System.out.print("File name : ");
        file_name = sc.next();
        
        System.out.println(game.state.board.getData());
        loader.loadInFile(game.state.board.getData(), file_name);
        Matrix<TilesStack> board = loader.loadFromFile(file_name);
        
        System.out.println(game.state.board);
        System.out.println(board);
    }
}
