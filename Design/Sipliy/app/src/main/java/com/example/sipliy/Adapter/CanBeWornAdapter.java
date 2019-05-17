package com.example.sipliy.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class CanBeWornAdapter extends RecyclerView.Adapter<CanBeWornAdapter.ViewHolder>
{
    private static ClickListener clickListener;
    private ArrayList<Items> list_cwb;
    private Context context;
    private LayoutInflater layoutInflater;
    private static String TAG = "canBe";

    public CanBeWornAdapter(Context context, ArrayList<Items> list)
    {
        this.list_cwb = list;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = layoutInflater.inflate(R.layout.can_be_worn, viewGroup, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder cbwViewHolder, int i)
    {
       cbwViewHolder.bonus.setText(Integer.toString(list_cwb.get(i).getBonus()));
       cbwViewHolder.name.setText(list_cwb.get(i).getName());
    }

    @Override
    public int getItemCount()
    {
        return list_cwb.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private final TextView name;
        private final TextView bonus;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            name = itemView.findViewById(R.id.cbw_name);
            bonus = itemView.findViewById(R.id.cbw_bonus);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v)
        {
            Log.d(TAG, "onClick: " + getAdapterPosition() + " " + v.getId());
            clickListener.onItemClick(v, getAdapterPosition());
        }
    }
    public interface ClickListener
    {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(ClickListener clickListener)
    {
        CanBeWornAdapter.clickListener = clickListener;
    }
}