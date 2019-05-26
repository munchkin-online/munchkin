package com.example.sipliy.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sipliy.Activity.Dialog.MenuDialogActivity;
import com.example.sipliy.Activity.Dialog.RemoveCardsDialogActivity;
import com.example.sipliy.Activity.Dialog.SaleDialogActivity;
import com.example.sipliy.Adapter.PlayersGameAdapter;
import com.example.sipliy.Cards.Doors;
import com.example.sipliy.Cards.Interface.DoorsInterface;
import com.example.sipliy.Cards.Monster;
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
    private TextView strengthInBattle;

    private ImageView monsterImage;
    private ImageView sale;


    private GameInteraction gameInteraction;


    private static String TAG = "INGAME";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        try
        {
            Objects.requireNonNull(this.getSupportActionBar()).hide();
        }
        catch(NullPointerException e) {}

        setContentView(R.layout.activity_game);
        gameInteraction = new GameInteraction();    //создание управление игрой
        findViewById();
        update();
        PlayerInstances.getPlayer().addTreasures(4);
        PlayerInstances.getPlayer().addDoors(4);
        buildRecyclerView();

        View.OnClickListener clickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                switch (v.getId())
                {
                    case R.id.player_icon:
                        //search.setText("play");
                        startActivity(new Intent(GameActivity.this, InventoryActivity.class));
                        break;
                    case R.id.imageViewTreasures:
                        if(Treasures.getItem() == null)
                        {
                            Toast.makeText(GameActivity.this, "Deck is empty", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            PlayerInstances.getPlayer().addTreasures(Treasures.getItemCard());  //при нажатии на иконку сокровищ, в руку игрока добавляется сокровище
                        }
                        break;
                    case R.id.imageViewDoors:
                        if(Doors.getItem() == null)
                        {
                            Toast.makeText(GameActivity.this, "Deck is empty", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            final DoorsInterface item = Doors.getItemCard();
                            switch(item.getType())
                            {
                                case 1:
                                    Log.d(TAG, "onClick1: ");
                                    PlayerInstances.getPlayer().addDoors(item);
                                    break;
                                case 2:
                                    Log.d(TAG, "onClick2: ");
                                    PlayerInstances.getPlayer().addDoors(item);
                                    break;
                                case 3:
                                    Log.d(TAG, "onClick3: ");
                                    AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                                    builder.setCancelable(false);
                                    View view = LayoutInflater.from(GameActivity.this).inflate(R.layout.battledialog, null);
                                    monsterImage = view.findViewById(R.id.imageViewMonsterDialog);
                                    strengthInBattle = view.findViewById(R.id.textViewStrenght);

                                    final Monster monster = (Monster)item;

                                    strengthInBattle.setText(Integer.toString(PlayerInstances.getPlayer().getStrength()));
                                    monsterImage.setImageResource(monster.getIMAGE_ID());

                                    builder.setNegativeButton("Оступить", new DialogInterface.OnClickListener()
                                    {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which)
                                        {
                                            GameInteraction.leave(PlayerInstances.getPlayer());
                                        }
                                    })
                                            .setPositiveButton("Атаковать", new DialogInterface.OnClickListener()
                                            {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which)
                                                {
                                                    GameInteraction.battle(PlayerInstances.getPlayer(), monster);
                                                }
                                            });
                                    builder.setView(view);
                                    builder.show();

                                    break;
                                case 4:
                                    break;
                            }
                        }
                        break;
                    case R.id.sale:
                        sale();
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

    @Override
    protected void onResume()
    {
        super.onResume();
        update();
    }

    public void buildRecyclerView()
    {
        LinearLayoutManager layoutManagerPlayers = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        playersList.setLayoutManager(layoutManagerPlayers);
        playersAdapter = new PlayersGameAdapter();
        playersList.setAdapter(playersAdapter);
        player_icon = findViewById(R.id.player_icon);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(playersList.getContext(),
        layoutManagerPlayers.getOrientation());
        playersList.addItemDecoration(dividerItemDecoration);

        PlayerInstances.getPlayer().getDecks().buildRecyclerView(this, cardsList);
        playersAdapter.addPlayer(PlayerInstances.getOpponent(0));
        playersAdapter.addPlayer(PlayerInstances.getOpponent(1));
        playersAdapter.addPlayer(PlayerInstances.getOpponent(2));
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
    }

    @Override
    protected void onDestroy()
    {
        Treasures.update();
        Doors.update();
        PlayerInstances.getPlayer().resetItems();
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState)
    {
        Treasures.update();
        Doors.update();
        PlayerInstances.getPlayer().resetItems();
        super.onSaveInstanceState(outState, outPersistentState);
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
    private void update()
    {
        nameView.setText(PlayerInstances.getPlayer().getName());
        lvlView.setText(String.valueOf(PlayerInstances.getPlayer().getLevel()));
        strView.setText(String.valueOf(PlayerInstances.getPlayer().getStrength()));
    }
}
