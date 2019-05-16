package com.example.sipliy.Cards;

public class Classes implements Cards
{
    private int ID;
    private String name;

    public Classes(int ID, String name)
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
        return null;
    }
}
