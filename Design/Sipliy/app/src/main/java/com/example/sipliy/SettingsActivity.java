package com.example.sipliy;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity
{
    private static int seekProgress = 0; //проценты в seek bar
    private static boolean switchProgress = false; //проверка на включение кнопки вибрации
    
    private Button buttonExit; // кнопка выхода в меню
    private Button buttonQuestion; // кнопка "задать вопрос"
    private Switch aSwitch;
    private SeekBar seekBar;
    private static final String TAG = "MyTag";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        findView();

        seekBar.setProgress(seekProgress);
        aSwitch.setChecked(switchProgress);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                switchProgress = aSwitch.isChecked();
            }
        });
        seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                seekProgress = seekBar.getProgress();
            }
        });

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

    private void findView()
    {
        aSwitch = (Switch)findViewById(R.id.switchVibration);
        seekBar = (SeekBar)findViewById(R.id.scrollSound);
        buttonExit = (Button)findViewById(R.id.buttonExitFromSettings);
        buttonQuestion = (Button)findViewById(R.id.buttonQuestion);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
    }
}
