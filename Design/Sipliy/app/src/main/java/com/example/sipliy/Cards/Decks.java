package com.example.sipliy.Cards;

import java.util.ArrayList;

public class Decks
{
    private ArrayList<Items> items; //колода шмоток
    private ArrayList<BuffOrDebuff> buffOrDebuff;  //колода баффов и дебаффов

    public Decks()
    {
        items = new ArrayList<>();
        buffOrDebuff = new ArrayList<>();
        fill();
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

    public void fill()
    {
        items.add(new Items(11001, "Шлем бесстрашия", 1, 1, 1, 1, 1, 200));
        items.add(new Items(11002, "Кожаный прикид",1,  2, 1, 1, 1, 200));
        items.add(new Items(11003, "Бензопила Кровавого расчленения",3,  5, 1, 1, 1, 600));
        items.add(new Items(11004, "Посох Напалма",5,  4, 1, 3, 1, 800));
        items.add(new Items(11005, "Колотушка резкости",4,  4, 1, 2, 1, 600));
        items.add(new Items(11006, "Одиннадцатифутовый кий",1,  5, 1, 1, 1, 200));
        items.add(new Items(11007, "Шлем-Рогач",1,  1, 3, 1, 1, 600, 3));
        items.add(new Items(11008, "Терка Умиротворения",3,  4, 1, 2, 1, 400));
        items.add(new Items(11009, "Кинжал Изменты",3,  4, 1, 5, 1, 400));
        items.add(new Items(11010, "Башмаки Могучего Пендаля",2,  1, 1, 1, 1, 400));
    }

}
