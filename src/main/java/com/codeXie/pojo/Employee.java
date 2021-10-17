package com.codeXie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data//生成get/set方法以及toString方法，以及hashCode和equals方法
public class Employee {
    private String empid;
    private String password;
    private Integer deptno;
    private Integer posid;
    private String mgrid;
    private String realname;
    private String sex;
    private String birthdate;
    private String hiredate;
    private String leavedate;
    private Integer onduty;
    private Integer emptype;
    private String phone;
    private String qq;
    private String emercontactperson;
    private String idcard;

    private Dept dept = null;
    private Position position = null;
    private Employee mgr = null;

}
