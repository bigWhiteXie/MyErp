package com.codeXie.service;

import com.codeXie.pojo.Expense;
import com.codeXie.pojo.ExpenseItem;

import java.util.List;

public interface ExpenseService {
    int addExpense(Expense expense, List<ExpenseItem> list);

    List<Expense> selectAll(String empid);
}
