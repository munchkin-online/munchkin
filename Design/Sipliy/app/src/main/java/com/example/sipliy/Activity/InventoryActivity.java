package com.example.sipliy.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.sipliy.Adapter.CanBeWornAdapter;
import com.example.sipliy.Cards.Items;
import com.example.sipliy.Cards.Treasures;
import com.example.sipliy.Data.PlayerInstances;
import com.example.sipliy.Player.Player;
import com.example.sipliy.R;

import java.util.ArrayList;
import java.util.List;

public class InventoryActivity extends AppCompatActivity {

    private ImageView exitFromInventory;
    private RecyclerView canBeWornList;        //лист со шмотками
    private CanBeWornAdapter canBeWornAdapter;

    private TextView name_hat;
    private TextView strView;
    private TextView lvlView;
    private TextView raceView;
    private TextView classView;
    private TextView sexView;

    private ImageView im_head;
    private ImageView im_r_hand;
    private ImageView im_l_hand;
    private ImageView im_boots;
    private ImageView im_body;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        refreshInform();

        ArrayList<Items> list_cwb = PlayerInstances.getPlayer().getDecks().getItems();
        View.OnClickListener clickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                switch (v.getId())
                {
                    case R.id.exitFromInventory:
                        finish();
                        break;
                }
            }
        };

        exitFromInventory.setOnClickListener(clickListener);

        LinearLayoutManager layoutManagerCBW = new LinearLayoutManager(this);
        canBeWornList.setLayoutManager(layoutManagerCBW);
        canBeWornAdapter = new CanBeWornAdapter(this);
        canBeWornAdapter.setOnItemClickListner(new CanBeWornAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, final int position)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(InventoryActivity.this);

                builder.setView(R.layout.inventorydialog)
                        .setCancelable(false)
                        .setPositiveButton("Надеть", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                setItemsOnPlayer(PlayerInstances.getPlayer().getDecks().getItems().get(position).getIMAGE_ID(),
                                                PlayerInstances.getPlayer().getDecks().getItems().get(position).getItemType());
                            }
                        })
                        .setNegativeButton("Назад", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                            }
                        });

            }
        });
        canBeWornList.setAdapter(canBeWornAdapter);
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    private void refreshInform()
    {
        name_hat = findViewById(R.id.char_hat);
        strView = findViewById(R.id.tv_strengthValue);
        lvlView = findViewById(R.id.tv_levelValue);
        raceView = findViewById(R.id.tv_raceType);
        classView = findViewById(R.id.tv_classType);
        sexView = findViewById(R.id.tv_sexType);
        exitFromInventory = findViewById(R.id.exitFromInventory);
        canBeWornList = findViewById(R.id.cbw_list);

        im_body = findViewById(R.id.im_body);
        im_boots = findViewById(R.id.im_boots);
        im_head = findViewById(R.id.im_head);
        im_l_hand = findViewById(R.id.im_l_hand);
        im_r_hand = findViewById(R.id.im_r_hand);

        name_hat.setText(PlayerInstances.getPlayer().getName());
        strView.setText(String.valueOf(PlayerInstances.getPlayer().getStrength()));
        lvlView.setText(String.valueOf(PlayerInstances.getPlayer().getLevel()));
        switch (PlayerInstances.getPlayer().getRace())
        {
            case 1:
                raceView.setText("Человек");
                break;
            case 2:
                raceView.setText("Дворф");
                break;
            case 3:
                raceView.setText("Эльф");
                break;
            case 4:
                raceView.setText("Хафлинг");
                break;
        }
        switch (PlayerInstances.getPlayer().get_Class())
        {
            case 1:
                classView.setText("Нет");
                break;
            case 2:
                classView.setText("Клирик");
                break;
            case 3:
                classView.setText("Волшебник");
                break;
            case 4:
                classView.setText("Воин");
                break;
            case 5:
                classView.setText("Вор");
                break;
        }
        if(PlayerInstances.getPlayer().getSex() == 1)
            sexView.setText("Мужской");
        else sexView.setText("Женский");
    }

    private void setItemsOnPlayer(int IMAGE_ID, int type)
    {
        switch(type)
        {
            case 1:
                im_head.setImageResource(IMAGE_ID);
                break;
            case 2:
                im_body.setImageResource(IMAGE_ID);
                break;
            case 3:
                im_boots.setImageResource(IMAGE_ID);
                break;
            case 4:
                im_l_hand.setImageResource(IMAGE_ID);
                break;
            case 5:
                im_r_hand.setImageResource(IMAGE_ID);
                break;
        }
    }
}
