package com.codeXie.service;

import com.codeXie.pojo.Position;
import com.codeXie.utils.PageBean;

import java.util.List;

public interface PositionService {
    public int addPosition(Position position);

    public PageBean<Position> selectPage(int index,int size);

    public int delPosition(int posid);

    public Position selectPos(int posid);

    public int updatePos(Position position);

    List<Position> selectAll();

}
