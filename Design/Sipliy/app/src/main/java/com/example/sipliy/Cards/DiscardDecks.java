package com.example.sipliy.Cards;

import java.util.ArrayList;

//TODO сделать взаимодействие со сброшенными картами

public class DiscardDecks //карты сброшенные в сброс-колоду
{
    private static ArrayList<Items> discardItems; //колода шмоток
    private static ArrayList<Buff> discardBuff;  //колода баффов и дебаффов

    public static void addCard(Items card) //добавление карт шмоток в колоду
    {
        discardItems.add(card);
    }

    public static void addCard(Buff card)  //добавление карт баффов и дебаффов в колоду
    {
        discardBuff.add(card);
    }
    public static void addCardsItems(ArrayList<Items> cards)  //добавление карт баффов и дебаффов в колоду
    {
        discardItems.addAll(cards);
    }
    public static void addCardsBuffDebuff(ArrayList<Buff> cards)  //добавление карт баффов и дебаффов в колоду
    {
        discardBuff.addAll(cards);
    }

}
