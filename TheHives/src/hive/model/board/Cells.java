/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.board;

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
        return true;
    }
    
    public static boolean isBlocked(Cell cell)
    {
        return isSurrounded(cell) && isCrushed(cell);
    }
}
