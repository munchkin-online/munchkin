package com.example.sipliy.Cards;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sipliy.Adapter.CardsGameAdapter;

import java.util.ArrayList;

public class PlayerDecks    //колода игрока
{
    private ArrayList<Items> items; //колода шмоток
    private ArrayList<Buff> buff;  //колода баффов и дебаффов
    private ArrayList<Object> doors; //колода монстров
    private ArrayList<Object> all;
    private CardsGameAdapter cardsAdapter;


    public ArrayList<Items> getItems() {
        return items;
    }

    public ArrayList<Buff> getBuff() {
        return buff;
    }

    public PlayerDecks()
    {
        items = new ArrayList<>();
        buff = new ArrayList<>();
        doors = new ArrayList<>();
        all = new ArrayList<>();
    }

    public void deleteItem(Items item)
    {
        items.remove(item);
        all.remove(item);
        update();
    }

    public void deleteBuff(Buff buf)
    {
        buff.remove(buf);
        buff.remove(buff);
        update();
    }


    public void addCard(Items card) //добавление карт шмоток в колоду
    {
        items.add(card);
        all.add(card);
        update();
    }

    public void addCard(Buff card)  //добавление карт баффов и дебаффов в колоду
    {
        buff.add(card);
        all.add(card);
        update();
    }

    public ArrayList<Object> getAll()
    {
        return all;
    }

    public void addCard(Object card)
    {
        doors.add(card);
        all.add(card);
        update();
    }

    public ArrayList<Object> getDoors() {
        return doors;
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
        this.buff.clear();
        this.all.clear();
        update();
    }

    public void bildRecyclerView(Context context, RecyclerView cardsList)
    {
        LinearLayoutManager layoutManagerCards = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        cardsList.setLayoutManager(layoutManagerCards);
        cardsAdapter = new CardsGameAdapter();
        cardsList.setAdapter(cardsAdapter);
    }

    private void update()
    {
        if(cardsAdapter != null){
            cardsAdapter.update();
        }
    }
}
