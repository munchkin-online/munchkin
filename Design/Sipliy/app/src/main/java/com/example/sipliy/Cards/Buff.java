package com.example.sipliy.Cards;

public class Buff implements BuffInterface, Cards, ItemsInterface
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

    public int getID()
    {
        return ID;
    }

    @Override
    public int getValue()
    {
        return value;
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
}
