package com.example.sipliy.Activity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        refreshInform();

        exitFromInventory = findViewById(R.id.exitFromInventory);
        canBeWornList = findViewById(R.id.cbw_list);
        ArrayList<Items> list_cwb = new ArrayList<>();

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

        int tmp_size = Treasures.getSize();
        for(int i = 0; i < tmp_size; i++)
        {
            list_cwb.add(Treasures.getItemCard());
        }

        exitFromInventory.setOnClickListener(clickListener);

        LinearLayoutManager layoutManagerCBW = new LinearLayoutManager(this);
        canBeWornList.setLayoutManager(layoutManagerCBW);
        canBeWornAdapter = new CanBeWornAdapter();
        canBeWornAdapter.addItem(list_cwb);
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

        name_hat.setText(PlayerInstances.getPlayer().getName());
        strView.setText(String.valueOf(PlayerInstances.getPlayer().getStrength()));
        lvlView.setText(String.valueOf(PlayerInstances.getPlayer().getLevel()));
        switch (PlayerInstances.getPlayer().getRace()){
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
        switch (PlayerInstances.getPlayer().getClas())
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
}
