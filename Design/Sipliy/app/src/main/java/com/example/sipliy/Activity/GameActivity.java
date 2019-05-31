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
import android.widget.Button;
import android.widget.ImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sipliy.Activity.Dialog.BattleDialog;
import com.example.sipliy.Activity.Dialog.MenuDialogActivity;
import com.example.sipliy.Activity.Dialog.RemoveCardsDialogActivity;
import com.example.sipliy.Activity.Dialog.SaleDialogActivity;
import com.example.sipliy.Adapter.PlayersGameAdapter;
import com.example.sipliy.AsyncTasks.AsyncTaskCheckInviteResult;
import com.example.sipliy.AsyncTasks.AsyncTaskCheckIvite;
import com.example.sipliy.AsyncTasks.AsyncTaskCheckNumber;
import com.example.sipliy.AsyncTasks.AsyncTaskCheckPlay;
import com.example.sipliy.AsyncTasks.AsyncTaskEndTurn;
import com.example.sipliy.Cards.Doors;
import com.example.sipliy.Cards.Interface.DoorsInterface;
import com.example.sipliy.Cards.Monster;
import com.example.sipliy.Cards.Treasures;
import com.example.sipliy.Data.PlayerInstances;
import com.example.sipliy.Interaction.GameInteraction;
import com.example.sipliy.R;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity
{

    private RecyclerView playersList;        //лист с игроками
    public static PlayersGameAdapter playersAdapter;   //адаптер для листа
    private RecyclerView cardsList;


    private ImageView player_icon;  //иконка игрока
    private ImageView doorsView;    //иконка с дверьми
    private ImageView treasuresView;    //иконка с сокровищами
    private TextView nameView;
    private TextView lvlView;
    private TextView strView;
    private TextView strengthInBattle;

    private Button endTurn; //кнопка конца хода
    public static Timer gameTimer;

    private ImageView monsterImage;
    private ImageView sale;

    private int fightAmount = 0; //количество битв
    private int doorsAmount = 0; //количетво взятых карт дверей


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
//        PlayerInstances.getPlayer().addTreasures(4);
//        PlayerInstances.getPlayer().addDoors(4);
        buildRecyclerView();



        View.OnClickListener clickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                switch (v.getId())
                {
                    case R.id.player_icon:
                        if (PlayerInstances.getPlayer().isCanPlay()){
                            startActivity(new Intent(GameActivity.this, InventoryActivity.class));
                        }
                        break;
                    case R.id.imageViewTreasures:
                        if (PlayerInstances.getPlayer().isCanPlay()){
//                            if(Treasures.getItem() == null)
//                            {
//                                Toast.makeText(GameActivity.this, "Deck is empty", Toast.LENGTH_SHORT).show();
//                            }
//                            else
//                            {
//                                PlayerInstances.getPlayer().addTreasures(Treasures.getItemCard());  //при нажатии на иконку сокровищ, в руку игрока добавляется сокровище
//                            }
                            Toast.makeText(getApplicationContext(), "Вытяните дверь для получения сокровища", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case R.id.imageViewDoors:
                        if (PlayerInstances.getPlayer().isCanPlay()){
                            if(Doors.getItem() == null)
                            {
                                Toast.makeText(GameActivity.this, "Deck is empty", Toast.LENGTH_SHORT).show();
                            }
                            else if(doorsAmount == 0 || doorsAmount == 1)
                            {
                                doorsAmount++;
                                final DoorsInterface item = Doors.getItemCard();
                                assert item != null;
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
                                        if(fightAmount == 0)
                                        {
                                            Log.d(TAG, "onClick3: ");
                                            fightAmount++;
                                            doorsAmount = 2;
                                            BattleDialog battleDialog = new BattleDialog();
                                            battleDialog.setItem(item);
                                            battleDialog.show(getSupportFragmentManager(), "Notice Data");
                                        }
                                        else
                                        {
                                            PlayerInstances.getPlayer().addDoors(item);
                                        }
                                        break;
                                    case 4:
                                        break;
                                }
                            }
                            else
                            {
                                Toast.makeText(GameActivity.this, "Вы исчерпали все попытки", Toast.LENGTH_SHORT).show();
                            }
                        }
                        break;
                    case R.id.sale:
                        if (PlayerInstances.getPlayer().isCanPlay()){
                            sale();
                        }
                        break;
                    case R.id.btn_endTurn:
                        if (PlayerInstances.getPlayer().isCanPlay()){
                            doorsAmount = 0;
                            fightAmount = 0;
                            AsyncTaskEndTurn asyncTaskEndTurn = new AsyncTaskEndTurn(getApplicationContext());
                            asyncTaskEndTurn.execute();
                            gameTimer = new Timer();
                            gameTimer.schedule(new UpdateTimeTask(), 0, 10000);
                        }
                        break;
                }
            }
        };
        endTurn.setOnClickListener(clickListener);
        player_icon.setOnClickListener(clickListener);
        doorsView.setOnClickListener(clickListener);
        treasuresView.setOnClickListener(clickListener);
        sale.setOnClickListener(clickListener);
        if (!PlayerInstances.getPlayer().isCanPlay()){
            gameTimer = new Timer();
            gameTimer.schedule(new UpdateTimeTask(), 0, 10000);
            playersAdapter.notifyDataSetChanged();
        }
    }

    class UpdateTimeTask extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AsyncTaskCheckNumber asyncTaskCheckNumber = new AsyncTaskCheckNumber(getApplicationContext());
                    asyncTaskCheckNumber.execute();
                }
            });

        }
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
    }
    private void findViewById()
    {
        doorsView = findViewById(R.id.imageViewDoors);
        treasuresView = findViewById(R.id.imageViewTreasures);
        playersList = findViewById(R.id.recyclerViewGamePlayers);
        cardsList = findViewById(R.id.recyclerViewGameCards);
        sale = findViewById(R.id.sale);
        endTurn = findViewById(R.id.btn_endTurn);
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
