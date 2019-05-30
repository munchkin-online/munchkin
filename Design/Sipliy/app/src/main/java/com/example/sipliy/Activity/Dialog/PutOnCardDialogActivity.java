package com.example.sipliy.Activity.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.example.sipliy.Cards.Classes;
import com.example.sipliy.Cards.Interface.Cards;
import com.example.sipliy.Cards.Races;
import com.example.sipliy.Data.PlayerInstances;
import com.example.sipliy.Interaction.GameInteraction;
import com.example.sipliy.Player.Player;
import com.example.sipliy.R;

import java.util.Objects;

public class PutOnCardDialogActivity extends DialogFragment
{
    Cards card;

    public void setCard(Cards card) {
        this.card = card;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        final View view = inflater.inflate(R.layout.putoncarddialog, null);
        ImageView imageViewcard = view.findViewById(R.id.imageViewPutOnCard);
        imageViewcard.setImageResource(card.getIMAGE_ID());
        
        
        String positiveButton = "404";
        if (card.getType() == 1 )
        {
            if (PlayerInstances.getPlayer().get_Class() == 1)
            {
                positiveButton = "Надеть";
            }
            else 
            {
                positiveButton = "Поменять";
            }
        }
        else if (card.getType() == 2)
        {
            if (PlayerInstances.getPlayer().getRace() == 1)
            {
                positiveButton = "Надеть";
            }
            else
            {
                positiveButton = "Поменять";
            }
        }

        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        })
                .setPositiveButton(positiveButton, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (card.getType()){   // 1 - класс, 2 - расы, 3 - монстры, 4 - проклятья, 5 - разовые шмотки, 6 - шмотки, 7 - LevelUp(хз что это)
                            case 1:
                                PlayerInstances.getPlayer().set_Class(((Classes) card).getClassesType());
                                break;
                            case 2:
                                PlayerInstances.getPlayer().setRace(((Races) card).getRacesType());
                                break;
                        }
                    }
                });

        builder.setView(view);
        return builder.create();
    }
}
