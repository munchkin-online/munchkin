package com.example.sipliy.Cards;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.sipliy.Adapter.CardsGameAdapter;
import com.example.sipliy.Cards.Interface.DoorsInterface;
import com.example.sipliy.Cards.Interface.TreasuresInterface;

import java.util.ArrayList;

public class PlayerDecks    //колода игрока
{
    private ArrayList<TreasuresInterface> treasures;    //колода сокровищ
    private ArrayList<DoorsInterface> doors;    //колода монстров
    private ArrayList<Object> all;
    private CardsGameAdapter cardsAdapter;

    public PlayerDecks()
    {
        treasures = new ArrayList<>();
        doors = new ArrayList<>();
        all = new ArrayList<>();
    }

    public ArrayList<Items> getItems()
    {
        ArrayList<Items> items = new ArrayList<>();
        for(int i = 0; i < treasures.size(); i++)
        {
            if(treasures.get(i).getClass() == Items.class)
            {
                items.add((Items)treasures.get(i));
            }
        }
        return items;
    }

    public ArrayList<Buff> getBuffs()
    {
        ArrayList<Buff> buffs = new ArrayList<>();
        for(int i = 0; i < treasures.size(); i++)
        {
            if(treasures.get(i).getClass() == Buff.class)
            {
                buffs.add((Buff)treasures.get(i));
            }
        }
        return buffs;
    }


    public void deleteItem(Items item)
    {
        treasures.remove(item);
        all.remove(item);
        update();
    }

    public void deleteBuff(Buff buff)
    {
        treasures.remove(buff);
        all.remove(buff);
        update();
    }


    public void addCard(TreasuresInterface card) //добавление карт шмоток в колоду
    {
        treasures.add(card);
        all.add(card);
        update();
    }

    public ArrayList<Object> getAll()
    {
        return all;
    }

    public void addCard(DoorsInterface card)
    {
        doors.add(card);
        all.add(card);
        update();
    }

    public ArrayList<DoorsInterface> getDoors()
    {
        return doors;
    }

    public TreasuresInterface getTreasuresCard(int id)
    {
        for(TreasuresInterface card : treasures)
        {
            if(card.getID() == id)
                return card;
        }
        return null;
    }

    public void reset()
    {
        DiscardDecks.addTreasures(treasures);
        DiscardDecks.addDoors(doors);
        this.treasures.clear();
        this.doors.clear();
        update();
    }

    public void buildRecyclerView(Context context, RecyclerView cardsList)
    {
        LinearLayoutManager layoutManagerCards = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        cardsList.setLayoutManager(layoutManagerCards);
        cardsAdapter = new CardsGameAdapter();
        cardsList.setAdapter(cardsAdapter);
    }

    private void update()
    {
        if(cardsAdapter != null)
        {
            cardsAdapter.update();
        }
    }
}
