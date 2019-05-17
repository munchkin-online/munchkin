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
import android.widget.Button;
import android.widget.Toast;

import com.example.sipliy.Cards.Buff;
import com.example.sipliy.Cards.Cards;
import com.example.sipliy.Cards.Items;
import com.example.sipliy.Cards.PlayerDecks;
import com.example.sipliy.Cards.Shmotki;
import com.example.sipliy.Player.Player;
import com.example.sipliy.R;

import java.util.ArrayList;
import java.util.Objects;

public class SaleDialogActivity extends DialogFragment  //диалоговое окно, представляет собой лист из карт игрока с флажками и кнопкой продать и отмена
{
    PlayerDecks playerDecks;

    public void setPlayerDecks(PlayerDecks playerDecks) { //Что бы диалог работал надо передать калоду игрока
        this.playerDecks = playerDecks;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
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

        final LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialogsale, null);
        final boolean[] checkedCardsArray = new boolean[cards.size()];
        for (boolean f : checkedCardsArray)
        {
            f = false;
        }
        String[] cardsName = new String[cards.size()];
        for (int i = 0; i < cards.size(); i++)
        {
            cardsName[i] = cards.get(i).getName();
        }

        final Button buttonSale = (Button)view.findViewById(R.id.oksale);
        final Button buttonCancel = (Button)view.findViewById(R.id.cancel);
        buttonSale.setEnabled(false);

        builder.setView(view);
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
                if (cost[0] < 1000){
                    buttonSale.setEnabled(false);
                }
                else
                {
                    buttonSale.setEnabled(true);
                }
            }
        });


        buttonSale.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
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
                SaleDialogActivity.this.getDialog().cancel();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                SaleDialogActivity.this.getDialog().cancel();
            }
        });
        return builder.create();
    }
}
