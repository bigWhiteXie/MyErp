package com.codeXie.service.empl;

import com.codeXie.mapper.ExpenseMapper;
import com.codeXie.pojo.Expense;
import com.codeXie.pojo.ExpenseItem;
import com.codeXie.service.ExpenseService;
import com.codeXie.utils.DBUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ExpenseServiceImpl implements ExpenseService {
    //添加报销及报销细节
    @Override
    public int addExpense(Expense expense, List<ExpenseItem> list) {
        int rs = 1;
        SqlSession sqlSession = DBUtil.getSqlSession();
        ExpenseMapper mapper = sqlSession.getMapper(ExpenseMapper.class);
        int i = mapper.addExpense(expense);
        if(i <= 0){
            rs = -2;
        }
        for(ExpenseItem item:list){
            item.setExpid(expense.getExpid());
            int i1 = mapper.addExpenseItem(item);
            if(i1 <= 0){
                rs = -1;
            }
        }
        sqlSession.commit();
        DBUtil.closeAll();
        return rs;
    }
    //查询所有报销
    @Override
    public List<Expense> selectAll(String empid) {
        SqlSession sqlSession = DBUtil.getSqlSession();
        ExpenseMapper mapper = sqlSession.getMapper(ExpenseMapper.class);
        List<Expense> expenses = mapper.selectAll(empid);
        DBUtil.closeAll();
        return expenses;
    }
}
