/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.board;

import util.Matrix;
import util.Vector2i;
import util.hexagons.CircularHexagonsGraph;

/**
 *
 * @author Thomas
 */
public class Board extends CircularHexagonsGraph<TilesStack>
{   
    public Board(Matrix<TilesStack> matrix)
    {
        super(matrix, new HiveNeighborsShifter());
    }
    
    @Override
    public String toString()
    {
        String res = "";
        res += toStringLine("  _______  ", "       ");
        res += "\n";
        for (int y = 0; y < getData().getData().length; y++)
        {
            res += toStringLine(" /       \\ ", "       ");
            res += "\n";
            res += toStringThirdLine(y);
            res += "\n";
            res += toStringLine("\\         /", "       ");
            res += "\n";
            res += toStringFifthLine(y);
            res += "\n";
        }
        res += "         ";
        res += toStringLine("\\         / ", "      ");
        res += "\n";
        res += "          ";
        res += toStringLine("\\_______/ ", "        ");

        return res;
    }

    private String toStringLine(String isCaseStr, String isNotCaseStr)
    {
        String res = "";
        boolean isCase = true;
        for (Object item : getData().getData()[0])
        {
            res += isCase ? isCaseStr : isNotCaseStr;
            isCase = !isCase;
        }
        return res;
    }

    private String toStringThirdLine(int y)
    {
        String res = "";
        boolean isCase = true;
        for (Object item : getData().getData()[y])
        {
            String str = item != null && !item.toString().equals("[]") ? item.toString() : "     ";
            res += isCase ? "/  " + str + "  \\" : "_______";
            isCase = !isCase;
        }
        return res;
    }

    private String toStringFifthLine(int y)
    {
        String res = "";
        boolean isCase = true;
        for (Object item : getData().getData()[y])
        {
            String str = item != null && !item.toString().equals("[]") ? item.toString() : "     ";
            res += isCase ? " \\_______/ " : " " + str + " ";
            isCase = !isCase;
        }
        return res;
    }
}
