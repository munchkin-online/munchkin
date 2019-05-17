package com.example.sipliy.Activity.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.sipliy.Activity.GameActivity;
import com.example.sipliy.Data.PlayerInstances;
import com.example.sipliy.Player.Player;
import com.example.sipliy.R;

import java.util.Objects;
import java.util.zip.Inflater;

import static android.support.constraint.Constraints.TAG;

public class PlayerDialogActivity extends DialogFragment
{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog, null);
        builder.setView(view)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        EditText un = (EditText)view.findViewById(R.id.username);
                        PlayerInstances.addPlayer(new Player(un.getText().toString()));
                        RadioButton rbM = (RadioButton) view.findViewById(R.id.radioButtonMale);
                        RadioButton rbF = (RadioButton) view.findViewById(R.id.radioButtonFemale);
                        if(rbM.isChecked()) PlayerInstances.setPlayerSex(1);
                        else PlayerInstances.setPlayerSex(2);
                        PlayerInstances.addPlayer(new Player("Леха"));
                        PlayerInstances.addPlayer(new Player("Лешенька"));
                        PlayerInstances.addPlayer(new Player("Алеша"));
                        startActivity(new Intent(getActivity(), GameActivity.class));
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}