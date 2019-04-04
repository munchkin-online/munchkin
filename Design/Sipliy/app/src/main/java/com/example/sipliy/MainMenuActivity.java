package com.example.sipliy;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainMenuActivity extends AppCompatActivity {

    private RecyclerView playersList;        //лист с игрками
    private PlayersMenuAdapter playersAdapter;   //адаптер для листа

    private EditText search;      // строка поиска
    private ImageView plusSearch; //кнопка плюсик в строке поиска
    private Button play;
    private Button settings;
    private Button exit;

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
                        startActivity(new Intent(MainMenuActivity.this, GameActivity.class));
                        break;
                    case R.id.button_exit:
                        search.setText("exit");
                        finish();
                        break;
                    case R.id.button_settings:
                        startActivity(new Intent(MainMenuActivity.this, SettingsActivity.class));
                        break;
                    case R.id.imageView_search_plus:
                        if (true)
                        {
                            playersAdapter.addItem(String.valueOf(search.getText()));
                            search.setText("");
                        }
                        else
                        {
                            Toast toast = Toast.makeText(getApplicationContext(), "Player is offline", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.BOTTOM, 0, 0);
                            toast.show();
                        }
                        break;
                }
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

        playersAdapter.setOnItemClickListner(new PlayersMenuAdapter.OnItemClickListner()
        {
            @Override
            public void onItemClick(int position)
            {
                playersAdapter.isPlay(position);
            }
        });

    }

    public void bildRecyclerView()
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        playersList.setLayoutManager(layoutManager);
        playersAdapter = new PlayersMenuAdapter();
        playersList.setAdapter(playersAdapter);
    }



    public void invite(final String name)
    {                                                //уведомлие приглашение в игру
        AlertDialog.Builder builder = new AlertDialog.Builder(MainMenuActivity.this);
        builder.setTitle("Invite")
                .setMessage("Join the game with player " + name + "?")
//                .setIcon(R.drawable.ic_android_cat)
                .setCancelable(false)
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                dialog.cancel();
                            }
                        })
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                                                                          //ДЛЯ ФЕДИ И ВАНИ!!!!!! ДОБАВИТЬ ОТПРАВКУ ОТВЕТА ДРУГУ
                                playersAdapter.toIvite(name);
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
