package com.example.sipliy.Cards;

import com.example.sipliy.Cards.Interface.Cards;
import com.example.sipliy.Cards.Interface.DoorsInterface;
import com.example.sipliy.R;

public class Races implements Cards, DoorsInterface
{
    private int ID;
    private String name;
    private int IMAGE_ID;
    private int type; //раса носителя (1-human, 2-dwarf, 3-elf, 4-hufling)

    public Races(int ID, String name, int IMAGE_ID, int type)
    {
        this.ID = ID;
        this.name = name;
        this.IMAGE_ID = IMAGE_ID;
        this.type = type;
    }

    public int getRacesType()
    {
        return type;
    }

    public int getID()
    {
        return ID;
    }

    @Override
    public int getType()  // 1 - класс, 2 - расы, 3 - монстры, 4 - проклятья, 5 - разовые шмотки, 6 - шмотки, 7 - LevelUp(хз что это)
    {
        return 2;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public int getIMAGE_ID()
    {
        return IMAGE_ID;
    }
}
