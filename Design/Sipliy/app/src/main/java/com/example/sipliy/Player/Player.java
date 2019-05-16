package com.example.sipliy.Player;

import com.example.sipliy.Cards.DiscardDecks;
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
    private int carrierRace;    //раса носителя (1-human, 2-dwarf, 3-elf, 4-hufling)
    private int carrierRaceSecond;    //дополнительная раса носителя для полукровки (1-human, 2-dwarf, 3-elf, 4-hufling)
    private boolean isSuperManichkin;    //суперманичкин или обычный
    private int carrierClass;   //класс носителя (1-all, 2-cleric, 3-wizard, 4-warrior, 5-thief)
    private int carrierClassSecond;   //дополнительный класс носителя для суперманичкина (1-all, 2-cleric, 3-wizard, 4-warrior, 5-thief)
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
                  int carrierRace,
                  int carrierRaceSecond,
                  boolean isSuperManichkin,
                  int carrierClass,
                  int carrierClassSecond,
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
        this.carrierRace = carrierRace;
        this.carrierRaceSecond = carrierRaceSecond;
        this.isSuperManichkin = isSuperManichkin;
        this.carrierClass = carrierClass;
        this.carrierClassSecond = carrierClassSecond;
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
        this.carrierRace = 0;
        this.carrierRaceSecond = 0;
        this.isSuperManichkin = false;
        this.carrierClass = 0;
        this.carrierClassSecond = 0;
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

    public int getStrengthClothes()
    {
        return strengthClothes;
    }

    public void setStrengthClothes(int strengthClothes)
    {
        this.strengthClothes = strengthClothes;
    }

    public int getCarrierRace()
    {
        return carrierRace;
    }

    public void setCarrierRace(int carrierRace)
    {
        this.carrierRace = carrierRace;
    }

    public int getCarrierClass()
    {
        return carrierClass;
    }

    public void setCarrierClass(int carrierClass)
    {
        this.carrierClass = carrierClass;
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

    public int getCarrierRaceSecond()
    {
        return carrierRaceSecond;
    }

    public void setCarrierRaceSecond(int carrierRaceSecond)
    {
        this.carrierRaceSecond = carrierRaceSecond;
    }

    public boolean isSuperManichkin()
    {
        return isSuperManichkin;
    }

    public void setSuperManichkin(boolean superManichkin)
    {
        isSuperManichkin = superManichkin;
    }

    public int getCarrierClassSecond()
    {
        return carrierClassSecond;
    }

    public void setCarrierClassSecond(int carrierClassSecond)
    {
        this.carrierClassSecond = carrierClassSecond;
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
}