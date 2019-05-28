package com.example.sipliy.Activity.Dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sipliy.Activity.GameActivity;
import com.example.sipliy.Cards.Interface.DoorsInterface;
import com.example.sipliy.Cards.Items;
import com.example.sipliy.Cards.Monster;
import com.example.sipliy.Data.PlayerInstances;
import com.example.sipliy.Interaction.GameInteraction;
import com.example.sipliy.R;

import java.util.Objects;

public class BattleDialog extends DialogFragment
{
    private DoorsInterface item;
    public void setItem(DoorsInterface item)
    {
        this.item = item;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
        builder.setCancelable(false);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.battledialog, null);
        ImageView monsterImage = view.findViewById(R.id.imageViewMonsterDialog);
        TextView strengthInBattle = view.findViewById(R.id.textViewStrenght);

        final Monster monster = (Monster) item;

        strengthInBattle.setText(Integer.toString(PlayerInstances.getPlayer().getStrength()));
        monsterImage.setImageResource(monster.getIMAGE_ID());

        builder.setNegativeButton("Оступить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GameInteraction.leave(PlayerInstances.getPlayer());
            }
        })
                .setPositiveButton("Атаковать", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GameInteraction.battle(PlayerInstances.getPlayer(), monster);
                    }
                });
        builder.setView(view);
        return builder.create();
    }
}