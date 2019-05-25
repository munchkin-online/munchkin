package com.example.sipliy.Cards;

import com.example.sipliy.Cards.Interface.Cards;
import com.example.sipliy.Cards.Interface.DoorsInterface;

public class Monster implements Cards, DoorsInterface
{
    private int ID;
    private String name;    //имя монстра
    private boolean undead;   //андед - true, обычный - false
    private int level;  //уровень монстра
    private int treasures;  //количество сокровищ, дающихся за его убийство
    private int given_levels;    //количество уровней, дающихся за его убийство
    private int not_beating_with;   //1 - рассовый признак, 2 - классовый признак, 3 - половой признак, 4 - уровневый признак
    private int not_beating_index;   //1 - человек, 3 - эльф ..., либо 1 - вони, 2 - вор..., либо 1 - мужчина, 2 - женщина..., либо значение уровня
    private int debuff_race;    // расса, против которой мостр дает дебафф
    private int debuff_class;   //класс, против которой монстр дает дебафф
    private int debuff_value;   //сам дебафф
    private int vanish;     //бафф или дебафф к смывке(положительное или отрицательное число соотвественно)

    public Monster(int ID, String name, boolean undead,
                   int level, int treasures, int given_levels, int not_beating_with, int not_beating_index, int debuff_race,
                   int debuff_class, int debuff_value, int vanish)
    {
        this.ID = ID;
        this.name = name;
        this.undead = undead;
        this.level = level;
        this.treasures = treasures;
        this.given_levels = given_levels;
        this.not_beating_with = not_beating_with;
        this.not_beating_index = not_beating_index;
        this.debuff_race = debuff_race;
        this.debuff_class = debuff_class;
        this.debuff_value = debuff_value;
        this.vanish = vanish;

    }

    public Monster(String name)
    {
        this.name = name;
    }

    public int getID()
    {
        return ID;
    }

    @Override
    public int getType()  // 1 - класс, 2 - расы, 3 - монстры, 4 - проклятья.
    {
        return 3;
    }

    public boolean IsItUndead()
    {
        return undead;
    }

    public int getLevel()
    {
        return level;
    }

    public int getDebuff_class()
    {
        return debuff_class;
    }

    public int getDebuff_race()
    {
        return debuff_race;
    }

    public int getDebuff_value()
    {
        return debuff_value;
    }

    public int getGiven_levels()
    {
        return given_levels;
    }

    public int getNot_beating_with()
    {
        return not_beating_with;
    }

    public int getNot_beating_index()
    {
        return not_beating_index;
    }

    public int getTreasures()
    {
        return treasures;
    }

    public int getVanish()
    {
        return vanish;
    }

    @Override
    public String getName()
    {
        return name;
    }



}
