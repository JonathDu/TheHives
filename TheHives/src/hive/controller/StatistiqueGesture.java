/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller;

import java.io.File;
import java.util.HashMap;

/**
 *
 * @author lucas
 */
public class StatistiqueGesture
{
    
    private static final String SCORES_DIR_PATH = System.getProperty("user.dir") + "/src/conf/scores";
    
    public static HashMap<String, String> getScoresFor(String playerName)
    {
        MyProperties prop = new MyProperties(SCORES_DIR_PATH + "/" + playerName);
        return prop.getProperties();
    }
    
    public static void setWinScoreFor(String playerName, String opponentName)
    {
        MyProperties prop = new MyProperties(SCORES_DIR_PATH + "/" + playerName);
        String score = prop.get(opponentName) == null ? "0/0" : prop.get(opponentName);
        int nbWin = Integer.parseInt(score.split("/")[0]) + 1;
        int nbGames = Integer.parseInt(score.split("/")[1]) + 1;
        prop.set(opponentName, String.valueOf(nbWin) + "/" + String.valueOf(nbGames));
    }
    
    public static void setLoseScoreFor(String playerName, String opponentName)
    {
        MyProperties prop = new MyProperties(SCORES_DIR_PATH + "/" + playerName);
        String score = prop.get(opponentName) == null ? "0/0" : prop.get(opponentName);
        int nbWin = Integer.parseInt(score.split("/")[0]);
        int nbGames = Integer.parseInt(score.split("/")[1]) + 1;
        prop.set(opponentName, String.valueOf(nbWin) + "/" + String.valueOf(nbGames));
    }
    
    public static HashMap<String, HashMap<String,String>> getPlayersScores()
    {
        HashMap<String, HashMap<String,String>> map = new HashMap<>();
        File dir = new File(SCORES_DIR_PATH);
        for(File file : dir.listFiles())
        {
            MyProperties prop = new MyProperties(file.getAbsolutePath());
            map.put(file.getName(), prop.getProperties());
        }
        return map;
    }
}
