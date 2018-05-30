/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions.IA;

import hive.model.insects.InsectType;
import java.util.ArrayList;

/**
 *
 * @author Coralie
 */

/*
    QUEEN -> 0
    SPIDER -> 1
    BEETLE -> 2
    GRASSHOPPER -> 3
    SOLDIER_ANT -> 4
*/

public class Heuristic {
    public static final int[] NB_QUEEN_OP_POSSIBILITY = {550, 400, 366, 220, 0,-50};
    public static final int[] QUEEN_NEIGH_BLOCK_OP_OP = {16, 145, 144, 200, 140};
    public static final int[] QUEEN_NEIGH_FREE_OP_OP = {75, 75, 75, 75, 75};
    public static final int[] QUEEN_NEIGH_BLOCK_OP_CUR = {6, 335, 334, 390, 310};
    public static final int[] QUEEN_NEIGH_FREE_OP_CUR = {26, 555, 554, 500, 520};
    public static final int[] INSECT_VALUE_OP = {-50, -56, -50, -141, -132};
    public static final int[] INSECT_BLOCK_VALUE_OP = {150, 180, 200, 185, 190};
    public static final int[] IN_HAND_OP = {-20 , -20, -20, -20, -20 };
    
    
    public static final int[] NB_QUEEN_CUR_POSSIBILITY = {-400, -100, 200, 250, 300, 250};
    public static final int[] QUEEN_NEIGH_BLOCK_CUR_CUR = {-136, -180, -200, -180, -180};
    public static final int[] QUEEN_NEIGH_FREE_CUR_CUR = {-150, -180, -200, -180, -180};
    public static final int[] QUEEN_NEIGH_BLOCK_CUR_OP = {-156, -80, -100, -80, -80};
    public static final int[] QUEEN_NEIGH_FREE_CUR_OP = {-16, -180, -200, -180, -180};
    public static final int[] INSECT_VALUE_CUR = {50, 56, 50, 171, 132};
    public static final int[] INSECT_BLOCK_VALUE_CUR = {-225, -70, -70, -80, -70};
    public static final int[] IN_HAND_CUR = {20 , 50, 20, 20, 20 };

    
    public static final int[] QUEEN_CRUSHED_CUR = {1000, 0};
    public static final int[] QUEEN_CRUSHED_OP = {-1000, 0};
    public static double P_J ;
    public static double D_J ;
    
    public static final int QUEEN_BEE = 0;
    public static final int SPIDER = 1;
    public static final int BEETLE = 2;
    public static final int GRASSHOPPER = 3;
    public static final int SOLDIER_ANT = 4;
    
    public static final int trans(InsectType type){
        switch(type){
            case QUEEN_BEE : return QUEEN_BEE;
            case SPIDER : return SPIDER;
            case BEETLE : return BEETLE;
            case GRASSHOPPER : return GRASSHOPPER;
            case SOLDIER_ANT : return SOLDIER_ANT;
        }
        return 0;
    }
    
    public static final void initMult(int nbCoupPossibles, int freeQueen, int freeNeighbours){
        if(nbCoupPossibles<=10 || (freeQueen !=0 && freeNeighbours > 3)){
            P_J = 4;
            D_J = 0.6;
        }
        else{
            P_J = 1;
            D_J = 1;

        }
    }
    
    public static final int[][][] getHeuristic(){
        int [][][] heuristicValues = new int [2][8][6];
        
        heuristicValues[0][0] = NB_QUEEN_OP_POSSIBILITY;
        heuristicValues[0][1] = QUEEN_NEIGH_BLOCK_OP_OP;
        heuristicValues[0][2] = QUEEN_NEIGH_FREE_OP_OP;
        heuristicValues[0][3] = QUEEN_NEIGH_BLOCK_OP_CUR;
        heuristicValues[0][4] = QUEEN_NEIGH_FREE_OP_CUR;
        heuristicValues[0][5] = INSECT_VALUE_OP;
        heuristicValues[0][6] = INSECT_BLOCK_VALUE_OP;
        heuristicValues[0][7] = IN_HAND_OP;
        
        heuristicValues[1][0] = NB_QUEEN_CUR_POSSIBILITY;
        heuristicValues[1][1] = QUEEN_NEIGH_BLOCK_CUR_CUR;
        heuristicValues[1][2] = QUEEN_NEIGH_FREE_CUR_CUR;
        heuristicValues[1][3] = QUEEN_NEIGH_BLOCK_CUR_OP;
        heuristicValues[1][4] = QUEEN_NEIGH_FREE_CUR_OP;
        heuristicValues[1][5] = INSECT_VALUE_CUR;
        heuristicValues[1][6] = INSECT_BLOCK_VALUE_CUR;
        heuristicValues[1][7] = IN_HAND_CUR;
        
        return heuristicValues;
    }
    
    
    public static final ArrayList<InsectType> insects_min;
    public static final ArrayList<InsectType> insects_max;
    
    static
    {
        insects_max = new ArrayList<>(5);
        insects_max.add(InsectType.QUEEN_BEE);
        insects_max.add(InsectType.SOLDIER_ANT);
        insects_max.add(InsectType.BEETLE);
        insects_max.add(InsectType.GRASSHOPPER);
        insects_max.add(InsectType.SPIDER);
        
        insects_min = new ArrayList<>(5);
        insects_min.add(InsectType.QUEEN_BEE);
        insects_min.add(InsectType.SPIDER);
        insects_min.add(InsectType.GRASSHOPPER);
        insects_min.add(InsectType.BEETLE);
        insects_min.add(InsectType.SOLDIER_ANT);
    }
}
