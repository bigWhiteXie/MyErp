package com.codeXie.service;

import com.codeXie.pojo.Duty;
import com.codeXie.pojo.EmpCondition;
import com.codeXie.utils.PageBean;

import java.util.List;

public interface DutyService {
    int signIn(Duty duty);

    int signOut(Duty duty);

    Duty selectOne(Duty duty);

    PageBean<Duty> selectPage(EmpCondition condition);

    List<Duty> selectAll();

    PageBean<Duty> myDuty(EmpCondition condition);

}
