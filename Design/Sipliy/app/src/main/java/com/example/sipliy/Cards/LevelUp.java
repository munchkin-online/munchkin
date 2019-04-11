package com.example.sipliy.Cards;

public class LevelUp implements Cards
{

    private String name;    //имя карты
    private String helpText;    //доп. информация о карте

    public LevelUp(String name, String helpText)
    {
        this.name = name;
        this.helpText = helpText;
    }

    @Override
    public String getHelpText()
    {
        return helpText;
    }

    @Override
    public String getName()
    {
        return name;
    }
}
