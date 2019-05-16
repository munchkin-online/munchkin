package com.example.sipliy.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.example.sipliy.R;

import java.util.Objects;

public class MenuDialogActivity extends DialogFragment
{
    private void setOnClick()
    {
        View.OnClickListener onClickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                switch(v.getId())
                {
                    case R.id.button_exit_dialog:
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Are you sure?")
                                .setPositiveButton("Continue", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        startActivity(new Intent(getActivity(), MainMenuActivity.class));
                                        getActivity().finish();
                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {

                                    }
                                });
                        break;
                    case R.id.button_setting_dialog:
                        startActivity(new Intent(Objects.requireNonNull(getActivity()), SettingsActivity.class));
                        break;
                }
            }
        };
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // Use the Builder class for convenient dialog construction
        AlertDialog alertDialog= new AlertDialog.Builder(getActivity()).create();

        LayoutInflater inflater;// = Objects.requireNonNull(getActivity()).getLayoutInflater();

        inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialogmenu, null);

        Button button = (Button)view.findViewById(R.id.button_exit_dialog);
        Button button1 = (Button)view.findViewById(R.id.button_setting_dialog);

        //setOnClick();

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Are you sure?")
                        .setPositiveButton("Continue", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                startActivity(new Intent(getActivity(), MainMenuActivity.class));
                                getActivity().finish();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {

                            }
                        });
            }
        });
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getActivity(), MainMenuActivity.class));
                getActivity().finish();
            }
        });

        alertDialog.setView(inflater.inflate(R.layout.dialogmenu, null));
        // Create the AlertDialog object and return it
        return alertDialog;
    }
}
/*

LayoutInflater layoutInflater = LayoutInflater.from(this);
View promptView = layoutInflater.inflate(R.layout.prompt, null);
final AlertDialog alertD = new AlertDialog.Builder(this).create();
EditText userInput = (EditText) promptView.findViewById(R.id.userInput);
Button btnAdd1 = (Button) promptView.findViewById(R.id.btnAdd1);
Button btnAdd2 = (Button) promptView.findViewById(R.id.btnAdd2);
btnAdd1.setOnClickListener(new OnClickListener()
{
    public void onClick(View v)
    {
        btnAdd1 has been clicked
    }
});
btnAdd2.setOnClickListener(new OnClickListener()
{
    public void onClick(View v)
    {
        btnAdd2 has been clicked
    }
});
alertD.setView(promptView); alertD.show(); */
/*
dialog.setPositiveButton(R.string.positive, new DialogInterface.OnClickListener()
{
    @Override public void onClick(DialogInterface dialogInterface, int i)
        { dialog.show(); }
});*/
