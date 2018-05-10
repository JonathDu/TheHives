/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.board;

import hive.model.players.TeamColor;
import java.util.ArrayList;

/**
 *
 * @author Thomas
 */
public class Cells
{
    public static boolean isSurrounded(Cell cell)
    {
        return true;
    }
    
    public static boolean isCrushed(Cell cell)
    {
        return cell.index < cell.hexagon.getValue().size() - 1;
    }
    
    // groupes de voisins (size=0 vrai) (size=1 et nb <= 4 vrai) (size=2 et nb 2 et 2 faux sinon vrai) (size=3 faux)
    public static boolean isFree(Cell cell)
    {
        return true;
    }
    
    // groupes de voisins (size=0 vrai) (size=1 vrai)
    // (size=2 count getvalue = pile vide -> parcours largeur sur pile non vide == nbTiles - 1 vrai sinon faux + remettre la pile
    public static boolean withoutIsConnex(Cell cell, int nbTiles)
    {
        return true;
    }
    
    public static TeamColor cellColor(Cell cell)
    {
        return cell.hexagon.getValue().peek().color;
    }
    
    // count -> filtre -> voisins qui ont une couleur différente    == 0
    public static boolean neighborsHaveSameColor(Cell cell)
    {
        return true;
    }
    
    public static ArrayList<ArrayList<Cell>> getNeighborsGroups(Cell cell)
    {
        return null;
    }
}
