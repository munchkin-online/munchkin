package com.example.sipliy.Cards;

import com.example.sipliy.Cards.Interface.DoorsInterface;
import com.example.sipliy.Cards.Interface.TreasuresInterface;

import java.util.ArrayList;

//TODO сделать взаимодействие со сброшенными картами

public class DiscardDecks //карты сброшенные в сброс-колоду
{
    private static ArrayList<TreasuresInterface> discardTreasures = new ArrayList<>(); //сброшенная карты из колоды сокровищ
    private static ArrayList<DoorsInterface> discardDoors = new ArrayList<>();  //сброшенные карты из колоды дверей

    public static void addCard(TreasuresInterface card) //добавление карт шмоток в колоду
    {
        discardTreasures.add(card);
    }
    public static void addCard(DoorsInterface card)
    {
        discardDoors.add(card);
    }
    public static void addTreasures(ArrayList<TreasuresInterface> cards)  //добавление карт баффов и дебаффов в колоду
    {
        discardTreasures.addAll(cards);
    }
    public static void addDoors(ArrayList<DoorsInterface> cards)  //добавление карт баффов и дебаффов в колоду
    {
        discardDoors.addAll(cards);
    }

}
