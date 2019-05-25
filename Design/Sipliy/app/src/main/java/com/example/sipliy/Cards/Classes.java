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
    public int getType()  // 1 - класс, 2 - расы, 3 - монстры, 4 - проклятья.
    {
        return 1;
    }

    @Override
    public String getName()
    {
        return null;
    }
}
