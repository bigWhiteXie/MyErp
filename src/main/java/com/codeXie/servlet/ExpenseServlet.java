package com.codeXie.servlet;

import com.codeXie.pojo.Employee;
import com.codeXie.pojo.Expense;
import com.codeXie.pojo.ExpenseItem;
import com.codeXie.service.ExpenseService;
import com.codeXie.service.empl.ExpenseServiceImpl;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/ExpenseServlet")
public class ExpenseServlet extends BaseServlet {
    public void addExpense(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //1.设置响应类型
        resp.setContentType("text/html;utf-8");

        //2.获得请求参数
        Employee emp = (Employee) req.getSession().getAttribute("emp");
        String[] types = req.getParameterValues("type");
        String[] amounts = req.getParameterValues("amount");
        String[] itemdescs = req.getParameterValues("itemdesc");
        String nextauditor = emp.getMgrid();
        String expdesc = req.getParameter("expdesc");
        String empid = emp.getEmpid();
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String exptime = dateFormat.format(now);

        //3.处理请求参数
        Expense expense = new Expense();
        expense.setEmpid(empid);
        expense.setExpdesc(expdesc);
        expense.setNextauditor(nextauditor);
        expense.setLastResult("无");
        expense.setStatus("待审核");
        double totalAmount = 0.0;
        ArrayList<ExpenseItem> list = new ArrayList<>();
        for(int i=0;i<types.length;i++){
            ExpenseItem item = new ExpenseItem();
            item.setType(types[i]);
            item.setItemdesc(itemdescs[i]);
            double amount = Double.parseDouble(amounts[i]);
            item.setAmount(amount);
            list.add(item);
            totalAmount += amount;
        }
        expense.setTotalamount(totalAmount);
        expense.setExptime(exptime);

        //4.调用service业务层对数据库进行添加数据
        ExpenseService service = new ExpenseServiceImpl();
        int i = service.addExpense(expense, list);

        //5.对客户端做出响应
        if(i>0){
            resp.getWriter().print("ok");
        }else{
            resp.getWriter().print("false");
        }
    }
    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //1.设置响应类型
        resp.setContentType("text/html;utf-8");
        //2.获取请求参数
        Employee emp = (Employee) req.getSession().getAttribute("emp");
        //3.处理请求参数

        //4.调用业务层
        ExpenseService service = new ExpenseServiceImpl();
        List<Expense> expenses = service.selectAll(emp.getEmpid());

        //5.做出响应
        resp.getWriter().print(new Gson().toJson(expenses));
    }
}
