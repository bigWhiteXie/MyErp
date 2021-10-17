package com.codeXie.mapper;

import com.codeXie.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface DeptMapper {
    @Insert("insert into dept values(#{deptno},#{deptname},#{location})")
    public int addDept(Dept dept);

    @Select("select * from dept limit #{param1},#{param2}")
    public List<Dept> selectList(int start,int size);

    @Select("select count(0) from dept")
    public int count();

    @Delete("delete from dept where deptno=#{deptno}")
    public int deleteDept(@Param("deptno") int deptno);

    @Select("select * from dept where deptno=#{deptno}")
    public Dept selectOne(@Param("deptno") int deptno);

    @Update("update dept set deptname=#{deptname},location=#{location} where deptno=#{deptno}")
    public int updateDept(Dept dept);
    @Select("select * from dept")
    public List<Dept> selectAll();
}
