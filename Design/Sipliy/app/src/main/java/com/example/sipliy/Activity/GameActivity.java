package com.example.sipliy.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sipliy.Adapter.CardsGameAdapter;
import com.example.sipliy.Adapter.PlayersGameAdapter;
import com.example.sipliy.Cards.Doors;
import com.example.sipliy.Cards.Treasures;
import com.example.sipliy.Data.PlayerInstances;
import com.example.sipliy.Interaction.GameInteraction;
import com.example.sipliy.Player.Player;
import com.example.sipliy.R;

import java.util.Objects;

import static com.example.sipliy.Activity.MainMenuActivity.Players;
import static com.example.sipliy.Activity.MainMenuActivity.SizePlayers;

public class GameActivity extends AppCompatActivity
{

    private RecyclerView playersList;        //лист с игроками
    private PlayersGameAdapter playersAdapter;   //адаптер для листа
    private RecyclerView cardsList;
    private CardsGameAdapter cardsAdapter;

    private ImageView player_icon;  //иконка игрока
    private ImageView doorsView;    //иконка с дверьми
    private ImageView treasuresView;    //иконка с сокровищами
    private TextView nameView;
    private TextView lvlView;
    private TextView pwrView;
    private ImageView sale;

    private GameInteraction gameInteraction;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        try
        {
            Objects.requireNonNull(this.getSupportActionBar()).hide();
        }
        catch(NullPointerException e)
        {

        }

        setContentView(R.layout.activity_game);

        gameInteraction = new GameInteraction();    //создание управление игрой

        findViewById();

        bildRecyclerView();

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.player_icon:
                        //search.setText("play");
                        startActivity(new Intent(GameActivity.this, InventoryActivity.class));
                        break;
                    case R.id.imageViewTreasures:
                        PlayerInstances.getPlayer().addTreasures(Treasures.getItemCard());  //при нажатии на иконку сокровищ, в руку игрока добавляется сокровище
                        break;
                    case R.id.imageViewDoors:
                        PlayerInstances.getPlayer().addDoors(Doors.getItemCard());
                        break;
                    case R.id.sale:
                        sale();
                        break;
                }
            }
        };

        player_icon.setOnClickListener(clickListener);
        sale.setOnClickListener(clickListener);

//        for (int i = 0; i < SizePlayers; i++) {
//            playersAdapter.addItem(Players[i]);
//        }

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
        playersAdapter.addPlayer(PlayerInstances.getOpponent_1());
        playersAdapter.addPlayer(PlayerInstances.getOpponent_2());
        playersAdapter.addPlayer(PlayerInstances.getOpponent_3());
    }
    private void findViewById()
    {
        doorsView = findViewById(R.id.imageViewDoors);
        treasuresView = findViewById(R.id.imageViewTreasures);
        playersList = findViewById(R.id.recyclerViewGamePlayers);
        cardsList = findViewById(R.id.recyclerViewGameCards);
        sale = findViewById(R.id.sale);
        nameView = findViewById(R.id.nameView);
        lvlView = findViewById(R.id.lvlView);
        pwrView = findViewById(R.id.pwrView);
        nameView.setText(PlayerInstances.getPlayer().getName());
        String lvl = "Уровень: " + String.valueOf(PlayerInstances.getPlayer().getLevel());
        String pwr = "Сила: " + String.valueOf(PlayerInstances.getPlayer().getStrength());
        lvlView.setText(lvl);
        pwrView.setText(pwr);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() //добавление взаимодействия с меню
    {
        MenuDialogActivity menuDialog = new MenuDialogActivity();
        menuDialog.show(getSupportFragmentManager(), "Menu");
    }

    public void sale(){
        SaleDialogActivity saleDialog = new SaleDialogActivity();
        saleDialog.show(getSupportFragmentManager(), "Sale");
    }
}
