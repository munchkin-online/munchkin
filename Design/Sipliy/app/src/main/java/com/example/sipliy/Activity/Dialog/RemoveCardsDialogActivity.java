package com.example.sipliy.Activity.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.sipliy.Cards.Buff;
import com.example.sipliy.Cards.Items;
import com.example.sipliy.Cards.ItemsInterface;
import com.example.sipliy.Cards.PlayerDecks;
import com.example.sipliy.R;

import java.util.ArrayList;
import java.util.Objects;


public class RemoveCardsDialogActivity extends DialogFragment {
    PlayerDecks playerDecks;

    public void setPlayerDecks(PlayerDecks playerDecks) { //Что бы диалог работал надо передать калоду игрока
        this.playerDecks = playerDecks;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        final ArrayList<ItemsInterface> cards = new ArrayList();
        for(ItemsInterface card : playerDecks.getItems())
        {
            cards.add(card);
        }
        for(ItemsInterface card : playerDecks.getBuff())
        {
            cards.add(card);
        }
        final int[] cost = {cards.size()};

        // Use the Builder clas for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialogremovecards, null);
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

        final Button buttonRemove = (Button)view.findViewById(R.id.okremove);
        final Button buttonCancel = (Button)view.findViewById(R.id.cancelremove);
        buttonRemove.setEnabled(false);

        builder.setView(view);
        builder.setMultiChoiceItems(cardsName, checkedCardsArray, new DialogInterface.OnMultiChoiceClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked)
            {
                checkedCardsArray[which] = isChecked;
                if(isChecked)
                {
                    cost[0] --;
                }
                else
                {
                    cost[0] ++;
                }
                if (cost[0] <= 5){
                    buttonRemove.setEnabled(true);
                }
                else
                {
                    buttonRemove.setEnabled(false);
                }
            }
        });


        buttonRemove.setOnClickListener(new View.OnClickListener()
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
                RemoveCardsDialogActivity.this.getDialog().cancel();
            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                RemoveCardsDialogActivity.this.getDialog().cancel();
            }
        });
        return builder.create();
    }
}
