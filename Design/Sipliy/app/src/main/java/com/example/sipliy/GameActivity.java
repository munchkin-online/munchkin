package com.example.sipliy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.util.Size;
import android.view.View;
import android.widget.ImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import static com.example.sipliy.MainMenuActivity.Players;
import static com.example.sipliy.MainMenuActivity.SizePlayres;

public class GameActivity extends AppCompatActivity {

    private RecyclerView playersList;        //лист с игрками
    private PlayersGameAdapter playersAdapter;   //адаптер для листа
    private RecyclerView cardsList;
    private CardsGameAdapter cardsAdapter;

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

        playersList = findViewById(R.id.recyclerViewGamePlayers);
        cardsList = findViewById(R.id.recyclerViewGameCards);
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

        for (int i = 0; i < SizePlayres; i++) {
            playersAdapter.addItem(Players[i]);
        }

        cardsAdapter.addItem();
        cardsAdapter.addItem();
        cardsAdapter.addItem();
        cardsAdapter.addItem();
        cardsAdapter.addItem();
        cardsAdapter.addItem();
    }

    public void bildRecyclerView()
    {
        LinearLayoutManager layoutManagerPlayers = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        playersList.setLayoutManager(layoutManagerPlayers);
        playersAdapter = new PlayersGameAdapter();
        playersList.setAdapter(playersAdapter);
        player_icon = findViewById(R.id.player_icon);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(playersList.getContext(),
                layoutManagerPlayers.getOrientation());
        playersList.addItemDecoration(dividerItemDecoration);

        LinearLayoutManager layoutManagerCards = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        cardsList.setLayoutManager(layoutManagerCards);
        cardsAdapter = new CardsGameAdapter();
        cardsList.setAdapter(cardsAdapter);
        //player_icon.setOnClickListener(clickListener);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
