package com.example.sipliy.Cards;

import java.util.ArrayList;

public class Doors
{
    private static ArrayList<Object> doors; //колода дверей

    public Doors()
    {
        doors = new ArrayList<>();
        fill();
    }

    public static Object getItemCard()
    {
        return doors.remove(doors.size() - 1);
    }

    public void fill()
    {
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

}
