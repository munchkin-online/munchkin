package com.example.sipliy.Activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sipliy.Adapter.CanBeWornAdapter;
import com.example.sipliy.Cards.Items;
import com.example.sipliy.Data.PlayerInstances;
import com.example.sipliy.Player.Player;
import com.example.sipliy.R;

import java.util.ArrayList;

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

    private ArrayList<ImageView> image = new ArrayList<>();

    private static String TAG = "Inventory";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        findViewByID();
        update();

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

        canBeWornAdapter = new CanBeWornAdapter(this, list_cwb);
        canBeWornAdapter.setOnItemClickListener(new CanBeWornAdapter.ClickListener()
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
                                setItemsOnPlayer(PlayerInstances.getPlayer().getDecks().getItems().get(position).getICON_ID(),
                                                PlayerInstances.getPlayer().getDecks().getItems().get(position).getItemType(),
                                                canBeWornAdapter.getItem(position));
                            }
                        })
                        .setNegativeButton("Назад", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                            }
                        });
                builder.show();

            }
        });

        canBeWornList.setAdapter(canBeWornAdapter);
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    private void findViewByID()
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

        image.add(im_body);
        image.add(im_boots);
        image.add(im_head);
        image.add(im_l_hand);
        image.add(im_r_hand);
    }

    private void setItemsOnPlayer(int IMAGE_ID, int type, Items item)
    {
        switch(type)
        {
            case 1:
                im_head.setImageResource(IMAGE_ID);
                PlayerInstances.getPlayer().setHelmet(item);
                update();
                break;
            case 2:
                im_body.setImageResource(IMAGE_ID);
                PlayerInstances.getPlayer().setArmor(item);
                update();
                break;
            case 3:
                im_boots.setImageResource(IMAGE_ID);
                PlayerInstances.getPlayer().setShoes(item);
                update();
                break;
            case 4:
<<<<<<< HEAD
                im_l_hand.setImageResource(IMAGE_ID);
                PlayerInstances.getPlayer().setLeftHand(item);
                update();
=======
>>>>>>> Design
                break;
            case 5:
                im_r_hand.setImageResource(IMAGE_ID);
                PlayerInstances.getPlayer().setRightHand(item);
                update();
                break;
        }
    }
    @SuppressLint("SetTextI18n")
    private void update()
    {
        name_hat.setText(PlayerInstances.getPlayer().getName());
        strView.setText(Integer.toString(PlayerInstances.getPlayer().getStrength()));
        lvlView.setText(Integer.toString(PlayerInstances.getPlayer().getLevel()));


        for(int i = 0; i < 5; i++)
        {
            if(checkItemsOnPlayer(PlayerInstances.getPlayer().getItem(i)))
            {
                updateImage(PlayerInstances.getPlayer().getItem(i), i);
            }
        }

        switch(PlayerInstances.getPlayer().getRace())
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
        switch(PlayerInstances.getPlayer().get_Class())
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
        else
            sexView.setText("Женский");
    }
    private boolean checkItemsOnPlayer(Items item)
    {
        return item.getBonus() != 0;
    }
    private void updateImage(Items item, int index)
    {
        switch(index)
        {
            case 0:
                im_boots.setImageResource(item.getICON_ID());
                break;
            case 1:
                im_head.setImageResource(item.getICON_ID());
                break;
            case 2:
                im_body.setImageResource(item.getICON_ID());
                break;
            case 3:
                im_l_hand.setImageResource(item.getICON_ID());
                break;
            case 4:
                im_r_hand.setImageResource(item.getICON_ID());
        }
    }
}
