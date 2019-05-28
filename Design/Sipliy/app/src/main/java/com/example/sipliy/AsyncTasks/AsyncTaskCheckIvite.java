package com.example.sipliy.AsyncTasks;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.example.sipliy.Activity.MainMenuActivity;
import com.example.sipliy.Data.MenuPlayers;
import com.example.sipliy.Data.PlayerInstances;
import com.example.sipliy.Player.Player;

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

public class AsyncTaskCheckIvite extends AsyncTask<String, String, String> {
    private String  answerHTTP;
    Context context;

    public AsyncTaskCheckIvite(Context context) {
        this.context = context;
    }

    String server = "http://192.168.1.9:8080/serverRegistration_war_exploded/checkinvite";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        HashMap<String,String> postDataParams = new HashMap<>();
        postDataParams.put("login", String.valueOf(PlayerInstances.getPlayer().getName()));
        answerHTTP = performPostCall(server,postDataParams);
        Log.d("check invite",answerHTTP);

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        if (!answerHTTP.equals("0")){
            invite(answerHTTP);
        }
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
    public void invite(final String name)
    {                                                //уведомлие приглашение в игру
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Invite")
                .setMessage("Join the game with player " + name + "?")
//                .setIcon(R.drawable.ic_android_cat)
                .setCancelable(false)
                .setNegativeButton("No",
                        new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                AsyncTaskInviteResult asyncTaskInviteResult = new AsyncTaskInviteResult("no", context);
                                asyncTaskInviteResult.execute();
                                dialog.cancel();
                            }
                        })
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                AsyncTaskInviteResult asyncTaskInviteResult = new AsyncTaskInviteResult("yes", context);
                                asyncTaskInviteResult.execute();
                                MenuPlayers.toIvite(name);
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
