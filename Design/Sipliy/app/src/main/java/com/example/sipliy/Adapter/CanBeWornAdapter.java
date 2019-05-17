package com.example.sipliy.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.sipliy.Cards.Items;
import com.example.sipliy.Data.PlayerInstances;
import com.example.sipliy.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class CanBeWornAdapter extends RecyclerView.Adapter<CanBeWornAdapter.CBWViewHolder>
{
    private static OnItemClickListener clickListener;
    private ArrayList<Items> list_cwb;
    private Context context;
    private LayoutInflater layoutInflater;

    public CanBeWornAdapter(Context context)
    {
        this.list_cwb = PlayerInstances.getPlayer().getDecks().getItems();
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CBWViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = layoutInflater.inflate(R.layout.can_be_worn, viewGroup, false);
        return new CBWViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CBWViewHolder cbwViewHolder, int i)
    {
       cbwViewHolder.bonus.setText(PlayerInstances.getPlayer().getDecks().getItems().get(i).getBonus());
       cbwViewHolder.name.setText(PlayerInstances.getPlayer().getDecks().getItems().get(i).getName());
    }

    @Override
    public int getItemCount()
    {
        return PlayerInstances.getPlayer().getDecks().getItems().size();
    }

    class CBWViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private final TextView name;
        private final TextView bonus;

        public CBWViewHolder(@NonNull View itemView)
        {
            super(itemView);

            name = itemView.findViewById(R.id.cbw_name);
            bonus = itemView.findViewById(R.id.cbw_bonus);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v)
        {
            clickListener.onItemClick(v, getAdapterPosition());
        }
    }
    public interface OnItemClickListener
    {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListner(OnItemClickListener clickListner)
    {
        CanBeWornAdapter.clickListener = clickListener;
    }
}
