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
public enum Info
{
    OK(0),
    IS_CRUSHED(1),
    IS_NOT_CONNEX_WITHOUT(2),
    IS_CRUSHED_AND_IS_NOT_CONNEX_WITHOUT(3);
    
    public int code;
    
    Info(int code)
    {
        this.code = code;
    }
    
    public int getCode()
    {
        return code;
    }
    
    public Info add(Info info)
    {
        this.code |= info.code;
        return this;
    }
    
    @Override
    public String toString()
    {
        switch(this)
        {
        case OK: return "Is not crushed and is connex without";
        case IS_CRUSHED: return "Is crushed";
        case IS_NOT_CONNEX_WITHOUT: return "Is not connex without";
        case IS_CRUSHED_AND_IS_NOT_CONNEX_WITHOUT: return "Is crushed and is not connex without";
        }
        assert false;
        return null;
    }
}
