package com.example.sipliy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class GameActivity extends AppCompatActivity {

    private RecyclerView playersList;        //лист с игрками
    private PlayersGameAdapter playersAdapter;   //адаптер для листа

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

        playersAdapter.addItem();
        playersAdapter.addItem();

    }

    public void bildRecyclerView()
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        playersList.setLayoutManager(layoutManager);
        playersAdapter = new PlayersGameAdapter();
        playersList.setAdapter(playersAdapter);
    }
}
