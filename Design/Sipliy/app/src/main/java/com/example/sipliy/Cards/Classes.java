package com.example.sipliy.Cards;

import com.example.sipliy.Cards.Interface.Cards;
import com.example.sipliy.Cards.Interface.DoorsInterface;

public class Classes implements Cards, DoorsInterface
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
