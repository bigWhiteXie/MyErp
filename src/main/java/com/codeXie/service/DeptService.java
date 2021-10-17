package com.codeXie.service;

import com.codeXie.pojo.Dept;
import com.codeXie.utils.PageBean;

import java.util.List;

public interface DeptService {
    int addDept(Dept dept);

    PageBean<Dept> selectPage(int index, int size);

    int delDept(int deptno);

    Dept selectOne(int deptno);

    int update(Dept dept);

    List<Dept> selectAll();
}
