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
public final class LanguagesGesture
{

    private final String languagePropertiesPath;
    private Locale language;
    private ResourceBundle bundle;
    public HashMap<String, Locale> languages;

    public LanguagesGesture(String _langue)
    {
        languagePropertiesPath = "properties/languages/text";
        languages = new HashMap<>();
        getImplementedLanguages().forEach((currentlangue) ->
        {
            languages.put(currentlangue.getDisplayName(Locale.getDefault()), currentlangue);
        });
        language = languages.get(_langue);
        bundle = ResourceBundle.getBundle(languagePropertiesPath, language);
    }

    public Locale getLanguage()
    {
        return language;
    }

    public void setLanguage(Locale _langue)
    {
        assert getImplementedLanguages().contains(_langue);
        language = _langue;
        bundle = ResourceBundle.getBundle(languagePropertiesPath, language);
    }

    public ArrayList<Locale> getImplementedLanguages()
    {
        ArrayList<Locale> retLangues = new ArrayList<>();
        retLangues.add(Locale.FRENCH);
        retLangues.add(Locale.ENGLISH);
        retLangues.add(Locale.GERMAN);
        retLangues.add(Locale.ITALIAN);
        retLangues.add(new Locale("pt"));
        retLangues.add(new Locale("ru"));
        retLangues.add(new Locale("ar"));
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
