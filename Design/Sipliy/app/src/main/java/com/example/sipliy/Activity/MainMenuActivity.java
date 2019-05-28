package com.example.sipliy.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sipliy.Activity.Dialog.PlayerDialogActivity;
import com.example.sipliy.Adapter.PlayersMenuAdapter;
import com.example.sipliy.AsyncTasks.AsyncTaskCheckInviteResult;
import com.example.sipliy.AsyncTasks.AsyncTaskCheckIvite;
import com.example.sipliy.AsyncTasks.AsyncTaskStatus;
import com.example.sipliy.Data.MenuPlayers;
import com.example.sipliy.Data.PlayerInstances;
import com.example.sipliy.Player.Player;
import com.example.sipliy.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainMenuActivity extends AppCompatActivity
{

    private RecyclerView playersList;        //лист с игрками

    public static String[] Players = new String[3];
    public static int SizePlayers;
    Timer timer;

    private EditText search;      // строка поиска
    private ImageView plusSearch; //кнопка плюсик в строке поиска
    private Button play;
    private Button settings;
    private Button exit;

    private boolean status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e)
        {

        }
        setContentView(R.layout.activity_main_menu);



        search = findViewById(R.id.search);
        plusSearch = findViewById(R.id.imageView_search_plus);
        playersList = findViewById(R.id.recyclerView);
        play = findViewById(R.id.button_play);
        settings = findViewById(R.id.button_settings);
        exit = findViewById(R.id.button_exit);

        View.OnClickListener clickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                switch(v.getId())
                {
                    case R.id.button_play:
                        //search.setText("play");
                        transfer();
                        for(int i = 0; i < SizePlayers; i++)    //добавление игроков в класс PlayerInstances
                        {
                            PlayerInstances.addPlayer(new Player(Players[i]));
                        }
                        PlayerDialogActivity playerDialogActivity = new PlayerDialogActivity(); //открытие диалового окна с просьбой ввести пол и имя
                        playerDialogActivity.show(getSupportFragmentManager(), "NoticeData");

                        //startActivity(new Intent(MainMenuActivity.this, GameActivity.clas));
                        break;
                    case R.id.button_exit:
                        search.setText("exit");
                        finish();
                        break;
                    case R.id.button_settings:
                        startActivity(new Intent(MainMenuActivity.this, SettingsActivity.class));
                        break;
                    case R.id.imageView_search_plus:
                        status = false;
                        if (String.valueOf(search.getText()) != PlayerInstances.getPlayer().getName())
                        {
                            AsyncTaskStatus asyncTaskStatus = new AsyncTaskStatus(String.valueOf(search.getText()), getApplicationContext());
                            asyncTaskStatus.execute();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Вы приглашете самого себя", Toast.LENGTH_LONG).show();
                        }
                        Log.d("asyncTask", "status");
                        search.setText(null);
                        break;
                }
                MenuPlayers.getPlayersAdapter().update();
            }
        };

        plusSearch.setOnClickListener(clickListener);
        play.setOnClickListener(clickListener);
        exit.setOnClickListener(clickListener);
        settings.setOnClickListener(clickListener);

        /*plusSearch.setOnClickListener(new View.OnClickListener() {   //нажатие кнопки плюсика в строке поиска
            @Override
            public void onClick(View v) {
                                                                                  //ДЛЯ ФЕДИ И ВАНИ!!!!!! ДОБАВИТЬ ОТПРАВКУ ПРИГЛАШЕНИЯ ДРУГА


                if (true){                                                        //ДЛЯ ФЕДИ И ВАНИ!!!!!! ИСПРАВИТЬ УСЛОВИЕ (ОТВЕТ ОТ ДРУГА)
                    playersAdapter.addItem(String.valueOf(search.getText()));
                    search.setText("");
//                    invite(String.valueOf(search.getText()));    // проверка функции инвайт в соло режиме
//                    search.setText("");
                }
                else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Player is offline", Toast.LENGTH_SHORT);  //уведомление что игрок не в сети
                    toast.setGravity(Gravity.BOTTOM, 0, 0);
                    toast.show();
                }
            }
        });*/


        bildRecyclerView(); //cборка листа
        timer = new Timer();
        timer.schedule(new UpdateTimeTask(), 0, 20000);
    }
    class UpdateTimeTask extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d("scroll", "onScrollStateChanged");
                    AsyncTaskCheckIvite asyncTaskCheckIvite = new AsyncTaskCheckIvite(MainMenuActivity.this);
                    asyncTaskCheckIvite.execute();
                    if (PlayerInstances.getPlayer().isInvite() == true){
                        AsyncTaskCheckInviteResult asyncTaskCheckInviteResult = new AsyncTaskCheckInviteResult(MainMenuActivity.this);
                        asyncTaskCheckInviteResult.execute();
                        PlayerInstances.getPlayer().setInvite(false);
                    }
                }
            });

        }
    }

    public void bildRecyclerView()
    {
        MenuPlayers.buildRecyclerView(this, playersList);
        playersList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(-1)) {

                }
            }
        });
    }


    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }

    public void transfer()
    {
        SizePlayers = MenuPlayers.getSize();
        for (int i = 0; i < SizePlayers; i++)
        {
            if (MenuPlayers.getName(i) != "")
            {
                Players[i] = MenuPlayers.getName(i);
            }
        }
    }
}
