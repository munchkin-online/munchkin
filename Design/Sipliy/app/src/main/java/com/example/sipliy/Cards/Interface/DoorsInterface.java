package com.example.sipliy.Cards.Interface;

public interface DoorsInterface
{
    //интерфейс для связи карт дверей
    int getID();
    int getType();  // 1 - класс, 2 - расы, 3 - монстры, 4 - проклятья.
    String getName();
}
