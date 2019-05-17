package com.example.sipliy.Activity.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sipliy.Cards.Buff;
import com.example.sipliy.Cards.Items;
import com.example.sipliy.Cards.ItemsInterface;
import com.example.sipliy.Cards.PlayerDecks;
import com.example.sipliy.Data.PlayerInstances;
import com.example.sipliy.R;

import java.util.ArrayList;
import java.util.Objects;

public class SaleDialogActivity extends DialogFragment  //диалоговое окно, представляет собой лист из карт игрока с флажками и кнопкой продать и отмена
{
    PlayerDecks playerDecks;
    int cost;
    boolean[] checkedCardsArray;
    ArrayList<ItemsInterface> cards;

    public void setPlayerDecks(PlayerDecks playerDecks) { //Что бы диалог работал надо передать калоду игрока
        this.playerDecks = playerDecks;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        cost = 0;
        cards = new ArrayList();


        for(Object card : playerDecks.getAll())
        {
            if(card instanceof ItemsInterface){
                cards.add((ItemsInterface) card);
            }
        }

        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        checkedCardsArray = new boolean[cards.size()];
        for (boolean f : checkedCardsArray)
        {
            f = false;
        }
        String[] cardsName = new String[cards.size()];
        for (int i = 0; i < cards.size(); i++)
        {
            cardsName[i] = cards.get(i).getName() + " - " + Integer.toString(cards.get(i).getCost());
        }

        builder.setMultiChoiceItems(cardsName, checkedCardsArray, new DialogInterface.OnMultiChoiceClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked)
            {
                checkedCardsArray[which] = isChecked;
                if(isChecked)
                {
                    cost += cards.get(which).getCost();
                }
                else
                {
                    cost -= cards.get(which).getCost();
                }
                Toast.makeText(getContext(), Integer.toString(cost), Toast.LENGTH_LONG).show();
            }
        });


        builder.setPositiveButton("Sale", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });
        return builder.create();
    }


    @Override
    public void onStart()
    {
        super.onStart();
        AlertDialog d = (AlertDialog)getDialog();
        if(d != null)
        {
            Button saleButton = (Button) d.getButton(Dialog.BUTTON_POSITIVE);
            saleButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (cost < 1000)
                    {
                        Toast.makeText(getContext(), "Type more than 1000 gold", Toast.LENGTH_LONG).show();
                    }
                    else
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
                        PlayerInstances.getPlayer().plusLVL();
                        dismiss();
                    }
                }
            });
        }
    }
}
