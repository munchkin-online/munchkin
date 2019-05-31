package com.example.sipliy.Cards;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.sipliy.Activity.Dialog.BattleDialog;
import com.example.sipliy.Activity.Dialog.ChoiceBattleDialogActivity;
import com.example.sipliy.Activity.Dialog.PutOnCardDialogActivity;
import com.example.sipliy.Activity.GameActivity;
import com.example.sipliy.Activity.InventoryActivity;
import com.example.sipliy.Adapter.CardsGameAdapter;
import com.example.sipliy.Cards.Interface.Cards;
import com.example.sipliy.Cards.Interface.DoorsInterface;
import com.example.sipliy.Cards.Interface.TreasuresInterface;
import com.example.sipliy.Data.PlayerInstances;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class PlayerDecks    //колода игрока
{
    private ArrayList<TreasuresInterface> treasures;    //колода сокровищ
    private ArrayList<DoorsInterface> doors;    //колода монстров
    private ArrayList<Cards> all;
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

    public void clear(){
        this.treasures.clear();
        this.doors.clear();
        this.all.clear();
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
        if(card == null)
            return;
        treasures.add(card);
        all.add((Cards) card);
        update();
    }

    public ArrayList<Cards> getAll()
    {
        return all;
    }

    public void addCard(DoorsInterface card)
    {
        if(card == null)
            return;
        doors.add(card);
        all.add((Cards) card);
        update();
    }

    public ArrayList<DoorsInterface> getDoors()
    {
        return doors;
    }

    public ArrayList<TreasuresInterface> getTreasures() {
        return treasures;
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
        this.all.clear();
        update();
    }

    public void buildRecyclerView(final Context context, RecyclerView cardsList)
    {
        LinearLayoutManager layoutManagerCards = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        cardsList.setLayoutManager(layoutManagerCards);
        cardsAdapter = new CardsGameAdapter(context, all);
        cardsList.setAdapter(cardsAdapter);
        cardsAdapter.setOnItemClickListner(new CardsGameAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (PlayerInstances.getPlayer().isCanPlay()){
                    FragmentManager fragmentManager = ((FragmentActivity) context).getSupportFragmentManager();
                    Cards card = PlayerInstances.getPlayer().getDecks().getAll().get(position);
                    switch (card.getType()){  // 1 - класс, 2 - расы, 3 - монстры, 4 - проклятья, 5 - разовые шмотки, 6 - шмотки, 7 - LevelUp(хз что это)
                        case 1:
                            Log.d("isClass", "Classe");
                            PutOnCardDialogActivity putOnCardDialogActivity = new PutOnCardDialogActivity();
                            putOnCardDialogActivity.setCard(card);
                            putOnCardDialogActivity.show(fragmentManager, "Notice Data");
                            break;
                        case 2:
                            Log.d("isClass", "Races");
                            putOnCardDialogActivity = new PutOnCardDialogActivity();
                            putOnCardDialogActivity.setCard(card);
                            putOnCardDialogActivity.show(fragmentManager, "Notice Data");
                            break;
                        case 3:
                            Log.d("isClass", "Monster");
                            ChoiceBattleDialogActivity choiceBattleDialogActivity = new ChoiceBattleDialogActivity();
                            choiceBattleDialogActivity.setMonster((Monster) card);
                            choiceBattleDialogActivity.show(fragmentManager, "Notice Data");
                            break;
                        case 4:
                            Log.d("isClass", "Проклятья");
                            Toast.makeText(context, "Это проклятья", Toast.LENGTH_LONG).show();
                            break;
                        case 5:
                            Log.d("isClass", "Buff");
                            Toast.makeText(context, "Это Buff", Toast.LENGTH_LONG).show();
                            break;
                        case 6:
                            Log.d("isClass", "Items");
                            context.startActivity(new Intent(context, InventoryActivity.class));
                            break;
                        case 7:
                            Log.d("isClass", "LevelUp");
                            Toast.makeText(context, "Это LevelUp", Toast.LENGTH_LONG).show();
                            break;
                    }
                }
            }
        });
    }

    private void update()
    {
        if(cardsAdapter != null)
        {
            cardsAdapter.update();
        }
    }
}
