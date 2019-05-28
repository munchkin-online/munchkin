package com.example.sipliy.Adapter;


import android.content.Context;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sipliy.Cards.Buff;
import com.example.sipliy.Cards.Classes;
import com.example.sipliy.Cards.Doors;
import com.example.sipliy.Cards.Interface.Cards;
import com.example.sipliy.Cards.Items;
import com.example.sipliy.Cards.Monster;
import com.example.sipliy.Cards.Races;
import com.example.sipliy.Data.PlayerInstances;
import com.example.sipliy.R;

import java.util.ArrayList;

public class CardsGameAdapter extends RecyclerView.Adapter<CardsGameAdapter.PlayerViewHolder>
{

    private ArrayList<Cards> cards;
    private OnItemClickListener listener;
    private Context context;

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }
    public void setOnItemClickListner(OnItemClickListener listener){
        this.listener = listener;
    }


    public CardsGameAdapter(Context context, ArrayList<Cards> cards)
    {
        this.cards = cards;
        this.context = context;
    }
    public void update()
    {
        notifyDataSetChanged();
    }

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
        //playerViewHolder.bind(PlayerInstances.getPlayer().getDecks().getAll().get(i));
        playerViewHolder.icon.setImageResource(PlayerInstances.getPlayer().getDecks().getAll().get(i).getIMAGE_ID());
    }

    @Override
    public int getItemCount()
    {
        return PlayerInstances.getPlayer().getDecks().getAll().size();
    }

    public static class PlayerViewHolder extends RecyclerView.ViewHolder
    {

        private final ImageView icon;

        public PlayerViewHolder(View itemView, final OnItemClickListener listener)
        {
            super(itemView);

            icon = (ImageView)itemView.findViewById(R.id.imageViewGameCard);
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
    }
}

