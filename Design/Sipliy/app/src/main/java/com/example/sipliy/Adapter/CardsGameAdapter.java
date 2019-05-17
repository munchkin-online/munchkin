package com.example.sipliy.Adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sipliy.Cards.Buff;
import com.example.sipliy.Cards.Doors;
import com.example.sipliy.Cards.Items;
import com.example.sipliy.Data.PlayerInstances;
import com.example.sipliy.MenuPlayer;
import com.example.sipliy.R;

import java.util.ArrayList;
import java.util.List;

public class CardsGameAdapter extends RecyclerView.Adapter<CardsGameAdapter.PlayerViewHolder>
{

    private OnItemClickListener listener;
    private static List<Object> cardsList = new ArrayList<>();

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }
    public void setOnItemClickListner(OnItemClickListener listener){
        this.listener = listener;
    }

    //    public void setItems(Collection<MenuPlayer> plaers){
//        plaersList.addAll(plaers);
//        notifyDataSetChanged();
//    }
//
//    public void clearItems(){
//        plaersList.clear();
//        notifyDataSetChanged();
//    }
//
    public void addItem(Object o)
    {
        cardsList.add(o);
        notifyDataSetChanged();
    }

    public static void delete(int id){
        for (int i = 0; i < cardsList.size(); i++)
        {
            if (((Items)cardsList.get(i)).getID() == id || ((Buff)cardsList.get(i)).getID() == id)
            {
                cardsList.remove(i);
            }
        }
        //notifyDataSetChanged();
    }
//
//    public void addItemPlay(String str){
//        if (!thereIs(str)){
//            plaersList.add(new MenuPlayer(str, true));
//            Sort();
//            notifyDataSetChanged();
//        }
//    }
//
//    public void isPlay(int i){
//        plaersList.get(i).setBoolStatus(true);
//        Sort();
//        notifyDataSetChanged();
//    }
//
//    public void toIvite(String name){
//        boolean f = false;
//        for (MenuPlayer item : plaersList){
//            if (item.getName().equals(name)){
//                isPlay(plaersList.indexOf(item));
//                f = true;
//            }
//        }
//        if (!f){
//            addItemPlay(name);
//        }
//    }
//
//    public boolean thereIs(String name){
//        boolean f = false;
//        for(MenuPlayer item : plaersList){
//            if (item.getName().equals(name)){
//                f = true;
//            }
//        }
//
//        return f;
//    }
//
//    public void Sort(){
//        int startIndex = 0;
//        int endIndex = plaersList.size() - 1;
//        doSort(startIndex, endIndex);
//    }
//    public void doSort(int start, int end) {
//        if (start >= end)
//            return;
//        int i = start, j = end;
//        int cur = i - (i - j) / 2;
//        while (i < j) {
//            while (i < cur && (Boolean.compare(plaersList.get(i).isBoolStatus(), false) >= Boolean.compare(plaersList.get(cur).isBoolStatus(), false))) {
//                i++;
//            }
//            while (j > cur && (Boolean.compare(plaersList.get(cur).isBoolStatus() ,false) >= Boolean.compare(plaersList.get(j).isBoolStatus(), false))) {
//                j--;
//            }
//            if (i < j) {
//                MenuPlayer temp = plaersList.get(i);
//                plaersList.set(i,plaersList.get(j));
//                plaersList.set(j,temp);
//                if (i == cur)
//                    cur = j;
//                else if (j == cur)
//                    cur = i;
//            }
//        }
//        doSort(start, cur);
//        doSort(cur+1, end);
//    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.recycleview_item_cards_game, viewGroup, false);
        return new PlayerViewHolder(view, listener);

    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder playerViewHolder, int i)
    {
        playerViewHolder.bind(PlayerInstances.getPlayer().getDecks().getAll().get(i));
    }

    @Override
    public int getItemCount()
    {
        return PlayerInstances.getPlayer().getDecks().getAll().size();
    }

    class PlayerViewHolder extends RecyclerView.ViewHolder
    {

        ImageView icon;

        public PlayerViewHolder(View itemView, final OnItemClickListener listener)
        {
            super(itemView);

            icon = (ImageView)itemView.findViewById(R.id.imageViewGameCard);
//            plus.setImageResource(R.drawable.cardbackdoors);
            icon.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (listener != null)
                    {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

        void bind(Object o)
        {
            if(o instanceof Doors){
                icon.setImageResource(R.drawable.cardbackdoors);
            }
            if(o instanceof Items){
                icon.setImageResource(R.drawable.cardbacktreasures);
            }
            if(o instanceof Buff){
                icon.setImageResource(R.drawable.cardbacktreasures);
            }
        }
    }
}

