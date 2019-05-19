package com.example.sipliy.Cards;

import com.example.sipliy.Cards.Interface.Cards;

public class LevelUp implements Cards
{
    private int ID; //id карты
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
