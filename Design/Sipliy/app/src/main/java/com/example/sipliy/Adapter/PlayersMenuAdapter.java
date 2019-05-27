package com.example.sipliy.Adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sipliy.MenuPlayer;
import com.example.sipliy.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PlayersMenuAdapter extends RecyclerView.Adapter<PlayersMenuAdapter.PlayerViewHolder>{

    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }



    public void setOnItemClickListner(OnItemClickListener listner){
        this.listener = listener;
    }


    private List<MenuPlayer> playersList = new ArrayList<>();

    public void setItems(Collection<MenuPlayer> plaers){
        playersList.addAll(plaers);
        notifyDataSetChanged();
    }

    public void clearItems(){
        playersList.clear();
        notifyDataSetChanged();
    }

    public void addItem(String str){
        if(!thereIs(str)){
            playersList.add(new MenuPlayer(str));
            notifyDataSetChanged();
        }
    }

    public void addItemPlay(String str){
        if (!thereIs(str)){
            playersList.add(new MenuPlayer(str, true));
            Sort();
            notifyDataSetChanged();
        }
    }

    public void isPlay(int i){
        playersList.get(i).setBoolStatus(true);
        Sort();
        notifyDataSetChanged();
    }

    public void toIvite(String name){
        boolean f = false;
        for (MenuPlayer item : playersList){
            if (item.getName().equals(name)){
                isPlay(playersList.indexOf(item));
                f = true;
            }
        }
        if (!f){
            addItemPlay(name);
        }
    }

    public boolean thereIs(String name){
        boolean f = false;
        for(MenuPlayer item : playersList){
            if (item.getName().equals(name)){
                f = true;
            }
        }

        return f;
    }

    public int getSize(){
        int count = 0;
        for (int i = 0; i < playersList.size(); i++) {
            if(playersList.get(i).isBoolStatus()) count++;
        }
        return count;
    }

    public String getName(int i){
        if (playersList.get(i).isBoolStatus()){
            return playersList.get(i).getName();
        }
        else return "";
    }

    public void Sort(){
        int startIndex = 0;
        int endIndex = playersList.size() - 1;
        doSort(startIndex, endIndex);
    }
    public void doSort(int start, int end) {
        if (start >= end)
            return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (Boolean.compare(playersList.get(i).isBoolStatus(), false) >= Boolean.compare(playersList.get(cur).isBoolStatus(), false))) {
                i++;
            }
            while (j > cur && (Boolean.compare(playersList.get(cur).isBoolStatus() ,false) >= Boolean.compare(playersList.get(j).isBoolStatus(), false))) {
                j--;
            }
            if (i < j) {
                MenuPlayer temp = playersList.get(i);
                playersList.set(i,playersList.get(j));
                playersList.set(j,temp);
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        doSort(start, cur);
        doSort(cur+1, end);
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.recycleview_item_players_menu, viewGroup, false);
        PlayerViewHolder viewHolder = new PlayerViewHolder(view, listener);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder playerViewHolder, int i) {
        playerViewHolder.bind(playersList.get(i));
    }

    @Override
    public int getItemCount() {
        return playersList.size();
    }

    class PlayerViewHolder extends RecyclerView.ViewHolder{

        TextView listNames;
        TextView listStatus;
        ImageView plus;

        public PlayerViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            listNames = itemView.findViewById(R.id.tv_name);
            listStatus = itemView.findViewById(R.id.tv_status);
            plus = itemView.findViewById(R.id.imageView_plus);

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }

        void bind(MenuPlayer plaer){
            listNames.setText(plaer.getName());
            if (plaer.isBoolStatus()){
                listStatus.setText("play");
                listStatus.setTextColor(Color.GREEN);
            }
        }
    }
}
