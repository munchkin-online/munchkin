package com.example.sipliy.Cards;

import java.util.ArrayList;

public class DiscardDecks
{
    private static ArrayList<Items> discardItems; //колода шмоток
    private static ArrayList<BuffOrDebuff> DiscardBuffOrDebuff;  //колода баффов и дебаффов

    public static void addCard(Items card) //добавление карт шмоток в колоду
    {
        discardItems.add(card);
    }

    public static void addCard(BuffOrDebuff card)  //добавление карт баффов и дебаффов в колоду
    {
        DiscardBuffOrDebuff.add(card);
    }

    public static void addCard(ArrayList<BuffOrDebuff> cards)  //добавление карт баффов и дебаффов в колоду
    {
        DiscardBuffOrDebuff.addAll(cards);
    }
    public static void addCard(ArrayList<Items> cards)  //добавление карт баффов и дебаффов в колоду
    {
        discardItems.addAll(cards);
    }

}
