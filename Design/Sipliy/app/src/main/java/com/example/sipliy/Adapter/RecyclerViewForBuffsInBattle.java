package com.example.sipliy.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.sipliy.Activity.Dialog.BattleDialog;
import com.example.sipliy.Cards.Buff;
import com.example.sipliy.R;

import java.util.ArrayList;

public class RecyclerViewForBuffsInBattle extends RecyclerView.Adapter<RecyclerViewForBuffsInBattle.ViewHolder>
{
    private static RecyclerViewForBuffsInBattle.ClickListener clickListener;
    private ArrayList<Buff> buffsList;
    private Context context;
    private LayoutInflater layoutInflater;

    public RecyclerViewForBuffsInBattle(Context context, ArrayList<Buff> list)
    {
        this.buffsList = list;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public Buff getItem(int position)
    {
        return buffsList.get(position);
    }
    @NonNull
    @Override
    public RecyclerViewForBuffsInBattle.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = layoutInflater.inflate(R.layout.buffslist, viewGroup, false);
        return new RecyclerViewForBuffsInBattle.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewForBuffsInBattle.ViewHolder viewHolder, final int i)
    {
        viewHolder.checkBox.setText(buffsList.get(i).getName() + " - " + buffsList.get(i).getValue());
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                {
                    BattleDialog.buffValue.add(buffsList.get(i).getValue());
                }
                else
                {
                    for(int j = 0; j < BattleDialog.buffValue.size(); j++)
                    {
                        if(BattleDialog.buffValue.get(j) == buffsList.get(i).getValue())
                        {
                            BattleDialog.buffValue.remove(j);
                        }
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return buffsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public final CheckBox checkBox;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBoxForBuffList);
            checkBox.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            clickListener.onItemClick(v, getAdapterPosition());
        }
    }
    public interface ClickListener
    {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(RecyclerViewForBuffsInBattle.ClickListener clickListener)
    {
        RecyclerViewForBuffsInBattle.clickListener = clickListener;
    }
}