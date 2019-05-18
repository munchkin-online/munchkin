package com.example.sipliy.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.view.View;
import android.widget.ImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.sipliy.Activity.Dialog.MenuDialogActivity;
import com.example.sipliy.Activity.Dialog.RemoveCardsDialogActivity;
import com.example.sipliy.Activity.Dialog.SaleDialogActivity;
import com.example.sipliy.Adapter.PlayersGameAdapter;
import com.example.sipliy.Cards.Treasures;
import com.example.sipliy.Data.PlayerInstances;
import com.example.sipliy.Interaction.GameInteraction;
import com.example.sipliy.R;

import java.util.Objects;

public class GameActivity extends AppCompatActivity
{

    private RecyclerView playersList;        //лист с игроками
    private PlayersGameAdapter playersAdapter;   //адаптер для листа
    private RecyclerView cardsList;


    private ImageView player_icon;  //иконка игрока
    private ImageView doorsView;    //иконка с дверьми
    private ImageView treasuresView;    //иконка с сокровищами
    private TextView nameView;
    private TextView lvlView;
    private TextView strView;
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

        PlayerInstances.getPlayer().addTreasures(4);
        PlayerInstances.getPlayer().addDoors(4);
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
                        //PlayerInstances.getPlayer().addDoors(Doors.getItemCard());
                        break;
                    case R.id.sale:
                        sale();
//                        String lvl = "Уровень: " + String.valueOf(PlayerInstances.getPlayer().getLevel());
//                        String pwr = "Сила: " + String.valueOf(PlayerInstances.getPlayer().getStrength());
//                        lvlView.setText(lvl);
//                        strView.setText(pwr);
                        //Toast.makeText(this, Integer.toString(PlayerInstances.getPlayer().getLevel()), Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };

        player_icon.setOnClickListener(clickListener);
        doorsView.setOnClickListener(clickListener);
        treasuresView.setOnClickListener(clickListener);
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

        PlayerInstances.getPlayer().getDecks().bildRecyclerView(this, cardsList);
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
        lvlView = findViewById(R.id.lvlValue);
        strView = findViewById(R.id.strValue);
        nameView.setText(PlayerInstances.getPlayer().getName());
        lvlView.setText(String.valueOf(PlayerInstances.getPlayer().getLevel()));
        strView.setText(String.valueOf(PlayerInstances.getPlayer().getStrength()));
    }

    @Override
    protected void onDestroy()
    {
        Treasures.update();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() //добавление взаимодействия с меню
    {
        MenuDialogActivity menuDialog = new MenuDialogActivity();
        menuDialog.show(getSupportFragmentManager(), "Menu");
    }

    public void sale()
    {
        SaleDialogActivity saleDialog = new SaleDialogActivity();
        saleDialog.setPlayerDecks(PlayerInstances.getPlayer().getDecks());
        saleDialog.setTextView(lvlView);
        saleDialog.setStr(strView);
        saleDialog.show(getSupportFragmentManager(), "Sale");
//        String lvl = "Уровень: " + String.valueOf(PlayerInstances.getPlayer().getLevel());
//        String pwr = "Сила: " + String.valueOf(PlayerInstances.getPlayer().getStrength());
//        lvlView.setText(lvl);
//        strView.setText(pwr);
    }

    public void remove()
    {
        RemoveCardsDialogActivity removeCardsDialogActivity = new RemoveCardsDialogActivity();
        removeCardsDialogActivity.setPlayerDecks(PlayerInstances.getPlayer().getDecks());
        removeCardsDialogActivity.show(getSupportFragmentManager(), "Remove");
    }
}
