package com.example.sipliy.Interaction;

import com.example.sipliy.Cards.Monster;
import com.example.sipliy.Player.Player;

public class GameInteraction    //взаимодействие по ходу игры
{
    public static void battle(Player player, Monster monster)  //сражение с монстром и последующее решение
    {
        if(battleWithMonster(player, monster))
        {
            player.increaseLVL(monster.getGiven_levels());
            player.addTreasures(monster.getTreasures());
        }
        else
        {
            player.resetItems();
        }
    }
    private static boolean battleWithMonster(Player player, Monster monster)  //сражение с монстром, в случае победы true.
    {
        return player.getStrength() > monster.getLevel();
    }
    public static int sell(Player player, int ID)
    {
        //TODO требуется описать процесс получения id шмотки и последующуу ее продажу
        return 0;
    }
}
