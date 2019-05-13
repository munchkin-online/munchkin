package com.example.sipliy.Cards;

import java.util.ArrayList;

public class PlayerDecks
{
    private ArrayList<Items> items; //колода шмоток
    private ArrayList<BuffOrDebuff> buffOrDebuff;  //колода баффов и дебаффов

    public PlayerDecks()
    {
        items = new ArrayList<>();
        buffOrDebuff = new ArrayList<>();
    }

    public void addCard(Items card) //добавление карт шмоток в колоду
    {
        items.add(card);
    }

    public void addCard(BuffOrDebuff card)  //добавление карт баффов и дебаффов в колоду
    {
        buffOrDebuff.add(card);
    }

    public Items getItemCard(int i)
    {
        return items.get(i);
    }

    public BuffOrDebuff getBuffOrDebuffCard(int i)
    {
        return buffOrDebuff.get(i);
    }

    public void reset()
    {
        DiscardDecks.addCard(items);
        DiscardDecks.addCard(buffOrDebuff);
        this.items.clear();
        this.buffOrDebuff.clear();
    }
}
