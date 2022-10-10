package com.example.crud.Database;

import java.util.ArrayList;

public interface IConnection {

     ArrayList<Picture> select();

    Picture selectOne(int id);

    int insert(Picture picture);

    int update(Picture picture);

    int delete(int id);
}
