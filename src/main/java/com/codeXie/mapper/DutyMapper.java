package com.codeXie.mapper;

import com.codeXie.pojo.Duty;
import com.codeXie.pojo.EmpCondition;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DutyMapper {
    @Insert("insert into duty values(default,#{emprid},#{dtdate},#{signintime},#{signouttime})")
    public int signInMapper(Duty duty);

    @Update("update duty set signouttime=#{signouttime} where emprid=#{emprid} and dtdate=#{dtdate} ")
    public int signOutMapper(Duty duty);

    @Select("select * from duty where  emprid=#{emprid} and dtdate=#{dtdate}")
    public Duty selectOne(Duty duty);

    List<Duty> dutyManageMapper(EmpCondition condition);

    @Select("select count(0) from duty")
    int selectCount();

    List<Duty> selectAll();

    @Select("select * from duty where emprid=#{empid} limit #{start},#{size}")
    List<Duty> myDuty(EmpCondition condition);

    @Select("select count(0) from duty where emprid=#{param1}")
    int myDutyCount(String empid);

}
