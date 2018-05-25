/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions.cerveau;

import hive.model.insects.InsectType;
import java.util.ArrayList;

/**
 *
 * @author Coralie
 */
public class HeuristicLearning {
    
    ArrayList<Integer> evalValues;
    
   /* public static final int[] NB_QUEEN_OP_POSSIBILITY = {170, 152, -166, -166, -166};
    public static final int[] QUEEN_NEIGH_BLOCK_OP_OP = {16, 65, 84, 200, 110};
    public static final int[] QUEEN_NEIGH_FREE_OP_OP = {0, 0, 0, 0, 0};
    public static final int[] QUEEN_NEIGH_BLOCK_OP_CUR = {16, 65, 84, 200, 110};
    public static final int[] QUEEN_NEIGH_FREE_OP_CUR = {16, 65, 84, 200, 110};
    public static final int[] INSECT_VALUE_OP = {-50, -56, -50, -191, -132};
    public static final int[] INSECT_BLOCK_VALUE_OP = {64, 122, 121, 144, 140};
    public static final int[] IN_HAND_OP = {-20 , -20, -20, -20, -20 };
    
    
    public static final int[] NB_QUEEN_CUR_POSSIBILITY = {-184, -170, 150, 150, 150};
    public static final int[] QUEEN_NEIGH_BLOCK_CUR_CUR = {-136, -180, -200, -180, -180};
    public static final int[] QUEEN_NEIGH_FREE_CUR_CUR = {-10, -10, -10, -10, -10};
    public static final int[] QUEEN_NEIGH_BLOCK_CUR_OP = {-136, -180, -200, -180, -180};
    public static final int[] QUEEN_NEIGH_FREE_CUR_OP = {-136, -180, -200, -180, -180};
    public static final int[] INSECT_VALUE_CUR = {50, 56, 50, 191, 132};
    public static final int[] INSECT_BLOCK_VALUE_CUR = {-64, -122, -121, -144, -140};
    public static final int[] IN_HAND_CUR = {0 , -100, 20, 30, 20 };

    */
    public static final int[] QUEEN_CRUSHED_CUR = {-200, 0};
    public static final int[] QUEEN_CRUSHED_OP = {175, 0};
    
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
    
    public static final int[][][] getHeuristic(){
        int [][][] heuristicValues = new int [2][8][5];        
        /*
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
        heuristicValues[1][7] = IN_HAND_CUR;*/
        int n = 0;
        for(int i =0;i<2;i++){
            for(int j =0 ; j<8;j++){
                for(int k = 0; k<5;k++){
                    assert(n<EvaluationLearning.evalValues.size());
                    heuristicValues[i][j][k]=EvaluationLearning.evalValues.get(n);
                    n++;
                }
            }
        }
        
        return heuristicValues;
    }
    
}
