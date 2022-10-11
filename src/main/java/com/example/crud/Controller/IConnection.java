package com.example.crud.Controller;

import com.example.crud.Model.PictureEntity;

import java.util.List;

public interface IConnection {

     List<PictureEntity> selectAll();

    PictureEntity selectOne(int id);

    int insert(PictureEntity picture);

    int update(PictureEntity picture);

    int delete(int id);
}
