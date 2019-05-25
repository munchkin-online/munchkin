package com.example.sipliy.Cards;

import com.example.sipliy.Cards.Interface.Cards;
import com.example.sipliy.Cards.Interface.TreasuresInterface;

public class Buff implements Cards, TreasuresInterface
{
    private int ID; //id карты
    private String name;    //имя карты
    private int value;  //значение баффа
    private int cost;   //стоимость карты

    public Buff(String name)
    {
        this.name = name;
    }

    public Buff(String name, int value, boolean isItForHero, int cost)
    {
        this.name = name;
        this.value = value;
        this.cost = cost;
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
        return 0;
    }


}
