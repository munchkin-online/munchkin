package com.example.sipliy.Cards;

import com.example.sipliy.Cards.Interface.Cards;

public class LevelUp implements Cards
{
    private int ID; //id карты

    @Override
    public int getType() {  // 1 - класс, 2 - расы, 3 - монстры, 4 - проклятья, 5 - разовые шмотки, 6 - шмотки, 7 - LevelUp(хз что это)
        return 7;
    }

    private String name;    //имя карты

    public LevelUp(String name)
    {
        this.name = name;
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
