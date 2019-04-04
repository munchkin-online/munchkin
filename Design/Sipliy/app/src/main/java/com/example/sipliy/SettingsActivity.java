package com.example.sipliy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity
{
    private static int progress;
    private Button buttonExit; // кнопка выхода в меню
    private Button buttonQuestion; // кнопка "задать вопрос"
    private Switch aSwitch;
    private SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        findView();

        seekBarChangeListner listner = new seekBarChangeListner();

        seekBar.setOnSeekBarChangeListener(listner);
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

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        int progress = savedInstanceState.getInt("seek progress");
        seekBar.setProgress(progress);
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void findView()
    {
        aSwitch = (Switch)findViewById(R.id.switchVibration);
        seekBar = (SeekBar)findViewById(R.id.scrollSound);
        buttonExit = (Button)findViewById(R.id.buttonExitFromSettings);
        buttonQuestion = (Button)findViewById(R.id.buttonQuestion);
    }
    private class seekBarChangeListner implements OnSeekBarChangeListener
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

        }
    }
}
