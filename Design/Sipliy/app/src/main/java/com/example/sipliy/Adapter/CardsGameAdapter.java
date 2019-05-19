package com.example.sipliy.Adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sipliy.Cards.Buff;
import com.example.sipliy.Cards.Classes;
import com.example.sipliy.Cards.Doors;
import com.example.sipliy.Cards.Items;
import com.example.sipliy.Cards.Monster;
import com.example.sipliy.Cards.Races;
import com.example.sipliy.Data.PlayerInstances;
import com.example.sipliy.R;

public class CardsGameAdapter extends RecyclerView.Adapter<CardsGameAdapter.PlayerViewHolder>
{

    private OnItemClickListener listener;

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }
    public void setOnItemClickListner(OnItemClickListener listener){
        this.listener = listener;
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
            if(o instanceof Items)
            {
                icon.setImageResource(R.drawable.cardbacktreasures);
            }
            else if(o instanceof Buff)
            {
                icon.setImageResource(R.drawable.cardbacktreasures);
            }
            else if(o instanceof Doors)
            {
                icon.setImageResource(R.drawable.cardbackdoors);
            }
            else if(o instanceof Monster)
            {
                icon.setImageResource(R.drawable.monstr);
            }
            else if(o instanceof Races)
            {
                icon.setImageResource(R.drawable.rasi);
            }
            else if(o instanceof Classes)
            {
                icon.setImageResource(R.drawable.clas);
            }
            else
            {
                icon.setImageResource(R.drawable.d11001);
            }
        }
    }
}

