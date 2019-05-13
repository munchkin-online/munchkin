package com.example.sipliy.Interaction;

import com.example.sipliy.Cards.Monster;
import com.example.sipliy.Cards.Player;

public class GameInteraction
{
    public void battle(Player player, Monster monster)  //сражение с монстром и последующее решение
    {
        if(battleWithMonster(player, monster))
        {
            player.increaseLVL(monster.getGiven_treasures());
            player.addTreasures(monster.getTreasures());
        }
        else
        {
            player.resetItems();
        }
    }
    public boolean battleWithMonster(Player player, Monster monster)  //сражение с монстром, в случае победы true.
    {
        return player.getStrength() > monster.getLevel();
    }
    public int sell(Player player, int ID)
    {
        //TODO требуется описать процесс получения id шмотки и последующуу ее продажу
        return 0;
    }
}
