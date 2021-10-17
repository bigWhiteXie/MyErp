package com.codeXie.mapper;

import com.codeXie.pojo.Expense;
import com.codeXie.pojo.ExpenseItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import java.util.List;

public interface ExpenseMapper {
    @Options(useGeneratedKeys = true, keyProperty = "expid", keyColumn = "expid")
    @Insert("insert into expense values(default,#{empid},#{totalamount},#{exptime},#{expdesc},#{nextauditor},#{lastResult},#{status})")
    int addExpense(Expense expense);

    @Insert("insert into expenseitem values(default,#{expid},#{type},#{amount},#{itemdesc}) ")
    int addExpenseItem(ExpenseItem item);

    List<Expense> selectAll(String empid);
}
