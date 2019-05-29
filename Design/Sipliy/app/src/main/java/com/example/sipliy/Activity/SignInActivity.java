package com.example.sipliy.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.HashMap;
import java.util.Map;



public class SignInActivity extends AppCompatActivity {

    private TextView textView;
    private EditText edtLogin;
    private EditText edtPassword;
    private Button btnSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        textView = (TextView) findViewById(R.id.textView);
        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyAsyncTask().execute();
            }
        });
    }

    public void clickRegistr(View view) {
        Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivity(intent);
    }

    class MyAsyncTask extends AsyncTask<String, String, String> {
        String a, b, answerHTTP;
        String server = "http://192.168.1.9:8080/serverRegistration_war_exploded/api/login";

        @Override
        protected void onPreExecute() {
            a = edtLogin.getText().toString();
            b = edtPassword.getText().toString();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            HashMap<String,String> postDataParams = new HashMap<>();
            postDataParams.put("login",a);
            postDataParams.put("password",b);
            answerHTTP = performPostCall(server,postDataParams);
            if (Integer.valueOf(answerHTTP)==-1){
                textView.setText("Player with this username is not exist. Or password is not true");
            }
            else{

                /*SharedPreferences.Editor editor = mSettings.edit();
                editor.putString(APP_PREFERENCES_ID, answerHTTP);
                editor.apply();*/
                PlayerInstances.addPlayer(new Player(a ,Integer.valueOf(answerHTTP)));

                Intent intent = new Intent(SignInActivity.this, MainMenuActivity.class);
                startActivity(intent);
            }
            textView.setText("id:"+answerHTTP);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Toast toast = Toast.makeText(getApplicationContext(),answerHTTP,Toast.LENGTH_SHORT);
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

}