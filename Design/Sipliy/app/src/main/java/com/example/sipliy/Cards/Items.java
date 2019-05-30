package com.example.sipliy.Cards;

import com.example.sipliy.Cards.Interface.Cards;
import com.example.sipliy.Cards.Interface.TreasuresInterface;

//TODO ЛЕХА БЛЭТ ИСПРАВЬ НУМЕРАЦИЮ
public class Items implements Cards, TreasuresInterface
{
    private int ID;
    private String name;    //имя карты
    private int itemType;   //тип шмотки (в какой слот надевается) (1-head, 2-chest, 3-boots, 4-hand, 5-bothHand, 6-thirdHand)
    private int carrierRace;    //раса носителя (1-human, 2-dwarf, 3-elf, 4-hufling)
    private int carrierClass;   //класс носителя (1-all, 2-cleric, 3-wizard, 4-warrior, 5-thief)
    private int carrierSex; //пол носителя (1-все, 2-мужчина, 3-женщина)
    private int bonus;
    private int cost;   //стоимость карты
    private int ICON_ID;
    private int IMAGE_ID;

    public Items(int bonus)
    {
        this.ID = 0;
        this.name = null;
        this.itemType = 0;
        this.carrierClass = 0;
        this.carrierRace = 0;
        this.carrierSex = 0;
        this.bonus = bonus;
        this.cost = 0;
        this.IMAGE_ID = 0;
        this.ICON_ID = 0;
    }
    public Items(int ID , String name, int bonus, int itemType, int carrierRace, int carrierClass, int carrierSex, int cost,int ICON_ID, int IMAGE_ID)
    {
        this.ID = ID;
        this.name = name;
        this.bonus = bonus;
        this.itemType = itemType;
        this.carrierRace = carrierRace;
        this.carrierClass = carrierClass;
        this.carrierSex = carrierSex;
        this.cost = cost;
        this.ICON_ID = ICON_ID;
        this.IMAGE_ID = IMAGE_ID;
    }

    public Items(int ID ,String name, int bonus, int itemType, int carrierRace, int carrierClass, int carrierSex, int cost, int raceBonus, int ICON_ID, int IMAGE_ID)
    {
        this.ID = ID;
        this.name = name;
        this.bonus = bonus;
        this.itemType = itemType;
        this.carrierRace = carrierRace;
        this.carrierClass = carrierClass;
        this.carrierSex = carrierSex;
        this.cost = cost;
        this.ICON_ID = ICON_ID;
        this.IMAGE_ID = IMAGE_ID;
        if(raceBonus == carrierRace)
        {
            this.bonus = elfBonus_2();
        }
    }

    @Override
    public int getID()
    {
        return ID;
    }

    public int getItemType()
    {
        return itemType;
    }

    public int getBonus()
    {
        if(bonus == 0)
        {
            return 0;
        }
        else
        {
            return bonus;
        }
    }

    @Override
    public int getType() {  // 1 - класс, 2 - расы, 3 - монстры, 4 - проклятья, 5 - разовые шмотки, 6 - шмотки, 7 - LevelUp(хз что это)
        return 6;
    }

    public int getCarrierRace()
    {
        return carrierRace;
    }

    public int getCarrierClass()
    {
        return carrierClass;
    }

    public int getCarrierSex()
    {
        return carrierSex;
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public String getName()
    {
        return name;
    }

    public int elfBonus_2()
    {
        return bonus + 2;
    }

    public int getICON_ID()
    {
        return ICON_ID;
    }

    @Override
    public int getIMAGE_ID()
    {
        return IMAGE_ID;
    }

}
