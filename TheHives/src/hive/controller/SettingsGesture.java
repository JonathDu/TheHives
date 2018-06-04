/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller;

/**
 *
 * @author lucas
 */
public class SettingsGesture extends MyProperties
{
    private static final String SETTINGS_PATH = System.getProperty("user.dir") + "/src/conf/settings.txt";
    
    public SettingsGesture()
    {
        super(SETTINGS_PATH);
    }
    
    @Override
    public String get(String key)
    {
        return super.map.getOrDefault(key, "true");
    }
}
