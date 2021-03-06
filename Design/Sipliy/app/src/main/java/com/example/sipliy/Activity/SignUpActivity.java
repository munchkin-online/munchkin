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

public class SignUpActivity extends AppCompatActivity {

    private Button btnRegistration;
    private EditText edtLogin;
    private EditText edtPassword1;
    private EditText edtPassword2;
    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        btnRegistration = findViewById(R.id.btnRegistration);
        edtLogin = findViewById(R.id.edtLogin);
        edtPassword1 = findViewById(R.id.edtPassword1);
        edtPassword2 = findViewById(R.id.edtPassword2);
        txtResult = findViewById(R.id.txtResult);
    }

    public void clickBtnRegistr(View view) {
        if(edtLogin.getText().toString().equals("")){
           Toast.makeText(getApplicationContext(), "Введите логин", Toast.LENGTH_LONG);
        }
        else if (edtPassword1.getText().toString().equals(edtPassword2.getText().toString())){
            new MyAsyncTask().execute();
        }
        else{
            Toast.makeText(getApplicationContext(), "Пароли не совпадают", Toast.LENGTH_LONG);
        }
    }

    private class MyAsyncTask extends AsyncTask<String,String, String> {
        String a, b, answerHTTP;
        String server = "http://jws-app-munchkin.1d35.starter-us-east-1.openshiftapps.com/api/registration";

        @Override
        protected void onPreExecute() {
            a = edtLogin.getText().toString();
            b = edtPassword1.getText().toString();
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            HashMap<String,String> postDataParams = new HashMap<>();
            postDataParams.put("login",a);
            postDataParams.put("password",b);

            answerHTTP = performPostCall(server,postDataParams);
            if (-1 == Integer.valueOf(answerHTTP)) {
                txtResult.setText("Player with this username already exists. Try again.");
            }
            else{
                txtResult.setText("id:"+answerHTTP);
                /*SharedPreferences.Editor editor = mSettings.edit();
                editor.putString(APP_PREFERENCES_ID, answerHTTP);
                editor.apply();*/
                PlayerInstances.addPlayer(new Player(a ,Integer.valueOf(answerHTTP)));
                Intent intent = new Intent(SignUpActivity.this, MainMenuActivity.class);
                startActivity(intent);
            }
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
