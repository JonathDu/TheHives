/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.players.decisions.example;

/**
 *
 * @author Thomas
 */
public interface IADecisionVisitor
{
    public void visit(EasyIADecision decision);
    public void visit(MediumIADecision decision);
    public void visit(HardIADecision decision);
}

class IADecisionDifficultyGetter implements IADecisionVisitor
{
    Difficulty difficulty;
    
    public void visit(EasyIADecision decision)
    {
        difficulty = Difficulty.EASY;
    }
    
    public void visit(MediumIADecision decision)
    {
        difficulty = Difficulty.MEDIUM;
    }
    
    public void visit(HardIADecision decision)
    {
        difficulty = Difficulty.HARD;
    }
    
    public Difficulty getDifficulty()
    {
        return difficulty;
    }
}
