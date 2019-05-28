package com.example.sipliy.Cards;

import com.example.sipliy.Cards.Interface.DoorsInterface;
import com.example.sipliy.R;

import java.util.ArrayList;
import java.util.Random;

public class Doors
{
    private static Random random;
    private static ArrayList<DoorsInterface> doors; //колода дверей
    private static ArrayList<DoorsInterface> spareDoorsDeck;
    public Doors()
    {
        doors = new ArrayList<>();
        fill();
        spareDoorsDeck = doors;
        random = new Random();
    }

    public static DoorsInterface getItemCard()
    {
        if(doors.size() == 0)
        {
            return null;
        }
        else if(doors.size() == 1)
        {
            return doors.remove(0);
        }
        else
        {
            return doors.remove(random.nextInt(doors.size() - 1));
        }
    }

    public static DoorsInterface getItem()
    {
        if(doors.size() == 0)
        {
            return null;
        }
        else
        {
            return doors.get(doors.size() - 1);
        }
    }
    public void fill()
    {
        //not_beating_with: 1 - рассовый признак, 2 - классовый признак, 3 - половой признак, 4 - уровневый признак
        doors.add(new Monster(20001, "Кальмадзилла", false, 18, 4, 2, 4, 4, 3, 0, 4, 0, R.drawable.d20001));
        doors.add(new Monster(20002, "3872 Орка", false, 10, 3, 1, 0, 0, 2, 0, 6, 0, R.drawable.d20002));
        doors.add(new Monster(20003, "Бульрог", false, 18, 5, 2, 4, 4, 0, 0, 0, 0, R.drawable.d20003));
        doors.add(new Monster(20004, "Амазонка", false, 8, 2, 1, 3, 2, 0, 0, 0, 0, R.drawable.d20004));
        doors.add(new Monster(20005, "Просто Тролль", false, 10, 3, 1, 0, 0, 0, 0, 0, 0, R.drawable.d20005));
        doors.add(new Monster(20006, "Рыгачу", false, 6, 2, 1, 0, 0, 0, 0, 0, 0, R.drawable.d20006));
        doors.add(new Monster(20007, "Лицесос", false, 8, 2, 1, 0, 0, 3, 0, 6, 0, R.drawable.d20007));
        doors.add(new Monster(20008, "Наскипидаренные улитки", false, 4, 2, 1, 0, 0, 3, 0, 0, -2, R.drawable.d20008));
        doors.add(new Monster(20009, "Невыразимо Жуткий Неописуемый Ужас", false, 14, 4, 1, 0, 0, 0, 4, 4, 0, R.drawable.d20009));
        doors.add(new Monster(20010, "Король Тут", true, 16, 4, 2, 4, 3, 0, 0, 0, 0, R.drawable.d20010));
        doors.add(new Monster(20011, "Сопливый Нос", false, 10, 3, 1, 0, 0, 0, 0, 0, -6, R.drawable.d20011));
        doors.add(new Monster(20012, "Желатиновый Октаэдр", false, 2, 1, 1, 0, 0, 0, 0, 0, 1, R.drawable.d20012));
        doors.add(new Monster(20013, "Газебо", false, 8, 2, 1, 0, 0, 0, 0, 0, 0, R.drawable.d20013));
        doors.add(new Monster(20014, "Гарпистки", false, 4, 2, 1, 0, 0, 0, 3, 5, 0, R.drawable.d20014));
        doors.add(new Monster(20015, "Лепрекон", false, 4, 2, 1, 0, 0, 3, 0, 5, 0, R.drawable.d20015));
        doors.add(new Monster(20016, "Питбуль", false, 2, 1, 1, 0, 0, 0, 0, 0, 0, R.drawable.d20016));
        doors.add(new Monster(20017, "Пасюк с кувалдой", false, 1, 1, 1, 0, 0, 0, 2, 3, 0, R.drawable.d20017));
        doors.add(new Monster(20018, "Типа вошки", false, 1, 1, 1, 0, 0, 0, 0, 0, -6, R.drawable.d20018));
        doors.add(new Monster(20019, "Адвокат", false, 6, 2, 1, 2, 5, 0, 0, 0, 0, R.drawable.d20019));
        doors.add(new Monster(20020, "Гипогриф", false, 18, 4, 2, 4, 3, 0, 0, 0, 0, R.drawable.d20020));
        doors.add(new Monster(20021, "Закос под вампиров", false, 12, 3, 1, 2, 2, 0, 0, 0, 0, R.drawable.d20021));
        doors.add(new Monster(20022, "Лятучие Легушки", false, 2, 1, 1, 0, 0, 0, 0, 0, -1, R.drawable.d20022));
        doors.add(new Monster(20023, "Калечный Гоблин", false, 1, 1, 1, 0, 0, 0, 0, 0, 1, R.drawable.d20023));
        doors.add(new Monster(20024, "Здоровенная Бешеная Цыпа", false, 2, 1, 1, 0, 0, 0, 0, 0, 0, R.drawable.d20024));
        doors.add(new Monster(20025, "Дотошный Ботан", false, 6, 2, 1, 0, 0, 0, 4, 6, 0, R.drawable.d20025));
        doors.add(new Monster(20026, "Трава в Горшке", false, 1, 1, 1, 0, 0, 0, 0, 0, 6, R.drawable.d20026));
        doors.add(new Monster(20027, "Белые Братья", true, 16, 4, 2, 4, 3, 0, 0, 0, 0, R.drawable.d20027));
        doors.add(new Monster(20028, "Плутониевый Дракон", false, 20, 5, 2, 4, 5, 0, 0, 0, 0, R.drawable.d20028));
        doors.add(new Monster(20029, "Обдолбанный Голем", false, 14, 4, 1, 0, 0, 0, 0, 0, 6, R.drawable.d20029));
        doors.add(new Monster(20030, "Демон с Язычком", false, 12, 3, 1, 0, 0, 0, 2, 4, 0, R.drawable.d20030));
        doors.add(new Monster(20031, "Утикора", false, 6, 2, 1, 0, 0, 0, 3, 6, 0, R.drawable.d20031));
        doors.add(new Monster(20032, "Бигфут", false, 12, 3, 1, 0, 0, 2, 0, 3, 0, R.drawable.d20032));
        doors.add(new Monster(20033, "Сочащаяся Слизь", false, 1, 1, 1, 0, 0, 3, 0, 4, 0, R.drawable.d20033));
        doors.add(new Monster(20034, "Г-н Кости", true, 2, 1, 1, 0, 0, 0, 0, 0, 0, R.drawable.d20034));
        doors.add(new Monster(20035, "Неживой Коник", true, 4, 2, 1, 0, 0, 2, 0, 5, 0, R.drawable.d20035));
        doors.add(new Monster(20036, "Бабыри", true, 8, 2, 1, 0, 0, 0, 0, 0, 0, R.drawable.d20036));
        //Закончил писать монстров на 8 странице

        doors.add(new Races(24001, "Дварф", R.drawable.d24002, 2));  //раса носителя (1-human, 2-dwarf, 3-elf, 4-hufling)
        doors.add(new Races(24002, "Дварф", R.drawable.d24002_2, 2));
        doors.add(new Races(24003, "Эльф", R.drawable.d24003, 3));
        doors.add(new Races(24004, "Эльф", R.drawable.d24003_2, 3));
        doors.add(new Races(24005, "Хафлинг", R.drawable.d24004, 4));
        doors.add(new Races(24006, "Хафлинг", R.drawable.d24004_2, 4));
        doors.add(new Classes(25001, "Воин", R.drawable.d25004, 4)); //класс носителя (2-cleric, 3-wizard, 4-warrior, 5-thief)
        doors.add(new Classes(25002, "Воин", R.drawable.d25004_2, 4));
        doors.add(new Classes(25003, "Клирик", R.drawable.d25002, 2));//У клирика пассивная способность +3 против андедов
        doors.add(new Classes(25004, "Клирик", R.drawable.d25002_2, 2));
        doors.add(new Classes(25005, "Вор", R.drawable.d25005, 5));//имеет шанс 50% получить дополнительное сокровище за убийство монстра
        doors.add(new Classes(25006, "Вор", R.drawable.d25005_2, 5));
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
