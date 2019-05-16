package com.example.sipliy.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.sipliy.Adapter.CanBeWornAdapter;
import com.example.sipliy.R;

public class InventoryActivity extends AppCompatActivity {

    private ImageView exitFromInventory;
    private RecyclerView canBeWornList;        //лист со шмотками
    private CanBeWornAdapter canBeWornAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        exitFromInventory = findViewById(R.id.exitFromInventory);
        canBeWornList = findViewById(R.id.cbw_list);

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
        canBeWornAdapter = new CanBeWornAdapter();
        canBeWornList.setAdapter(canBeWornAdapter);
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
