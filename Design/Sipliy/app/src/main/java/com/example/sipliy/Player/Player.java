package com.example.sipliy.Player;

import com.example.sipliy.Cards.DiscardDecks;
import com.example.sipliy.Cards.Doors;
import com.example.sipliy.Cards.Items;
import com.example.sipliy.Cards.PlayerDecks;
import com.example.sipliy.Cards.Treasures;

import java.util.ArrayList;

public class Player
{
    private String name;    //Имя игрока
    private int level;  //Левел игрока
    private int strength;   //Сила игрока
    private int strengthClothes;    //Суммарная сила всех шмоток игрока
    private int Race;    //раса носителя (1-human, 2-dwarf, 3-elf, 4-hufling)
    private int RaceSecond;    //дополнительная раса для полукровки (1-human, 2-dwarf, 3-elf, 4-hufling)
    private boolean isSuperManichkin;    //суперманичкин или обычный
    private int sex;
    private int Clas;   //класс носителя (2-cleric, 3-wizard, 4-warrior, 5-thief)
    private int ClasSecond;   //дополнительный класс для суперманичкина (1-all, 2-cleric, 3-wizard, 4-warrior, 5-thief)
    private boolean isHalfBlood;    //полукровка или обычный
    private Items shoes;    //Обувка игрока
    private Items armor;    //Броник игрока
    private Items helmet;   //Головняк игрока
    private Items leftHand;     //Левая рука игрока
    private Items rightHand;    //Правая рука игока
    private PlayerDecks decks = new PlayerDecks();  //колода карт игрока

    public Player(String name,
                  int level,
                  int strength,
                  int strengthClothes,
                  int Race,
                  int RaceSecond,
                  boolean isSuperManichkin,
                  int sex,
                  int Class,
                  int ClassSecond,
                  boolean isHalfBlood,
                  Items shoes,
                  Items armor,
                  Items helmet,
                  Items leftHand,
                  Items rightHand)
    {
        this.name = name;
        this.level = level;
        this.strength = strength;
        this.strengthClothes = strengthClothes;
        this.Race = Race;
        this.RaceSecond = RaceSecond;
        this.isSuperManichkin = isSuperManichkin;
        this.sex = sex;
        this.Clas = Clas;
        this.ClasSecond = ClasSecond;
        this.isHalfBlood = isHalfBlood;
        this.shoes = shoes;
        this.armor = armor;
        this.helmet = helmet;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
    }

    public Player(String name)
    {
        this.name = name;
        this.level = 1;
        this.strength = 1;
        this.strengthClothes = 0;
        this.Race = 1;
        this.RaceSecond = 1;
        this.isSuperManichkin = false;
        this.sex = 1;
        this.Clas = 1;
        this.ClasSecond = 1;
        this.isHalfBlood = false;
        this.shoes = null;
        this.armor = null;
        this.helmet = null;
        this.leftHand = null;
        this.rightHand = null;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public int getStrength()
    {
        return strength;
    }

    public void setStrength(int strength)
    {
        this.strength = strength;
    }

    public int getSex() {return sex;}

    public void setSex(int sex) {this.sex = sex;}

    public int getStrengthClothes()
    {
        return strengthClothes;
    }

    public void setStrengthClothes(int strengthClothes)
    {
        this.strengthClothes = strengthClothes;
    }

    public int getRace()
    {
        return Race;
    }

    public void setRace(int carrierRace)
    {
        this.Race = carrierRace;
    }

    public int getClas()
    {
        return Clas;
    }

    public void setClass(int Clas)
    {
        this.Clas = Clas;
    }

    public Items getShoes()
    {
        return shoes;
    }

    public void setShoes(Items shoes)
    {
        this.shoes = shoes;
    }

    public Items getArmor()
    {
        return armor;
    }

    public void setArmor(Items armor)
    {
        this.armor = armor;
    }

    public Items getHelmet()
    {
        return helmet;
    }

    public void setHelmet(Items helmet)
    {
        this.helmet = helmet;
    }

    public Items getLeftHand()
    {
        return leftHand;
    }

    public void setLeftHand(Items leftHand)
    {
        this.leftHand = leftHand;
    }

    public Items getRightHand()
    {
        return rightHand;
    }

    public void setRightHand(Items rightHand)
    {
        this.rightHand = rightHand;
    }

    public void sumStrength()
    {
        this.strength = this.level + this.strengthClothes;
    }

    public int getRaceSecond()
    {
        return RaceSecond;
    }

    public void setRaceSecond(int RaceSecond)
    {
        this.RaceSecond = RaceSecond;
    }

    public boolean isSuperManichkin()
    {
        return isSuperManichkin;
    }

    public void setSuperManichkin(boolean superManichkin)
    {
        isSuperManichkin = superManichkin;
    }

    public int getClassSecond()
    {
        return ClasSecond;
    }

    public void setClassSecond(int ClasSecond)
    {
        this.ClasSecond = ClasSecond;
    }

    public boolean isHalfBlood()
    {
        return isHalfBlood;
    }

    public void setHalfBlood(boolean halfBlood)
    {
        isHalfBlood = halfBlood;
    }

    public void increaseLVL(int increase)   //увеличение уровня
    {
        this.level += increase;
    }

    public void resetItems()    //удаление карт из руки игрока после смерти
    {
        DiscardDecks.addCard(shoes);
        DiscardDecks.addCard(armor);
        DiscardDecks.addCard(helmet);
        DiscardDecks.addCard(rightHand);
        DiscardDecks.addCard(leftHand);
        this.shoes = null;
        this.armor = null;
        this.helmet = null;
        this.rightHand = null;
        this.leftHand = null;
        this.decks.reset();
    }

    public void addTreasures(int numbers)   //добавить сокровища в руку игрока
    {
        for(int i = 0; i < numbers; i++)
        {
            decks.addCard(Treasures.getItemCard());
        }
    }
    public void addTreasures(Items item)    //добавление сокровища в руку
    {
        decks.addCard(item);
    }

    public void addDoors(int numbers)   //добавить сокровища в руку игрока
    {
        for(int i = 0; i < numbers; i++)
        {
            decks.addCard(Doors.getItemCard());
        }
    }
    public void addDoors(Object doors)    //добавление сокровища в руку
    {
        decks.addCard(doors);
    }

    public PlayerDecks getDecks() {
        return decks;
    }
}