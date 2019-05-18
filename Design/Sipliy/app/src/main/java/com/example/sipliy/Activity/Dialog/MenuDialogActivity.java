package com.example.sipliy.Activity.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.sipliy.Activity.SettingsActivity;
import com.example.sipliy.R;

import java.util.Objects;

public class MenuDialogActivity extends DialogFragment  //диалоговое окно, открывающееся из игры, представляет собой 2 кнопки, выход и настройки
{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // Use the Builder clas for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater;// = Objects.requireNonNull(getActivity()).getLayoutInflater();

        inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialogmenu, null);

        Button buttonExit = (Button)view.findViewById(R.id.button_exit_dialog);
        Button buttonSettings = (Button)view.findViewById(R.id.button_setting_dialog);

        buttonExit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                /*builder.setTitle("Are you sure?")
                        .setPositiveButton("Continue", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                startActivity(new Intent(getActivity(), MainMenuActivity.clas));
                                Objects.requireNonNull(getActivity()).finish();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                            }
                        });
                builder.create();*/
                Objects.requireNonNull(getActivity()).finish();
            }
        });
        buttonSettings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getActivity(), SettingsActivity.class));
            }
        });

        builder.setView(view);
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
