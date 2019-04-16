package com.example.sipliy.Cards;

public class Items implements Cards
{
    private int ID;
    private String name;    //имя карты
    private int itemType;   //тип шмотки (в какой слот надевается) (1-head, 2-chest, 3-boots, 4-hand, 5-bothHand, 6-thirdHand)
    private int carrierRace;    //раса носителя (1-human, 2-dwarf, 3-elf, 4-hufling)
    private int carrierClass;   //класс носителя (1-all, 2-cleric, 3-wizard, 4-warrior, 5-thief)
    private int carrierSex; //пол носителя (1-все, 2-мужчина, 3-женщина)
    private int bonus;
    private int cost;   //стоимость карты

    public Items(int ID ,String name, int bonus, int itemType, int carrierRace, int carrierClass, int carrierSex, int cost)
    {
        this.ID = ID;
        this.name = name;
        this.bonus = bonus;
        this.itemType = itemType;
        this.carrierRace = carrierRace;
        this.carrierClass = carrierClass;
        this.carrierSex = carrierSex;
        this.cost = cost;
    }

    public Items(int ID ,String name, int bonus, int itemType, int carrierRace, int carrierClass, int carrierSex, int cost, int raceBonus)
    {
        this.ID = ID;
        this.name = name;
        this.bonus = bonus;
        this.itemType = itemType;
        this.carrierRace = carrierRace;
        this.carrierClass = carrierClass;
        this.carrierSex = carrierSex;
        this.cost = cost;
        if(raceBonus == carrierRace)
        {
            this.bonus = elfBonus_2();
        }
    }

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
        return bonus;
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

    public int getCost()
    {
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

}
