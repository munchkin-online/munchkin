package com.example.sipliy.Cards;

import com.example.sipliy.Cards.Interface.TreasuresInterface;
import com.example.sipliy.R;

import java.util.ArrayList;


public class Treasures //класс карт сокровищ (общая колода с картами сокровищ)
{
    private static ArrayList<TreasuresInterface> items; //колода сокровищ
    private static ArrayList<TreasuresInterface> spareItemsDeck;   //запосная колода, для восстановления после закрытия активити

    public Treasures()
    {
        items = new ArrayList<>();
        fill();
        spareItemsDeck = items;
    }

    public static TreasuresInterface getItemCard()   //удаление карты из колоды с целью вставки ее в колоду игрока
    {
        if(items.size() == 0)
        {
            return null;
        }
        else
        {
            return items.remove(items.size() - 1);
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

        /*items.add(new Items(11011, "Доспехи Поперек-себя-шире",3,  2, 2, 1, 1, 400, R.drawable.d11011));
        items.add(new Items(11012, "Вездешний Щит",4,  4, 1, 4, 1, 600, R.drawable.d11012));
        items.add(new Items(11013, "Колготки Великанской Силы",3,  6, 1, 1, 1, 600, R.drawable.d11013));
        items.add(new Items(11014, "Пафосный Баклер",2,  4, 1, 1, 1, 400, R.drawable.d11014));
        items.add(new Items(11015, "Небоись-Бандана",3,  1, 5, 1, 1, 400, R.drawable.d11015));
        //items.add(new Items(11016, "Небоись-Бандана",3,  1, 5, 1, 1, 400, R.drawable.d11016));*/
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
