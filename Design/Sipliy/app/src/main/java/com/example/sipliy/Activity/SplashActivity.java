package com.example.sipliy.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.sipliy.Cards.Doors;
import com.example.sipliy.Cards.Treasures;
import com.example.sipliy.R;

public class SplashActivity extends AppCompatActivity {

    private static String TAG = "SplashScr";
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
        setContentView(R.layout.activity_splash);
        Treasures treasures = new Treasures();  //заполнение сокровищ
        Doors doors = new Doors();  //заполнение дверей
        Log.d(TAG, "onCreate: " + Treasures.getSize());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainMenuActivity.class);
                startActivity(i);
                finish();
            }
        }, 1000);
    }
}
