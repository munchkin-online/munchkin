package com.example.sipliy.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.sipliy.Adapter.CanBeWornAdapter;
import com.example.sipliy.Cards.Items;
import com.example.sipliy.Cards.Treasures;
import com.example.sipliy.R;

import java.util.ArrayList;
import java.util.List;

public class InventoryActivity extends AppCompatActivity {

    private ImageView exitFromInventory;
    private RecyclerView canBeWornList;        //лист со шмотками
    private CanBeWornAdapter canBeWornAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

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
}
