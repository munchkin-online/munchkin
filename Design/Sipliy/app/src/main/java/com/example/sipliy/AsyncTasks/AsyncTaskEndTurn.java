package com.example.sipliy.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.sipliy.Cards.Interface.DoorsInterface;
import com.example.sipliy.Cards.Interface.TreasuresInterface;
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

import static com.example.sipliy.Activity.GameActivity.gameTimer;
import static com.example.sipliy.Activity.MainMenuActivity.mainMenuTimer;

public class AsyncTaskEndTurn extends AsyncTask<String, String, String> {
    private String  answerHTTP;
    Context context;

    public AsyncTaskEndTurn(Context context) {
        this.context = context;
    }

    String server = "http://jws-app-munchkin.1d35.starter-us-east-1.openshiftapps.com/api/endturn";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {
        HashMap<String,String> postDataParams = new HashMap<>();

        int ndoor = PlayerInstances.getPlayer().getDecks().getDoors().size();

        String door = "";
        for(DoorsInterface doorsInterface : PlayerInstances.getPlayer().getDecks().getDoors()){
            door += doorsInterface.getID() + " ";
        }

        int ntrs = PlayerInstances.getPlayer().getDecks().getTreasures().size();

        String trs = "";
        for (TreasuresInterface treasuresInterface : PlayerInstances.getPlayer().getDecks().getTreasures()){
            trs += treasuresInterface.getID() + " ";
        }

        postDataParams.put("login", PlayerInstances.getPlayer().getName());
        postDataParams.put("whoplay", PlayerInstances.getOpponent(0).getName());
        postDataParams.put("lvl", String.valueOf(PlayerInstances.getPlayer().getLevel()));
        postDataParams.put("power", String.valueOf(PlayerInstances.getPlayer().getStrength()));
        /*postDataParams.put("ndoors", String.valueOf(ndoor));
        postDataParams.put("ntrs", String.valueOf(ntrs));*/
        postDataParams.put("doors", door);
        postDataParams.put("trs", trs);

        answerHTTP = performPostCall(server,postDataParams);
        Log.d("EndTurn",String.valueOf(PlayerInstances.getPlayer().getLevel()) + " " + String.valueOf(PlayerInstances.getPlayer().getStrength()));
        return null;
    }

//0 - number=1 //pervyi hod, pervogo igroka
//1 - number=2 //pervyi hod, vtorogo igroka
//-1 - endturn =0 //ne tvoi hod
//2 - number=-1 //ostalnie hodi
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        PlayerInstances.getPlayer().setCanPlay(false);
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
