package com.codeXie.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Expense implements Serializable {
    private Integer expid;
    private String empid;
    private Double totalamount;
    private String exptime;
    private String expdesc;
    private String nextauditor;
    private String lastResult;
    private String status;

    private Employee employee;
    private List<ExpenseItem> list;
}
