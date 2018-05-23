/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hive.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author lucas
 */
public final class GestionnaireLangage
{
    Locale langue;
    ResourceBundle bundle;
    public HashMap<String, Locale> langues;

    public GestionnaireLangage(Locale _langue)
    {
        langue = _langue;
        bundle = ResourceBundle.getBundle("properties/text", langue);
        langues = new HashMap<>();
        getImplementedLanguages().forEach((currentlangue) ->
        {
            langues.put(currentlangue.getDisplayName(), currentlangue);
        });
    }

    public Locale getCurrentLanguage()
    {
        return langue;
    }

    public void changerLangue(Locale _langue)
    {
        assert getImplementedLanguages().contains(_langue);
        langue = _langue;
        bundle = ResourceBundle.getBundle("properties/text", langue);
    }

    public ArrayList<Locale> getImplementedLanguages()
    {
        ArrayList<Locale> retLangues = new ArrayList<>();
        retLangues.add(Locale.FRENCH);
        retLangues.add(Locale.ENGLISH);
        retLangues.add(Locale.GERMAN);
        retLangues.add(Locale.ITALIAN);
        retLangues.add(new Locale("ru"));
        return retLangues;
    }

    public ArrayList<String> getImplementedLanguagesString()
    {
        ArrayList<String> stringLanguages = new ArrayList<>();
        getImplementedLanguages().forEach((currentlangue) ->
        {
            stringLanguages.add(currentlangue.getDisplayName());
        });
        return stringLanguages;
    }

    public String getText(String textName)
    {
        return (String) bundle.getObject(textName);
    }
}
