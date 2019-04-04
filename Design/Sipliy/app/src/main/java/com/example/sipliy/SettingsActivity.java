package com.example.sipliy;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;

public class SettingsActivity extends AppCompatActivity
{
    private int progress;
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

        seekBarChangeListener listener = new seekBarChangeListener();

        seekBar.setOnSeekBarChangeListener(listener);
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
        outState.putInt("seek", seekBar.getProgress());
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        int progress = savedInstanceState.getInt("seek progress");
        seekBar.setProgress(progress);
    }

    private void findView()
    {
        aSwitch = (Switch)findViewById(R.id.switchVibration);
        seekBar = (SeekBar)findViewById(R.id.scrollSound);
        buttonExit = (Button)findViewById(R.id.buttonExitFromSettings);
        buttonQuestion = (Button)findViewById(R.id.buttonQuestion);
    }
    private class seekBarChangeListener implements OnSeekBarChangeListener
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
            Log.d(TAG, "onSaveInstanceState: " + seekBar.getProgress());
            progress = seekBar.getProgress();
        }
    }
}
