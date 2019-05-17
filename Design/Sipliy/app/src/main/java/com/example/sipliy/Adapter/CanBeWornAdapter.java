package com.example.sipliy.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.sipliy.Cards.Items;
import com.example.sipliy.R;

import java.util.ArrayList;

public class CanBeWornAdapter extends RecyclerView.Adapter<CanBeWornAdapter.CBWViewHolder>
{
    private OnItemClickListner listner;
    private ArrayList<Items> list_cwb;

    public interface OnItemClickListner{
        void onItemClick(View v, int position);
    }

    public void setOnItemClickListner(OnItemClickListner listner){
        this.listner = listner;
    }

    public CanBeWornAdapter()
    {
        this.list_cwb = new ArrayList<>();
    }

    public void addItem(ArrayList<Items> list_cwb){

        this.list_cwb = list_cwb;
    }

    @NonNull
    @Override
    public CBWViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.can_be_worn, viewGroup, false);
        return new CBWViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CBWViewHolder cbwViewHolder, int i)
    {
       cbwViewHolder.bind(list_cwb.get(i));
    }

    @Override
    public int getItemCount()
    {
        return list_cwb.size();
    }

    class CBWViewHolder extends RecyclerView.ViewHolder
   {
       TextView name;
       TextView bonus;

       public CBWViewHolder(@NonNull View itemView)
       {
           super(itemView);
           name = itemView.findViewById(R.id.cbw_name);
           bonus = itemView.findViewById(R.id.cbw_bonus);

           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if (listner != null){
                       int position = getAdapterPosition();
                       if (position != RecyclerView.NO_POSITION){
                           listner.onItemClick(v, position);
                       }
                   }
               }
           });

       }

       void bind(Items item)
       {
           name.setText(item.getName());
           bonus.setText("+" + String.valueOf(item.getBonus()));
       }

   }
}
