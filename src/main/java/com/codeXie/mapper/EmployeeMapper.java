package com.codeXie.mapper;

import com.codeXie.pojo.EmpCondition;
import com.codeXie.pojo.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface EmployeeMapper {
    @Select("select * from employee where empid=#{param1} and password=#{param2}")
    public Employee selectOne(String ename,String pwd);

    public List<Employee> selectPage(EmpCondition condition);

    @Insert("insert into employee values(#{empid},#{password},#{deptno},#{posid}," +
            "#{mgrid},#{realname},#{sex},#{birthdate},#{hiredate},#{leavedate}," +
            "#{onduty},#{emptype},#{phone},#{qq},#{emercontactperson},#{idcard})")
    public int addEmp(Employee employee);

    public int empCount(EmpCondition condition);

    @Select("select * from employee where emptype=2")
    public List<Employee> selectManager();

    Employee selectUpdate(String empid);

    @Update("update employee set deptno=#{deptno}," +
            "mgrid=#{mgrid},realname=#{realname},sex=#{sex},birthdate=#{birthdate},hiredate=#{hiredate},leavedate=#{leavedate}," +
            "onduty=#{onduty},phone=#{phone},qq=#{qq},emercontactperson=#{emercontactperson},idcard=#{idcard} where empid=#{empid} ")
    int updateEmp(Employee employee);

    @Delete("delete from employee where empid=#{param1}")
    int delEmp(String empid);

    @Update("update employee set password='123' where empid=#{param1} ")
    int resetPwd(String empid);


}
