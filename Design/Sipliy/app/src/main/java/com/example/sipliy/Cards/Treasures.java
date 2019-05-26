package com.example.sipliy.Cards;

import com.example.sipliy.Cards.Interface.TreasuresInterface;
import com.example.sipliy.R;

import java.util.ArrayList;
import java.util.Random;


public class Treasures //класс карт сокровищ (общая колода с картами сокровищ)
{
    private static Random random;
    private static ArrayList<TreasuresInterface> items; //колода сокровищ
    private static ArrayList<TreasuresInterface> spareItemsDeck;   //запосная колода, для восстановления после закрытия активити

    public Treasures()
    {
        items = new ArrayList<>();
        fill();
        spareItemsDeck = items;
        random = new Random();
    }

    public static TreasuresInterface getItemCard()   //удаление карты из колоды с целью вставки ее в колоду игрока
    {
        if(items.size() == 0)
        {
            return null;
        }
        else
        {
            return items.remove(random.nextInt(items.size() - 1));
        }
    }

    public static ArrayList<TreasuresInterface> getAllTreasures()
    {
        return items;
    }

    public void fill()
    {
        items.add(new Items(11001, "Шлем бесстрашия", 1, 1, 1, 1, 1, 200,
                R.drawable.d31001, R.drawable.d11001));
        items.add(new Items(11002, "Кожаный прикид",1,  2, 1, 1, 1, 200,
                R.drawable.d31002, R.drawable.d11002));
        items.add(new Items(11003, "Бензопила Кровавого расчленения",3,  5, 1, 1, 1,
                600, R.drawable.d31003, R.drawable.d11003));
        items.add(new Items(11004, "Посох Напалма",5,  4, 1, 3, 1, 800,
                R.drawable.d31004, R.drawable.d11004));
        items.add(new Items(11005, "Колотушка резкости",4,  4, 1, 2, 1, 600,
                R.drawable.d31005, R.drawable.d11005));
        items.add(new Items(11006, "Одиннадцатифутовый кий",1,  5, 1, 1, 1, 200,
                R.drawable.d31006, R.drawable.d11006));
        items.add(new Items(11007, "Шлем-Рогач",1,  1, 3, 1, 1, 600, 3,
                R.drawable.d31007, R.drawable.d11007));
        items.add(new Items(11008, "Терка Умиротворения",3,  4, 1, 2, 1, 400,
                R.drawable.d31008, R.drawable.d11008));
        items.add(new Items(11009, "Кинжал Измены",3,  4, 1, 5, 1, 400,
                R.drawable.d31009, R.drawable.d11009));
        items.add(new Items(11010, "Башмаки Могучего Пендаля",2,  3, 1, 1, 1, 400,
                R.drawable.d31010, R.drawable.d11010));
        items.add(new Items(11011, "Доспехи Поперек-себя-шире",3,  2, 2, 1, 1, 400, R.drawable.d31011,R.drawable.d11011));
        items.add(new Items(11012, "Вездешний Щит",4,  4, 1, 4, 1, 600, R.drawable.d31012,R.drawable.d11012));
        items.add(new Items(11013, "Колготки Великанской Силы",3,  6, 1, 1, 1, 600, R.drawable.d31013,R.drawable.d11013));
        items.add(new Items(11014, "Пафосный Баклер",2,  4, 1, 1, 1, 400, R.drawable.d31014,R.drawable.d11014));
        items.add(new Items(11015, "Небоись-Бандана",3,  1, 5, 1, 1, 400, R.drawable.d31015,R.drawable.d11015));
        items.add(new Items(11016, "Боевая стремянка",3,  6, 4, 1, 1, 400, R.drawable.d31016,R.drawable.d11016));
        items.add(new Items(11017, "Огромная Каменюга",3,  5, 1, 1, 1, 0, R.drawable.d31017,R.drawable.d11017));
        items.add(new Items(11018, "Плащ Замутнения",4,  6, 1, 5, 1, 600, R.drawable.d31018,R.drawable.d11018));
        items.add(new Items(11019, "Склизкие Доспехи",1,  2, 1, 1, 1, 200, R.drawable.d31019,R.drawable.d11019));
        items.add(new Items(11020, "Лучок С Ленточками",4,  5, 3, 1, 1, 800, R.drawable.d31020,R.drawable.d11020));
        items.add(new Items(11021, "Меч Коварного Бастарда",2,  4, 1, 1, 1, 400, R.drawable.d31021,R.drawable.d11021));
        items.add(new Items(11022, "Острые коленки",1,  6, 1, 1, 1, 200, R.drawable.d31022,R.drawable.d11022));
        items.add(new Items(11023, "Меч Широты Взглядов",3,  4, 1, 1, 3, 400, R.drawable.d31023,R.drawable.d11023));
        items.add(new Items(11024, "Дуб Джентельменов",3,  4, 1, 1, 2, 400, R.drawable.d31024,R.drawable.d11024));
        items.add(new Items(11025, "Меч Песни и Пляски",2,  6, 1, 1, 1, 400, R.drawable.d31025,R.drawable.d11025));
        items.add(new Items(11026, "Коленеотбойный Молоточек",4,  4, 2, 1, 1, 600, R.drawable.d31026,R.drawable.d11026));
        items.add(new Items(11027, "Сэндвич Запоздалого Прозрения с сыром и селедкой",3,  6, 4, 1, 1, 400, R.drawable.d31027,R.drawable.d11027));
        items.add(new Items(11028, "Пойнтовая Шляпа",3,  1, 1, 3, 1, 400, R.drawable.d31028,R.drawable.d11028));
        items.add(new Items(11029, "Рапира Такнечестности",3,  4, 3, 1, 1, 600, R.drawable.d31029,R.drawable.d11029));
        items.add(new Items(11030, "Швейцарская Армейская Алебарда",4,  5, 5, 1, 1, 600, R.drawable.d31030,R.drawable.d11030));
        items.add(new Items(11031, "Мифрильные Доспехи",3,  2, 1, 1, 1, 600, R.drawable.d31031,R.drawable.d11031));
        items.add(new Items(11032, "Реально Конкретный Титул",3,  6, 1, 1, 1, 0, R.drawable.d31032,R.drawable.d11032));
        items.add(new Items(11033, "Паленые Доспехи",2,  2, 1, 1, 1, 400, R.drawable.d31033,R.drawable.d11033));
    }

    public static void reset()
    {
        items.clear();
    }

    public static void update()
    {
        items = spareItemsDeck;
    }

    public static int getSize()
    {
        return items.size();
    }

}
