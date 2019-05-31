package com.example.sipliy.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.sipliy.Cards.Doors;
import com.example.sipliy.Cards.Interface.DoorsInterface;
import com.example.sipliy.Cards.Interface.TreasuresInterface;
import com.example.sipliy.Cards.Treasures;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.sipliy.Activity.GameActivity.gameTimer;
import static com.example.sipliy.Activity.MainMenuActivity.mainMenuTimer;

public class AsyncTaskCheckEndTurn extends AsyncTask<String, String, String> {
    private String  answerHTTP;
    Context context;

    public AsyncTaskCheckEndTurn(Context context) {
        this.context = context;
    }

    String server = "http://jws-app-munchkin.1d35.starter-us-east-1.openshiftapps.com/api/checkendturn";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        HashMap<String,String> postDataParams = new HashMap<>();
        postDataParams.put("login", PlayerInstances.getPlayer().getName());
        postDataParams.put("whoplay", PlayerInstances.getOpponent(0).getName());
        answerHTTP = performPostCall(server,postDataParams);

        return null;
    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        Log.d("checkendturn", answerHTTP);

        String[] units = answerHTTP.split(" ");


        String lvl = units[0];
        String power = units[1];

        PlayerInstances.getOpponent(0).setLevel(Integer.valueOf(lvl));
        PlayerInstances.getOpponent(0).setLevel(Integer.valueOf(power));

        ArrayList<String> drs = new ArrayList<>();
        ArrayList<String> trs = new ArrayList<>();

        int i = 2;
        while ((i < units.length)&&(!units[i].equals("|"))){
            drs.add(units[i]);
            i++;
        }
        i++;
        for(int l=i; l<units.length; l++){
            trs.add(units[l]);
        }
        PlayerInstances.getPlayer().getDecks().clear();
        for (String temp : drs){
            for (DoorsInterface door : Doors.getDoors())
            {
                if(Integer.valueOf(temp) == door.getID()){
                    PlayerInstances.getOpponent(0).getDecks().addCard(door);
                }
            }
        }
        for (String temp : trs){
            for (TreasuresInterface trrs : Treasures.getItems())
            {
                if(Integer.valueOf(temp) == trrs.getID()){
                    PlayerInstances.getOpponent(0).getDecks().addCard(trrs);
                }
            }
        }
        Log.d("CheckEndTurn", lvl + " " + power);
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
