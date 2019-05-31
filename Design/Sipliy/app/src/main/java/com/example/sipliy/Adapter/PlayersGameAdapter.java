package com.example.sipliy.Adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sipliy.MenuPlayer;
import com.example.sipliy.Player.Player;
import com.example.sipliy.R;

import java.util.ArrayList;
import java.util.List;

public class PlayersGameAdapter extends RecyclerView.Adapter<PlayersGameAdapter.PlayerViewHolder>
{
    private List<Player> playerList = new ArrayList<>();
    private OnItemClickListener listener;

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }

    public void setOnItemClickListner(OnItemClickListener listner)
    {
        this.listener = listener;
    }

    public void update(){
        notifyDataSetChanged();
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
    public void addPlayer(Player player)
    {
        playerList.add(player);
        notifyDataSetChanged();
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
        View view = inflater.inflate(R.layout.recycleview_item_players_game, viewGroup, false);
        return new PlayerViewHolder(view, listener);

    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder playerViewHolder, int i)
    {
        playerViewHolder.bind(playerList.get(i));
    }

    @Override
    public int getItemCount()
    {
        return playerList.size();
    }

    class PlayerViewHolder extends RecyclerView.ViewHolder
    {
        ImageView plus;
        TextView name;
        TextView level;
        TextView strength;

        public PlayerViewHolder(View itemView, final OnItemClickListener listener)
        {
            super(itemView);

            name = itemView.findViewById(R.id.Name);
            level = itemView.findViewById(R.id.Level);
            strength = itemView.findViewById(R.id.Strength);
            plus = (ImageView)itemView.findViewById(R.id.imageView5);

//            plus.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (listner != null){
//                        int position = getAdapterPosition();
//                        if (position != RecyclerView.NO_POSITION){
//                            listner.onItemClick(position);
//                        }
//                    }
//                }
//            });
        }

        void bind(Player player){
            plus.setImageResource(R.drawable.player_icon);
            name.setText(String.valueOf(player.getName()));
            level.setText(Integer.toString(player.getLevel()));
            strength.setText(Integer.toString(player.getStrength()));
        }
    }
}

