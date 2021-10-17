package com.codeXie.pojo;

import java.io.Serializable;

public class Dept implements Serializable {
    private int deptno;
    private String deptname;
    private String location;

    public Dept() {
    }

    public Dept(int deptno, String deptname, String location) {
        this.deptno = deptno;
        this.deptname = deptname;
        this.location = location;
    }

    public Dept(String deptno, String deptname, String location) {
    }

    public int getDeptno() {
        return deptno;
    }

    public String getDeptname() {
        return deptname;
    }

    public String getLocation() {
        return location;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "deptno=" + deptno +
                ", deptname='" + deptname + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
