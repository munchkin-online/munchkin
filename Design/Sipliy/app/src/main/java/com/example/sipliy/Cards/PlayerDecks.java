package com.example.sipliy.Cards;

import java.util.ArrayList;

public class PlayerDecks    //колода игрока
{
    private ArrayList<Items> items; //колода шмоток
    private ArrayList<Buff> buff;  //колода баффов и дебаффов

    public PlayerDecks()
    {
        items = new ArrayList<>();
        buff = new ArrayList<>();
    }

    public void addCard(Items card) //добавление карт шмоток в колоду
    {
        items.add(card);
    }

    public void addCard(Buff card)  //добавление карт баффов и дебаффов в колоду
    {
        buff.add(card);
    }

    public Items getItemCard(int i)
    {
        return items.get(i);
    }

    public Buff getBuffOrDebuffCard(int i)
    {
        return buff.get(i);
    }

    public void reset()
    {
        DiscardDecks.addCardsItems(items);
        DiscardDecks.addCardsBuffDebuff(buff);
        this.items.clear();
        this.buff.clear();
    }
}
