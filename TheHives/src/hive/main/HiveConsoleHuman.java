/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.main;

import hive.model.game.DefaultGame;
import hive.model.GameProgress;
import hive.model.HiveInterfaceIA;
import hive.model.board.Tile;
import hive.model.board.Cell;
import hive.model.game.Game;
import hive.model.game.rules.GameStatus;
import hive.model.game.rules.HiveFunctions;
import hive.model.game.rules.HiveRules;
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
public class HiveConsoleHuman
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Game game = DefaultGame.get(new HumanDecision(), new HumanDecision());
        GameProgress progress = new GameProgress(game);

        Scanner sc = new Scanner(System.in);
        
        HiveInterfaceIA hi = new HiveInterfaceIA();
        
        while (game.rules.getStatus(game.state) == GameStatus.CONTINUES)
        {
            Player player = game.state.turn.getCurrent();
            if (player == game.state.players.get(0))
                System.out.println("Joueur 1");
            else
                System.out.println("Joueur 2");
            
            System.out.println("Tour " + HiveFunctions.nbTurns(game.state));

            assert player.decision instanceof HumanDecision;
            HumanDecision decision = (HumanDecision) player.decision;
            
            for(Action a : hi.currentPlayerPossibilities(game))
                System.out.println(a);

            String actionChoose;
            do
            {
                System.out.println("Choix de l'action (PUT ou MOVE) (ou UNDO) :");
                actionChoose = sc.next();
            } while (!actionChoose.equals("PUT") && !actionChoose.equals("MOVE") && !actionChoose.equals("UNDO"));
            
            if(actionChoose.equals("UNDO"))
            {
                progress.undoAction();
            }
            else
            {
                Action a = (actionChoose.equals("PUT") ? put(game) : move(game));
                decision.setAction(a);
                progress.doAction();
            }
            System.out.println(game.state.board);
        }
    }

    private static Action put(Game game)
    {
        Scanner sc = new Scanner(System.in);

        InsectType insectType = null;
        do
        {
            if(insectType != null)
            {
                System.out.println("Insecte non disponible dans la pioche. Veuillez en saisir un autre.");
            }
            do
            {
                System.out.println("Type d'insecte (Q, S, B, G, A, M, L, P) :");
                insectType = InsectType.toInsectType(sc.next());
            } while (insectType == null);
        } while (game.state.turn.getCurrent().collection.get(insectType) <= 0);
        
        ArrayList<Cell> listPossibleDestinations = ((HiveRules)game.rules).getPossiblePlacements(game.state, insectType);
        System.out.println("Positions d'arrive possibles :");
        System.out.println(listPossibleDestinations);
        
        System.out.println("Position de l'insecte : ");
        Vector2i pos = new Vector2i(sc.nextInt(), sc.nextInt());

        Tile tile = new Tile(insectType, game.state.turn.getCurrent().color);
        Cell c = new Cell(game.state.board.getHexagon(pos));
        
        return new PutAction(c, tile);
    }

    private static Action move(Game game)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Position depart : ");
        Vector2i startPos = new Vector2i(sc.nextInt(), sc.nextInt());
        Cell start = new Cell(game.state.board.getHexagon(startPos), game.state.board.getHexagon(startPos).value().size()-1);
        ArrayList<Cell> listPossibleDestinations = game.rules.getPossibleDestinations(game.state, start);
        System.out.println("Positions d'arrive possibles :");
        
        System.out.println(listPossibleDestinations);
        
        System.out.println("");
        
        System.out.println("Position d'arrive : ");
        Vector2i endPos = new Vector2i(sc.nextInt(), sc.nextInt());
        Cell end = new Cell(game.state.board.getHexagon(endPos));
        
        return new MoveAction(start, end);
    }

}
