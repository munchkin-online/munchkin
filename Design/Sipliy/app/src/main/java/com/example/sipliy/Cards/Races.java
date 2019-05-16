package com.example.sipliy.Cards;

public class Races implements Cards
{
    private int ID;
    private String name;

    public Races(int ID, String name)
    {
        this.ID = ID;
        this.name = name;
    }

    public int getID()
    {
        return ID;
    }

    @Override
    public String getName()
    {
        return name;
    }
}
