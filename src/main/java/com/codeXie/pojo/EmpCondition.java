package com.codeXie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmpCondition implements Serializable {
    private Integer start = null;
    private Integer index = null;
    private Integer size = null;
    private String name = null;
    private String deptno = null;
    private String onduty = null;
    private String hiredate = null;
    private String empid = null;
    private String dtdate = null;
}
