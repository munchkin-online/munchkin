package com.example.sipliy.Cards;

public class Items implements Cards
{
    private String name;    //имя карты
    private String helpText;    //доп. информация
    private int itemType;   //тип шмотки (в какой слот надевается) (1-head, 2-chest, 3-boots, 4-rHand, 5-lHand, 6-thirdHand)
    private int carrierRace;    //раса носителя (1-human ...
    private int carrierClass;   //класс носителя (1-all ...
    private boolean carrierSex; //пол носителя (true - мужчина, false - женщина)
    private int cost;   //стоимость карты

    public Items(String name, int itemType, int carrierRace, int carrierClass, boolean carrierSex, int cost, String helpText)
    {
        this.name = name;
        this.helpText = helpText;
        this.itemType = itemType;
        this.carrierRace = carrierRace;
        this.carrierClass = carrierClass;
        this.carrierSex = carrierSex;
        this.cost = cost;
    }

    public int getItemType()
    {
        return itemType;
    }

    public int getCarrierRace()
    {
        return carrierRace;
    }

    public int getCarrierClass()
    {
        return carrierClass;
    }

    public boolean getCarrierSex()
    {
        return carrierSex;
    }

    @Override
    public String getHelpText()
    {
        return helpText;
    }

    @Override
    public String getName()
    {
        return name;
    }


}
