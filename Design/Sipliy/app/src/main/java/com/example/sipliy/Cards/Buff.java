package com.example.sipliy.Cards;

import com.example.sipliy.Cards.Interface.Cards;
import com.example.sipliy.Cards.Interface.TreasuresInterface;

public class Buff implements Cards, TreasuresInterface
{
    private final int ID; //id карты
    private final String name;    //имя карты
    private final int value;  //значение баффа
    private final int IMAGE_ID;
    private final int cost;   //стоимость карты

    public Buff(int id, String name, int value, int cost, int im)
    {
        this.ID = id;
        this.name = name;
        this.value = value;
        this.cost = cost;
        this.IMAGE_ID = im;
    }


    public int getValue()
    {
        return value;
    }
    @Override
    public int getType() { // 1 - класс, 2 - расы, 3 - монстры, 4 - проклятья, 5 - разовые шмотки, 6 - шмотки, 7 - LevelUp(хз что это)
        return 5;
    }

    @Override
    public int getID()
    {
        return ID;
    }

    @Override
    public int getCost()
    {
        return cost;
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
