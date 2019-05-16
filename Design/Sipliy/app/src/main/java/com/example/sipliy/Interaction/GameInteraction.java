package com.example.sipliy.Interaction;

import com.example.sipliy.Cards.Monster;
import com.example.sipliy.Player.Player;

import java.util.Random;

public class GameInteraction    //взаимодействие по ходу игры
{
    public static void battle(Player player, Monster monster)  //сражение с монстром и последующее решение
    {
        if(battleWithMonster(player, monster))
        {
            player.increaseLVL(monster.getGiven_levels());
            Random random = new Random();
            if (player.getClas() == 5 && random.nextInt(101) > 50)//Бафф вора
                player.addTreasures(monster.getTreasures() + 1);
            else
                player.addTreasures(monster.getTreasures());
        }
        else
        {
            player.resetItems();
        }
    }
    private static boolean battleWithMonster(Player player, Monster monster)  //сражение с монстром, в случае победы true.
    {
        if (player.getClas() == 4)//Бафф воина
            return player.getStrength() >= monster.getLevel();
        else if (player.getClas() == 2 && monster.IsItUndead() == true)//Бафф клирика
            return player.getStrength() + 3 > monster.getLevel();
        return player.getStrength() > monster.getLevel();
    }
    public static int sell(Player player, int ID)
    {
        //TODO требуется описать процесс получения id шмотки и последующуу ее продажу
        return 0;
    }
}
