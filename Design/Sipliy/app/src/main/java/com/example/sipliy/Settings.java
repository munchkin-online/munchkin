package com.example.sipliy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity
{
    private Button buttonExit; // кнопка выхода в меню
    private Button buttonQuestion; // кнопка "задать вопрос"
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        buttonExit = (Button)findViewById(R.id.buttonExitFromSettings);
        buttonQuestion = (Button)findViewById(R.id.buttonQuestion);

        View.OnClickListener clickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                switch(v.getId())
                {
                    case R.id.buttonExitFromSettings:
                        finish();
                        break;
                    case R.id.buttonQuestion:
                        break;
                }
            }
        };

        buttonQuestion.setOnClickListener(clickListener);
        buttonExit.setOnClickListener(clickListener);
    }
}
