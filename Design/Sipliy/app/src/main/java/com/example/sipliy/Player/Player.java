package com.example.sipliy.Player;

import android.util.Log;

import com.example.sipliy.Cards.DiscardDecks;
import com.example.sipliy.Cards.Doors;
import com.example.sipliy.Cards.Interface.DoorsInterface;
import com.example.sipliy.Cards.Interface.TreasuresInterface;
import com.example.sipliy.Cards.Items;
import com.example.sipliy.Cards.PlayerDecks;
import com.example.sipliy.Cards.Treasures;

import java.util.ArrayList;

public class Player
{
    private String name;    //Имя игрока
    private int level;  //Левел игрока
    private int strengthClothes;    //Суммарная сила всех шмоток игрока
    private int Race;    //раса носителя (1-human, 2-dwarf, 3-elf, 4-hufling)
    private int RaceSecond;    //дополнительная раса для полукровки (1-human, 2-dwarf, 3-elf, 4-hufling)
    private boolean isSuperMunchkin;    //суперманичкин или обычный
    private int sex;
    private int _class;   //класс носителя (2-cleric, 3-wizard, 4-warrior, 5-thief)
    private int classSecond;   //дополнительный класс для суперманичкина (1-all, 2-cleric, 3-wizard, 4-warrior, 5-thief)
    private boolean isHalfBlood;    //полукровка или обычный
    private ArrayList<Items> thingsOnCharacter; // 0 - обувка. 1 - голова. 2 - броня. 3 - левая рука. 4 - правая рука
    private PlayerDecks decks;  //колода карт игрока

    public Player(String name,
                  int level,
                  int strengthClothes,
                  int Race,
                  int RaceSecond,
                  boolean isSuperMunchkin,
                  int sex,
                  int _class,
                  int classSecond,
                  boolean isHalfBlood,
                  ArrayList<Items> things)
    {
        this.name = name;
        this.level = level;
        this.strengthClothes = strengthClothes;
        this.Race = Race;
        this.RaceSecond = RaceSecond;
        this.isSuperMunchkin = isSuperMunchkin;
        this.sex = sex;
        this._class = _class;
        this.classSecond = classSecond;
        this.isHalfBlood = isHalfBlood;
        this.thingsOnCharacter = things;
        this.decks = new PlayerDecks();
    }

    public Player(String name)
    {
        this.name = name;
        this.level = 1;
        this.strengthClothes = 0;
        this.Race = 1;
        this.RaceSecond = 1;
        this.isSuperMunchkin = false;
        this.sex = 1;
        this._class = 1;
        this.classSecond = 1;
        this.isHalfBlood = false;
        this.thingsOnCharacter = new ArrayList<>();
        for(int i = 0; i < 5; i++)
        {
            thingsOnCharacter.add(new Items(0));
        }
        this.decks = new PlayerDecks();
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
        return level + strengthClothes;
    }
    public void update()
    {
        this.strengthClothes = sum();
    }

    public int sum()
    {
        int sum = 0;
        for(Items item : thingsOnCharacter)
        {
            sum += item.getBonus();
        }
        return sum;
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

    public int get_Class()
    {
        return _class;
    }

    public void set_Class(int _class)
    {
        this._class = _class;
    }

    public Items getShoes()
    {
        return thingsOnCharacter.get(0);
    }

    public void setShoes(Items shoes)
    {
        this.thingsOnCharacter.set(0, shoes);
        update();
    }

    public Items getArmor()
    {
        return thingsOnCharacter.get(2);
    }

    public void setArmor(Items armor)
    {
        this.thingsOnCharacter.set(2, armor);
        update();
    }

    public Items getHelmet()
    {
        return thingsOnCharacter.get(1);
    }

    public void setHelmet(Items helmet)
    {
        this.thingsOnCharacter.set(1, helmet);
        update();
    }

    public Items getLeftHand()
    {
        return thingsOnCharacter.get(3);
    }

    public void setLeftHand(Items leftHand)
    {
        this.thingsOnCharacter.set(3, leftHand);
        update();
    }

    public Items getRightHand()
    {
        return thingsOnCharacter.get(4);
    }

    public void setRightHand(Items rightHand)
    {
        this.thingsOnCharacter.set(4, rightHand);
        update();
    }

    public int getRaceSecond()
    {
        return RaceSecond;
    }

    public void setRaceSecond(int RaceSecond)
    {
        this.RaceSecond = RaceSecond;
    }

    public boolean isSuperMunchkin()
    {
        return isSuperMunchkin;
    }

    public void setSuperMunchkin(boolean superMunchkin)
    {
        isSuperMunchkin = superMunchkin;
    }

    public int getClassSecond()
    {
        return classSecond;
    }

    public void setClassSecond(int classSecond)
    {
        this.classSecond = classSecond;
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

    public void plusLVL()
    {
        if(this.level < 9)
        {
            this.level += 1;
        }
    }

    public Items getItem(int index)
    {
        return thingsOnCharacter.get(index);
    }

    public boolean hasItems()
    {
        return thingsOnCharacter.size() != 0;
    }
    public void resetItems()    //удаление карт из руки игрока после смерти
    {
        for(Items items : thingsOnCharacter)
        {
            DiscardDecks.addCard(items);
        }
        for(int i = 0; i < thingsOnCharacter.size(); i++)
        {
            thingsOnCharacter.set(i, new Items(0));
        }
        this.decks.reset();
    }

    public void addTreasures(int numbers)   //добавить сокровища в руку игрока
    {
        for(int i = 0; i < numbers; i++)
        {
            decks.addCard(Treasures.getItemCard());
        }
    }
    public void addTreasures(TreasuresInterface item)    //добавление сокровища в руку
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
    public void addDoors(DoorsInterface doors)    //добавление сокровища в руку
    {
        decks.addCard(doors);
    }

    public PlayerDecks getDecks() {
        return decks;
    }
}