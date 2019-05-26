package com.example.sipliy.Activity.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.sipliy.Cards.Classes;
import com.example.sipliy.Cards.Monster;
import com.example.sipliy.Cards.Races;
import com.example.sipliy.Data.PlayerInstances;
import com.example.sipliy.R;

import java.util.Objects;

public class ChoiceBattleDialogActivity extends DialogFragment {
    Monster monster;

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        final View view = inflater.inflate(R.layout.choicebattledialog, null);
        ImageView imageViewcard = view.findViewById(R.id.imageViewChoiceBattle);
        imageViewcard.setImageResource(monster.getIMAGE_ID());

        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        })
                .setPositiveButton("Сразиться", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BattleDialog battleDialog = new BattleDialog();
                        battleDialog.setItem((Monster) monster);
                        battleDialog.show(getFragmentManager(), "Notice Data");
                    }
                });

        builder.setView(view);
        return builder.create();
    }
}
