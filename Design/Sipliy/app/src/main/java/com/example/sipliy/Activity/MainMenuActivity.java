package com.example.sipliy.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
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

public class MainMenuActivity extends AppCompatActivity
{

    private RecyclerView playersList;        //лист с игрками

    public static String[] Players = new String[3];
    public static int SizePlayers;

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
                        new AsyncTaskStatus().execute();
                        //as.cancel(true);

                        Log.d("statusNew", String.valueOf(status));

                        /*if(status)
                        {
                            playersAdapter.addItem(String.valueOf(search.getText()));
                            search.setText("");
                        }
                        else
                        {
                            Toast toast = Toast.makeText(getApplicationContext(), "Player is offline", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.BOTTOM, 0, 0);
                            toast.show();
                        }*/
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
    }

    public void bildRecyclerView()
    {
        MenuPlayers.buildRecyclerView(this, playersList);
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
                                MenuPlayers.toIvite(name);
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

    public void clickSearch(View view) {
        Log.d("startStatus", String.valueOf(status));
        AsyncTaskStatus as = new AsyncTaskStatus();
        as.execute();
    }

    public class AsyncTaskStatus  extends AsyncTask<String, String, String> {
        private String  answerHTTP;



        String server = "http://192.168.1.9:8080/serverRegistration_war_exploded/status";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            HashMap<String,String> postDataParams = new HashMap<>();
            postDataParams.put("login", String.valueOf(search.getText()));
            answerHTTP = performPostCall(server,postDataParams);
            Log.d("status",answerHTTP);
            if (Integer.valueOf(answerHTTP)==0){
                Toast toast = Toast.makeText(getApplicationContext(), "Player is offline", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                toast.show();
            }
            else if (Integer.valueOf(answerHTTP)==1){
                Log.d("newStatus","true");
                MenuPlayers.addItem(String.valueOf(search.getText()));
                search.setText("");
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast toast = Toast.makeText(getApplicationContext(),answerHTTP,Toast.LENGTH_SHORT);
        }



        public String performPostCall(String requestUrl, HashMap<String, String> postDataParams){
            URL url;
            String response = "";
            try{
                url = new URL(requestUrl);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                writer.write(getDataString(postDataParams));
                writer.flush();
                writer.close();
                os.close();
                int responseCode = conn.getResponseCode();
                if(responseCode == HttpURLConnection.HTTP_OK){
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while((line = br.readLine()) != null)
                        response += line;
                }
                else response = "";
            } catch (Exception e){
                e.printStackTrace();
            }
            return response;
        }
        private String getDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
            StringBuilder result = new StringBuilder();
            boolean first = true;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            }

            return result.toString();
        }
    }


}
