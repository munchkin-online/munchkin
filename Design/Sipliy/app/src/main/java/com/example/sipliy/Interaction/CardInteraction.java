package com.example.sipliy.Interaction;

import com.example.sipliy.Cards.BuffDebuffCards;
import com.example.sipliy.Cards.BuffOrDebuff;
import com.example.sipliy.Cards.Decks;
import com.example.sipliy.Cards.Items;

public class CardInteraction
{
    Decks decksItems = new Decks();

    public void addInDeck(Items card)
    {
        decksItems.addCard(card);
    }
    public void addInDeck(BuffOrDebuff card)
    {
        decksItems.addCard(card);
    }
}
