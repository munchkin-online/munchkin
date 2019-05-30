package com.example.sipliy.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import com.example.sipliy.Activity.Dialog.PlayerDialogActivity;
import com.example.sipliy.Data.MenuPlayers;
import com.example.sipliy.Data.PlayerInstances;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static com.example.sipliy.Activity.MainMenuActivity.Players;

public class AsyncTaskCheckPlay extends AsyncTask<String, String, String> {
    private String  answerHTTP;
    FragmentManager fragmentManager;
    Context context;

    public AsyncTaskCheckPlay(Context context, FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        this.context = context;
    }

    String server = "http://jws-app-munchkin.1d35.starter-us-east-1.openshiftapps.com/api/checkplay";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        HashMap<String,String> postDataParams = new HashMap<>();
        postDataParams.put("login", PlayerInstances.getPlayer().getName());
        answerHTTP = performPostCall(server,postDataParams);
        Log.d("CheckPlay",answerHTTP);
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Toast toast = Toast.makeText(context,answerHTTP,Toast.LENGTH_SHORT);
        if (answerHTTP.equals("1")){
            Toast.makeText(context, "Play", Toast.LENGTH_LONG).show();
            //transfer();

            /*for(int i = 0; i < MenuPlayers.getSize(); i++)    //добавление игроков в класс PlayerInstances
            {
                //PlayerInstances.addPlayer(new Player(Players[i]));
            }
            */
            PlayerDialogActivity playerDialogActivity = new PlayerDialogActivity(); //открытие диалового окна с просьбой ввести пол и имя
            playerDialogActivity.show(fragmentManager, "NoticeData");
        }
    }

    public void transfer()
    {
//        SizePlayers = MenuPlayers.getSize();
//        for (int i = 0; i < SizePlayers; i++)
//        {
//            if (MenuPlayers.getName(i) != "")
//            {
//                Players[i] = MenuPlayers.getName(i);
//            }
//        }
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
