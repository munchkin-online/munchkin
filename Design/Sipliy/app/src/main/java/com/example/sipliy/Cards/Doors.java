package com.example.sipliy.Cards;

import com.example.sipliy.Cards.Interface.DoorsInterface;

import java.util.ArrayList;

public class Doors
{
    private static ArrayList<DoorsInterface> doors; //колода дверей
    private static ArrayList<DoorsInterface> spareDoorsDeck;
    public Doors()
    {
        doors = new ArrayList<>();
        fill();
        spareDoorsDeck = doors;
    }

    public static DoorsInterface getItemCard()
    {
        return doors.remove(doors.size() - 1);
    }

    public void fill()
    {
        //not_beating_with: 1 - рассовый признак, 2 - классовый признак, 3 - половой признак, 4 - уровневый признак
        doors.add(new Monster(20001, "Кальмадзилла", false, 18, 4, 2, 4, 4, 3, 0, 4, 0));
        doors.add(new Monster(20002, "3872 Орка", false, 10, 3, 1, 0, 0, 2, 0, 6, 0));
        doors.add(new Monster(20003, "Бульрог", false, 18, 5, 2, 4, 4, 0, 0, 0, 0));
        doors.add(new Monster(20004, "Амазонка", false, 8, 2, 1, 3, 2, 0, 0, 0, 0));
        doors.add(new Monster(20005, "Просто Тролль", false, 10, 3, 1, 0, 0, 0, 0, 0, 0));
        doors.add(new Monster(20006, "Рыгачу", false, 6, 2, 1, 0, 0, 0, 0, 0, 0));
        doors.add(new Monster(20007, "Лицесос", false, 8, 2, 1, 0, 0, 3, 0, 6, 0));
        doors.add(new Monster(20008, "Наскипидаренные улитки", false, 4, 2, 1, 0, 0, 3, 0, 6, -2));
        doors.add(new Monster(20009, "Невыразимо Жуткий Неописуемый Ужас", false, 14, 4, 1, 0, 0, 0, 4, 4, 0));
        doors.add(new Monster(20010, "Король Тут", true, 16, 4, 2, 4, 3, 0, 0, 0, 0));
        doors.add(new Monster(20011, "Сопливый Нос", false, 10, 3, 1, 0, 0, 0, 0, 0, -6));
        doors.add(new Monster(20012, "Желатиновый Октаэдр", false, 2, 1, 1, 0, 0, 0, 0, 0, 1));
        doors.add(new Monster(20013, "Газебо", false, 8, 2, 1, 0, 0, 0, 0, 0, 0));
        doors.add(new Monster(20014, "Гарпистки", false, 4, 2, 1, 0, 0, 0, 3, 5, 0));
        doors.add(new Monster(20015, "Лепрекон", false, 4, 2, 1, 0, 0, 3, 0, 5, 0));
        doors.add(new Monster(20016, "Питбуль", false, 2, 1, 1, 0, 0, 0, 0, 0, 0));
        doors.add(new Monster(20017, "Пасюк с кувалдой", false, 1, 1, 1, 0, 0, 0, 2, 3, 0));
        doors.add(new Monster(20018, "Типа вошки", false, 1, 1, 1, 0, 0, 0, 0, 0, -6));
        doors.add(new Monster(20019, "Адвокат", false, 6, 2, 1, 2, 5, 0, 0, 0, 0));
        doors.add(new Monster(20020, "Гипогриф", false, 18, 4, 2, 4, 3, 0, 0, 0, 0));
        doors.add(new Monster(20021, "Закос под вампиров", false, 12, 3, 1, 2, 2, 0, 0, 0, 0));
        //Закончил писать монстров на 8 странице

        doors.add(new Races(24002, "Дварф"));
        doors.add(new Races(24003, "Эльф"));
        doors.add(new Races(24004, "Хафлинг"));
        doors.add(new Classes(25004, "Воин"));
        doors.add(new Classes(25002, "Клирик"));//У клирика пассивная способность +3 против андедов
        doors.add(new Classes(25005, "Вор"));//имеет шанс 50% получить дополнительное сокровище за убийство монстра
    }

    public static void reset()
    {
        doors.clear();
    }

    public static void update()
    {
        doors = spareDoorsDeck;
    }
}
