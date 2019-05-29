package com.example.sipliy.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

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

public class AsyncTaskInvite extends AsyncTask<String, String, String> {
    private String login, answerHTTP;
    Context context;


    public AsyncTaskInvite(String login, Context context) {
        this.login = login;
        this.context = context;
    }

    String server = "http://192.168.1.9:8080/serverRegistration_war_exploded/api/invite";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        HashMap<String,String> postDataParams = new HashMap<>();
        postDataParams.put("id", String.valueOf(PlayerInstances.getPlayer().getId()));
        postDataParams.put("whoinvite", String.valueOf(login));
        answerHTTP = performPostCall(server,postDataParams);

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (Integer.valueOf(answerHTTP) == 0){
            Toast.makeText(context, "Пользователя нельзя пригласить", Toast.LENGTH_LONG).show();
        }
        else if (Integer.valueOf(answerHTTP)==1){
            Toast.makeText(context, "Пригашениe отпрвлено", Toast.LENGTH_LONG).show();
            PlayerInstances.getPlayer().setInvite(true);
        }
        Log.d("invite", login);
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
