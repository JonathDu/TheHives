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
import hive.model.players.actions.Action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            }
        } catch (IOException | ClassNotFoundException ioe) {
            System.out.println("Erreur à l'initialisation de evalValue");
        }

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
        Player opponent = hia.opponentPlayer(state);
        Player current = hia.currentPlayer(state);
        int afterCurrentNeighbour = hia.queenFreeNeighbour(current, state);
        int afterOpponentNeighbour = hia.queenFreeNeighbour(opponent, state);
        Action hello = hia.undoAction(state);
        opponent = hia.currentPlayer(state);
        current = hia.opponentPlayer(state);
        int beforeCurrentNeighbour = hia.queenFreeNeighbour(current, state);
        int beforeOpponentNeighbour = hia.queenFreeNeighbour(opponent, state);
        hia.doAction(state, hello);
        return ((afterCurrentNeighbour - beforeCurrentNeighbour) - (afterOpponentNeighbour - beforeOpponentNeighbour)) * (-30);
    }

    int valueNeighboursQueen(Game state) {
        int value = 0;
        HiveInterfaceIA hia = new HiveInterfaceIA();
        Player opponent = hia.opponentPlayer(state);
        ArrayList<Tile> neighbours = hia.queenNeighbours(opponent, state);
        Tile tuile;
        while (!neighbours.isEmpty()) {
            tuile = neighbours.remove(0);
            if (tuile.color != opponent.color) {
                value += insectsValueNeighboursQueen(tuile);
            }
        }
        return value;
    }

    int insectsValue(ArrayList<Tile> freeTile) {
        Tile currentTile;
        int value = 0;
        while (!freeTile.isEmpty()) {
            currentTile = freeTile.remove(0);
            switch (currentTile.type) {
                case QUEEN_BEE:
                    value += evalValues.get(0);
                    break;
                case GRASSHOPPER:
                    value += evalValues.get(1);
                    break;
                case SOLDIER_ANT:
                    value += evalValues.get(2);
                    break;
                case SPIDER:
                    value += evalValues.get(3);
                    break;
                case BEETLE:
                    value += evalValues.get(4);
                    break;
            }

        }
        return value;
    }

    int insectsValueBloc(ArrayList<Tile> blocTile) {
        Tile currentTile;
        int value = 0;
        while (!blocTile.isEmpty()) {
            currentTile = blocTile.remove(0);
            switch (currentTile.type) {
                case QUEEN_BEE:
                    value += evalValues.get(5);
                    break;
                case GRASSHOPPER:
                    value += evalValues.get(6);
                    break;
                case SOLDIER_ANT:
                    value += evalValues.get(7);
                    break;
                case SPIDER:
                    value += evalValues.get(8);
                    break;
                case BEETLE:
                    value += evalValues.get(9);
                    break;
            }

        }
        return value;
    }

    int insectsValueNeighboursQueen(Tile freeTile) {
        switch (freeTile.type) {
            case QUEEN_BEE:
                return evalValues.get(10);
            case GRASSHOPPER:
                return evalValues.get(11);
            case SOLDIER_ANT:
                return evalValues.get(12);
            case SPIDER:
                return evalValues.get(13);
            case BEETLE:
                return evalValues.get(14);
            default:
                return evalValues.get(0);

        }

    }
}
