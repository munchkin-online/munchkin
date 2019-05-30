package com.example.sipliy.Interaction;

import com.example.sipliy.Adapter.RecyclerViewForBuffsInBattle;
import com.example.sipliy.Cards.Monster;
import com.example.sipliy.Player.Player;

import java.util.Random;

public class GameInteraction    //взаимодействие по ходу игры
{
    private static Random random = new Random();
    public static void battle(Player player, Monster monster, int buff)  //сражение с монстром и последующее решение
    {
        if(battleWithMonster(player, monster, buff))
        {
            player.increaseLVL(monster.getGiven_levels());
            if (player.get_Class() == 5 && random.nextInt(101) > 50)//Бафф вора
                player.addTreasures(monster.getTreasures() + 1);
            else
                player.addTreasures(monster.getTreasures());
        }
        else
        {
            leave(player);
        }
    }
    public static void leave(Player player)
    {
        if (player.getRace() == 3 && random.nextInt(6) + 1 >= 4)//Бафф эльфа на смывку
            return;
        else if (random.nextInt(6) >= 4)
            return;
        else
            player.resetItems();
    }
    private static boolean battleWithMonster(Player player, Monster monster, int buff)  //сражение с монстром, в случае победы true.
    {
        if (player.get_Class() == 4)//Бафф воина
            return player.getStrength() + buff >= monster.getLevel();
        else if (player.get_Class() == 2 && monster.IsItUndead())//Бафф клирика
            return player.getStrength() + 3 + buff > monster.getLevel();
        return player.getStrength() + buff > monster.getLevel();
    }
    public static int sell(Player player, int ID)
    {
        //TODO требуется описать процесс получения id шмотки и последующуу ее продажу
        return 0;
    }
}
