package com.codeXie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@AllArgsConstructor
@NoArgsConstructor
@Data//生成get/set方法以及toString方法，以及hashCode和equals方法
public class Duty implements Serializable {
    private Integer dtid;
    private  String emprid;
    private String dtdate;
    private String signintime;
    private String signouttime;

    Employee employee;
    Dept dept;
}
