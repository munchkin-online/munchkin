package com.example.sipliy.Cards;

public class LevelUp implements Cards
{

    private String name;    //имя карты

    public LevelUp(String name)
    {
        this.name = name;
    }

    @Override
    public String getName()
    {
        return name;
    }
}
