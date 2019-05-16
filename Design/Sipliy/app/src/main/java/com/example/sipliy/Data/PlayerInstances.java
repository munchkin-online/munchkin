package com.example.sipliy.Data;

import com.example.sipliy.Player.Player;

public class PlayerInstances    //класс хранящий данные игроков, динамически расширяется до 6
{
    private static Player player = null;
    private static Player opponent_1 = null;
    private static Player opponent_2 = null;
    private static Player opponent_3 = null;
    private static Player opponent_4 = null;
    private static Player opponent_5 = null;

    public PlayerInstances(Player player, Player opponent_1, Player opponent_2, Player opponent_3, Player opponent_4, Player opponent_5)
    {
        PlayerInstances.player = player;
        PlayerInstances.opponent_1 = opponent_1;
        PlayerInstances.opponent_2 = opponent_2;
        PlayerInstances.opponent_3 = opponent_3;
        PlayerInstances.opponent_4 = opponent_4;
        PlayerInstances.opponent_5 = opponent_5;
    }
    public PlayerInstances(Player player, Player opponent_1, Player opponent_2, Player opponent_3, Player opponent_4)
    {
        PlayerInstances.player = player;
        PlayerInstances.opponent_1 = opponent_1;
        PlayerInstances.opponent_2 = opponent_2;
        PlayerInstances.opponent_3 = opponent_3;
        PlayerInstances.opponent_4 = opponent_4;
    }
    public PlayerInstances(Player player, Player opponent_1, Player opponent_2, Player opponent_3)
    {
        PlayerInstances.player = player;
        PlayerInstances.opponent_1 = opponent_1;
        PlayerInstances.opponent_2 = opponent_2;
        PlayerInstances.opponent_3 = opponent_3;
    }
    public PlayerInstances(Player player, Player opponent_1, Player opponent_2)
    {
        PlayerInstances.player = player;
        PlayerInstances.opponent_1 = opponent_1;
        PlayerInstances.opponent_2 = opponent_2;
    }
    public PlayerInstances(Player player, Player opponent_1)
    {
        PlayerInstances.player = player;
        PlayerInstances.opponent_1 = opponent_1;
    }
    public static boolean addPlayer(Player newPlayer)
    {
        if(player == null)
        {
            player = newPlayer;
            return true;
        }
        else if(opponent_1 == null)
        {
            opponent_1 = newPlayer;
            return true;
        }
        else if(opponent_2 == null)
        {
            opponent_2 = newPlayer;
            return true;
        }
        else if(opponent_3 == null)
        {
            opponent_3 = newPlayer;
            return true;
        }
        else if(opponent_4 == null)
        {
            opponent_4 = newPlayer;
            return true;
        }
        else if(opponent_5 == null)
        {
            opponent_5 = newPlayer;
            return true;
        }
        else
            return false;
    }
    public static Player getPlayer()
    {
        return player;
    }
    public static void setPlayerSex(int sex){player.setSex(sex);}
    public static Player getOpponent_1()
    {
        return opponent_1;
    }
    public static Player getOpponent_2()
    {
        return opponent_2;
    }
    public static Player getOpponent_3()
    {
        return opponent_3;
    }
    public static Player getOpponent_4()
    {
        return opponent_4;
    }
    public static Player getOpponent_5()
    {
        return opponent_5;
    }
}
