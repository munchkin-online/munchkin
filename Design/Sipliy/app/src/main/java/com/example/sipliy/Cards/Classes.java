package com.example.sipliy.Cards;

import com.example.sipliy.Cards.Interface.Cards;
import com.example.sipliy.Cards.Interface.DoorsInterface;

public class Classes implements Cards, DoorsInterface
{
    private int ID;
    private String name;
    private int IMAGE_ID;

    public Classes(int ID, String name, int IMAGE_ID)
    {
        this.ID = ID;
        this.name = name;
        this.IMAGE_ID = IMAGE_ID;
    }

    public int getID()
    {
        return ID;
    }

    @Override
    public int getIMAGE_ID()
    {
        return IMAGE_ID;
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
