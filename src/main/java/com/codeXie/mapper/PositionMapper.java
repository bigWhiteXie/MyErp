package com.codeXie.mapper;

import com.codeXie.pojo.Dept;
import com.codeXie.pojo.Position;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PositionMapper {
    @Insert("insert into position values(#{posid},#{pname},#{pdesc})")
    public int addPosition(Position position);

    @Select("select * from position limit #{param1},#{param2}")
    public List<Position> selectPage(int start,int size);

    @Select("select count(0) from position")
    public int count();

    @Delete("delete from position where posid=#{param1}")
    public int deleteOne(int posid);

    @Select("select * from position where posid=#{param1}")
    public Position selectOne(int posid);

    @Update("update position set pname=#{pname},pdesc=#{pdesc} where posid=#{posid}")
    public int upDateOne(Position position);

    @Select("select * from position")
    public List<Position> selectAll();
}
