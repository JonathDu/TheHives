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
import hive.model.game.PrecalculatedGame;
import hive.model.game.Game;
import hive.model.game.GameLoader;
import util.LoaderXML;
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

/**
 *
 * @author Thomas
 */
public class HiveSaveGame
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
        GameLoader loader = new GameLoader();
        
        String choice;
        do
        {
            System.out.print("START or LOAD : ");
            choice = sc.next();
        } while (!choice.equals("START") && !choice.equals("LOAD"));
        
        Game game = null;
        String file_name;
        if(choice.equals("START"))
            game = DefaultGame.get(new IADecision(Level.EASY), new IADecision(Level.EASY));
        else
        {
            System.out.print("File name : ");
            file_name = sc.next();
            try
            {
                game = loader.loadFromFile(file_name);
            }
            catch (FileNotFoundException ex)
            {
                System.out.println("Erreur à l'ouverture du fichier " + file_name);
                System.exit(1);
            }
        }
                
        GameProgress progress = new GameProgress(game);
        
        System.out.println(game.state.board);
        
        int n;
        System.out.print("n : ");
        n = sc.nextInt();
        
        do
        {
            int k = 0;
            GameStatus status;
            while ((status = game.rules.getStatus(game.state)) == GameStatus.CONTINUES && k < n)
                progress.doAction();

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
            case CONTINUES:
                System.out.println("La partie continue");
                break;
            }
            
            System.out.print("File name : ");
            file_name = sc.next();
            try
            {
                loader.loadInFile(game, file_name);
            }
            catch (Exception ex)
            {
                System.out.println("Erreur à l'ouverture du fichier " + file_name);
                System.exit(1);
            }
            
            do
            {
                System.out.print("CONTINUE or QUIT : ");
                choice = sc.next();
            } while (!choice.equals("CONTINUE") && !choice.equals("QUIT"));
            
        } while(choice.equals("CONTINUE"));
    }
}
