/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.model.insects.behaviors.info;

/**
 *
 * @author Thomas
 */
public enum CombInfo
{
    NONE(0),
    
    IS_NOT_FREE(1),
    HAS_NO_WALL(2),
    IS_FREE(4),
    HAS_WALL(8),
    
    CANT_SLIDE_WITHOUT_WALL(6),
    CAN_SLIDE(12),
    
    IS_ABOVE(16),
    IS_ON_TILES(17),
    
    CAN_GO_DOWN(20),
    CAN_SLIDE_ON_TILES(21),
    
    CAN_CLIMB(32),
    
    CANT_CLIMB(33),
    
    CAN_JUMP(34),
    
    NEED_SMTHG_TO_JUMP(35);
    
    public int code;
    
    CombInfo(int code)
    {
        this.code = code;
    }
    
    public int getCode()
    {
        return code;
    }
    
    public CombInfo add(CombInfo info)
    {
        this.code |= info.code;
        return this;
    }
    
    @Override
    public String toString()
    {
        switch(this)
        {
        case IS_NOT_FREE: return "Not free"; // ERROR
        case CANT_SLIDE_WITHOUT_WALL: return "Can't slide without a wall"; // ERROR
        case CAN_SLIDE: return "Can slide"; // OK
        case CAN_GO_DOWN: return "Can go down"; // OK
        case CAN_SLIDE_ON_TILES: return "Can slide on tiles"; // OK
        case CAN_CLIMB: return "Can climb"; // OK
        case CANT_CLIMB: return "Can't climb"; // ERROR
        case CAN_JUMP: return "Can jump"; // OK
        case NEED_SMTHG_TO_JUMP: return "Need something to jump"; // ERROR
        }
        assert false;
        return null;
    }
}
