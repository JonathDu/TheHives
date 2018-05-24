/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions.cerveau;

import hive.model.HiveInterfaceIA;
import hive.model.board.Tile;
import hive.model.game.Game;
import hive.model.players.Player;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 *
 * @author Coralie
 */
public class EvaluationLearning {

    //ajout atribut
    ArrayList<Integer> evalValues;

    public EvaluationLearning(String fichier) {
        evalValues = new ArrayList<>();
        try {
            try (FileInputStream fis = new FileInputStream(new File(fichier));
                    ObjectInputStream ois = new ObjectInputStream(fis)) {
                evalValues = (ArrayList<Integer>) ois.readObject();
                System.out.println("Evaluatio fils : " + evalValues.toString());
            }
        } catch (IOException | ClassNotFoundException ioe) {
            System.out.println("Erreur à l'initialisation de evalValue");
        }

    }

    public EvaluationLearning(ArrayList<Integer> evalValues) {
        this.evalValues = evalValues;
    }

    public ArrayList<Integer> getEvalValues() {
        return evalValues;
    }

    int evaluation(Game state) {
        HiveInterfaceIA hia = new HiveInterfaceIA();
        Player current = hia.currentPlayer(state);
        Player opponent = hia.opponentPlayer(state);
        int value;
        if (hia.winOpponent(state)) {
            return -10000;
        } else if (hia.winCurrent(state)) {
            return 10000;
        } else {
            ArrayList<Tile> currentFreeTile = hia.freeTiles(state, current);
            value = insectsValue(currentFreeTile);
            ArrayList<Tile> opponentFreeTile = hia.freeTiles(state, opponent);
            value -= insectsValue(opponentFreeTile);
            ArrayList<Tile> currentBlocTile = hia.blockedTiles(current, state);
            value -= insectsValueBloc(currentBlocTile);
            ArrayList<Tile> opponentBlocTile = hia.blockedTiles(opponent, state);
            value += insectsValueBloc(opponentBlocTile);
            value += evalQueen(state);
            value += valueNeighboursQueen(state);
        }
        return value;
    }

    int evalQueen(Game state) {
        HiveInterfaceIA hia = new HiveInterfaceIA();
        int values=0;
        Player opponent = hia.opponentPlayer(state);
        Player current = hia.currentPlayer(state);
        int queenOpponentPossibilities = hia.nbPossibilitiesQueen(state, opponent);
        int queenCurrentPossibilities = hia.nbPossibilitiesQueen(state, current);
        switch (queenOpponentPossibilities) {
            case 0:
                values+= evalValues.get(0);
                break;
            case 1:
                values+= evalValues.get(1);
                break;
            default:
                values-= evalValues.get(2);
                break;
        }
        switch (queenCurrentPossibilities) {
            case 0:
                values-= evalValues.get(3);
                break;
            case 1:
                values-= evalValues.get(4);
                break;
            default:
                values+= evalValues.get(5);
                break;
        }
        
        
        return values;
    }

    int valueNeighboursQueen(Game state) {
        int value = 0;
        HiveInterfaceIA hia = new HiveInterfaceIA();
        Player opponent = hia.opponentPlayer(state);
        Player current = hia.currentPlayer(state);
        ArrayList<Tile> neighboursOpponent = hia.queenNeighbours(opponent, state);
        ArrayList<Tile> neighboursCurrent = hia.queenNeighbours(current, state);
        
        for(Tile tuile : neighboursOpponent){
            value += insectsValueNeighboursQueenOpponent(tuile, state);

        }
        for(Tile tuile : neighboursCurrent) {
            value -= insectsValueNeighboursQueenCurrent(tuile, state);

        }
        return value;
    }

    int insectsValue(ArrayList<Tile> freeTile) {
        int value = 0;
        for(Tile currentTile : freeTile)
            switch (currentTile.type) {
                case QUEEN_BEE:
                    value += evalValues.get(6);
                    break;
                case GRASSHOPPER:
                    value += evalValues.get(7);
                    break;
                case SOLDIER_ANT:
                    value += evalValues.get(8);
                    break;
                case SPIDER:
                    value += evalValues.get(9);
                    break;
                case BEETLE:
                    value += evalValues.get(10);
                    break;
            }

        
        return value;
    }

    int insectsValueBloc(ArrayList<Tile> blocTile) {
        int value = 0;
        for(Tile currentTile : blocTile)
            switch (currentTile.type) {
                case QUEEN_BEE:
                    value += evalValues.get(11);
                    break;
                case GRASSHOPPER:
                    value += evalValues.get(12);
                    break;
                case SOLDIER_ANT:
                    value += evalValues.get(13);
                    break;
                case SPIDER:
                    value += evalValues.get(14);
                    break;
                case BEETLE:
                    value += evalValues.get(15);
                    break;
            }
        return value;
    }

    int insectsValueNeighboursQueenCurrent(Tile tile, Game state) {
        HiveInterfaceIA hia = new HiveInterfaceIA();
        if (tile.color == hia.currentPlayer(state).color) {
            switch (tile.type) {
                case QUEEN_BEE:
                    return evalValues.get(16);
                default:
                    return evalValues.get(17);

            }
        }else{
            switch (tile.type) {
                case QUEEN_BEE:
                    return evalValues.get(18);
                case BEETLE:
                    return evalValues.get(19);
                default:
                    return evalValues.get(20);

            }
        }

    }
    int insectsValueNeighboursQueenOpponent(Tile tile, Game state){
            HiveInterfaceIA hia = new HiveInterfaceIA();
            if (tile.color == hia.currentPlayer(state).color) {
                switch (tile.type) {
                    case QUEEN_BEE:
                        return evalValues.get(21);
                    case GRASSHOPPER:
                        return evalValues.get(22);
                    case SOLDIER_ANT:
                        return evalValues.get(23);
                    case SPIDER:
                        return evalValues.get(24);
                    case BEETLE:
                        return evalValues.get(25);
                    default:
                        return evalValues.get(0);

                }
            }else{
                return evalValues.get(26);
                    
            }

        }
    }
