package com.example.sipliy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class GameActivity extends AppCompatActivity {

    private RecyclerView playersList;        //лист с игрками
    private PlayersGameAdapter playersAdapter;   //адаптер для листа

    private ImageView player_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e)
        {

        }
        setContentView(R.layout.activity_game);

        playersList = findViewById(R.id.recyclerViewGame);
        bildRecyclerView();

//        View.OnClickListener clickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()) {
//                    case R.id.player_icon:
//                        //search.setText("play");
//                        startActivity(new Intent(GameActivity.this, InventoryActivity.class));
//                        break;
//                }
//            }
//        };
//
//        player_icon.setOnClickListener(clickListener);

        playersAdapter.addItem();
        playersAdapter.addItem();

    }

    public void bildRecyclerView()
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        playersList.setLayoutManager(layoutManager);
        playersAdapter = new PlayersGameAdapter();
        playersList.setAdapter(playersAdapter);
        player_icon = findViewById(R.id.player_icon);
    }
}
