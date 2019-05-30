package com.example.sipliy.Data;

import com.example.sipliy.Player.Player;

import java.util.ArrayList;

public class PlayerInstances    //класс хранящий данные игроков, динамически расширяется до 6
{
    private static Player player = null;
    private static ArrayList<Player> opponents = new ArrayList<>();


    public static boolean addPlayer(Player newPlayer)
    {
        if(player == null)
        {
            player = newPlayer;
            return true;
        }
        else if(opponents.size() < 6)
        {
            opponents.add(newPlayer);
            return true;
        }
        else
        {
            return false;
        }
    }
    public static void exit()
    {
        player = null;
        opponents.clear();
    }
    public static Player getPlayer()
    {
        return player;
    }
    public static void setPlayerSex(int sex)
    {
        player.setSex(sex);
    }
    public static Player getOpponent(int index)
    {
        return opponents.get(index);
    }

}
