package com.codeXie.service;

import com.codeXie.pojo.EmpCondition;
import com.codeXie.pojo.Employee;
import com.codeXie.utils.PageBean;

import java.util.List;

public interface EmployeeService {
    /**
     * 用于员工登录
     * @param ename 用户名
     * @param pwd 密码
     * @return
     */
    public Employee selectOne(String ename,String pwd);

    /**
     * 添加员工
     * @param employee
     * @return
     */
    public int addEmp(Employee employee);

    /**
     * 分页查询
     * @param condition 分页查询条件
     * @return
     */
    public PageBean<Employee> selectPage(EmpCondition condition);

    List<Employee> selectManager();

    Employee selectUpdate(String empid);

    int updateEmp(Employee employee);

    int delEmp(String empid);

    int resetPwd(String empid);

}
