package com.example.sipliy.Cards;

import java.util.ArrayList;

public class Player
{
    private String name;    //Имя игрока
    private int level;  //Левел игрока
    private int strength;   //Сила игрока
    private int strengthClothes;    //Суммарная сила всех шмоток игрока
    private Items shoes;    //Обувка игрока
    private Items armor;    //Броник игрока
    private Items helmet;   //Головняк игрока
    private Items leftHand;     //Левая рука игрока
    private Items rightHand;    //Правая рука игока
    private ArrayList<Object> otherClothes;  //Прочие шмотки игрока
    private ArrayList<Object> onetimeClothes;     //Разовые шмотки игока


    public Player(String name, int level, int strength, int strengthClothes, Items shoes, Items armor, Items helmet, Items leftHand, Items rightHand)
    {
        this.name = name;
        this.level = level;
        this.strength = strength;
        this.strengthClothes = strengthClothes;
        this.shoes = shoes;
        this.armor = armor;
        this.helmet = helmet;
        this.leftHand = leftHand;
        this.rightHand = rightHand;
        onetimeClothes = new ArrayList<>();
        otherClothes = new ArrayList<>();
    }

    public Player(String name)
    {
        this.name = name;
        this.level = 1;
        this.strength = 1;
        this.strengthClothes = 0;
        this.shoes = null;
        this.armor = null;
        this.helmet = null;
        this.leftHand = null;
        this.rightHand = null;
        onetimeClothes = new ArrayList<>();
        otherClothes = new ArrayList<>();
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

    public Items getHelmet() {
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
}
