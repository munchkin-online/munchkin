package com.example.sipliy.Cards.Interface;

public interface DoorsInterface
{
    //интерфейс для связи карт дверей
    int getID();
    int getType();  // 1 - класс, 2 - расы, 3 - монстры, 4 - проклятья, 5 - разовые шмотки, 6 - шмотки, 7 - LevelUp(хз что это)
    String getName();
}
