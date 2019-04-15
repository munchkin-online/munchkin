package com.example.sipliy.Cards;

public class Monster implements Cards
{
    private int ID;
    private String name;    //имя монстра
    private boolean undead;   //андед - true, обычный - false
    private int level;  //уровень монстра
    private int treasures;  //количество сокровищ, дающихся за его убийство
    private int given_treasures;    //количество уровней, дающихся за его убийство
    private int pursuit;    //не преследует никого, чей уровень x или ниже
    private int debuff_race;    // расса, против которой мостр дает дебафф
    private int debuff_class;   //класс, против которой монстр дает дебафф
    private int debuff_value;   //сам дебафф
    private int vanish;     //бафф или дебафф к смывке(положительное или отрицательное число соотвественно)

    public Monster(int ID, String name, boolean undead,
                   int level, int treasures, int given_treasures, int pursuit, int debuff_race,
                   int debuff_class, int debuff_value, int vanish)
    {
        this.ID = ID;
        this.name = name;
        this.undead = undead;
        this.level = level;
        this.treasures = treasures;
        this.given_treasures = given_treasures;
        this.pursuit = pursuit;
        this.debuff_class = debuff_race;
        this.debuff_class = debuff_class;
        this.debuff_value = debuff_value;
        this.vanish = vanish;

    }

    public int getID()
    {
        return ID;
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

    public int getGiven_treasures()
    {
        return given_treasures;
    }

    public int getPursuit()
    {
        return pursuit;
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
