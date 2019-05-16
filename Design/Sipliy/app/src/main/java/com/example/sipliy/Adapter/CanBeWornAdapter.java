package com.example.sipliy.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sipliy.Cards.Items;
import com.example.sipliy.Cards.Treasures;
import com.example.sipliy.R;

import java.util.ArrayList;
import java.util.List;

public class CanBeWornAdapter extends RecyclerView.Adapter<CanBeWornAdapter.CBWViewHolder>
{
    private OnItemClickListener listener;

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }

    public void setOnItemClickListner(OnItemClickListener listner)
    {
        this.listener = listener;
    }

    private List<Items> cbw_list = new ArrayList<>();

    @NonNull
    @Override
    public CBWViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.can_be_worn, viewGroup, false);
        return new CBWViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CBWViewHolder cbwViewHolder, int i)
    {
        cbwViewHolder.bind(Treasures.getItemCard());
    }

    @Override
    public int getItemCount()
    {
        return cbw_list.size();
    }

    class CBWViewHolder extends RecyclerView.ViewHolder
   {
       TextView name;
       TextView bonus;

       public CBWViewHolder(@NonNull View itemView, OnItemClickListener listener)
       {
           super(itemView);

           name = itemView.findViewById(R.id.cbw_name);
           bonus = itemView.findViewById(R.id.cbw_bonus);

           name.setText("Кожаный прикид");
           bonus.setText("+1");
       }

       void bind(Items item)
       {
           name.setText(item.getName());
           bonus.setText(item.getBonus());
       }
   }
}
