package com.example.sipliy.Cards;

public class BuffOrDebuff implements BuffDebuffCards, Cards
{
    private String name;    //имя карты
    private int value;  //значение баффа или дебаффа
    private boolean isBuff; //бафф это или дебафф
    private boolean isItForHero;    //для героя или для монстра
    private int cost;   //стоимость карты

    public BuffOrDebuff(String name, int value, boolean isBuff, boolean isItForHero, int cost)
    {
        this.name = name;
        this.value = value;
        this.isBuff = isBuff;
        this.isItForHero = isItForHero;
        this.cost = cost;
    }

    public boolean isItBuff()
    {
        return isBuff;
    }

    public boolean isItForHero()
    {
        return isItForHero;
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
