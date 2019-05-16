package com.example.sipliy.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.example.sipliy.Cards.Buff;
import com.example.sipliy.Cards.Cards;
import com.example.sipliy.Cards.Items;
import com.example.sipliy.Cards.PlayerDecks;
import com.example.sipliy.Cards.Shmotki;
import com.example.sipliy.Player.Player;
import com.example.sipliy.R;

import java.util.ArrayList;

public class SaleDialogActivity extends DialogFragment  //диалоговое окно, представляет собой лист из карт игрока с флажками и кнопкой продать и отмена
{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        final PlayerDecks playerDecks = new PlayerDecks();
        playerDecks.addCard(new Items(11001, "Шлем бесстрашия", 1, 1, 1, 1, 1, 200));
        final int[] cost = {0};
        final ArrayList<Shmotki> cards = new ArrayList();
        for(Shmotki card : playerDecks.getItems())
        {
            cards.add(card);
        }
        for(Shmotki card : playerDecks.getBuff())
        {
            cards.add(card);
        }

        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater;// = Objects.requireNonNull(getActivity()).getLayoutInflater();

        inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialogsale, null);
        final boolean[] checkedCardsArray = new boolean[cards.size()];
        for (boolean f : checkedCardsArray)
        {
            f = false;
        }
        String[] cardsName = new String[cards.size()];
        for (int i = 0; i < cards.size(); i++)
        {
            cardsName[i] = cards.get(i).getClass().getName();
        }
        builder.setMultiChoiceItems(cardsName, checkedCardsArray, new DialogInterface.OnMultiChoiceClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked)
            {
                checkedCardsArray[which] = isChecked;
                if(isChecked)
                {
                    cost[0] += cards.get(which).getCost();
                }
                else
                {
                    cost[0] -= cards.get(which).getCost();
                }
            }
        });

//        if(cost[0] < 1000)
//        {
//            ((AlertDialog)getDialog()).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
//        }
//        else
//        {
//            ((AlertDialog)getDialog()).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
//        }




        builder.setPositiveButton("Продать", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                for (int i = 0; i < checkedCardsArray.length; i++)
                {
                    boolean checked = checkedCardsArray[i];
                    if(checked)
                    {
                        Object card = cards.get(i);
                        if (card instanceof Items)
                        {
                            playerDecks.deleteItem((Items) card);
                        }
                        else if(card instanceof Buff)
                        {
                            playerDecks.deleteBuff((Buff) card);
                        }
                    }
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder.create();
    }
}
