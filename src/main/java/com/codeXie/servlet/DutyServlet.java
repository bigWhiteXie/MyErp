package com.codeXie.servlet;

import com.codeXie.pojo.Duty;
import com.codeXie.pojo.EmpCondition;
import com.codeXie.pojo.Employee;
import com.codeXie.service.DutyService;
import com.codeXie.service.EmployeeService;
import com.codeXie.service.empl.DutyServiceImpl;
import com.codeXie.service.empl.EmpServiceImpl;
import com.codeXie.utils.ExcelUtil;
import com.codeXie.utils.PageBean;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/DutyServlet")
public class DutyServlet extends BaseServlet {
    //签到功能
    public void DutySignIn(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Employee emp = (Employee) req.getSession().getAttribute("emp");
        System.out.println(emp);
        String emprid = emp.getEmpid();
        Date date = new Date();
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dtdate = dataFormat.format(date);
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String time = timeFormat.format(date);
        Duty duty = new Duty(null, emprid, dtdate, time, null,null,null);
        DutyService service = new DutyServiceImpl();
        Duty duty1 = service.selectOne(duty);
        if(duty1!=null){
            resp.getWriter().print("请勿重复签到");
        }else{
            service.signIn(duty);
            resp.getWriter().print("签到成功");
        }

    }
    //签退功能
    public void DutySignOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Employee emp = (Employee) req.getSession().getAttribute("emp");
        String emprid = emp.getEmpid();
        Date date = new Date();
        //获取日期
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dtdate = dataFormat.format(date);
        //获取时间
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String time = timeFormat.format(date);
        //存储员工id和日期的duty
        Duty duty = new Duty(null, emprid, dtdate, null, time,null,null);
        DutyService service = new DutyServiceImpl();
        Duty duty1 = service.selectOne(duty);
        //若没有找到当天的签到则让客户端先进行签到
        if(duty1==null){
            resp.getWriter().print("请先签到");
        }else{
            int i = service.signOut(duty);
            resp.getWriter().print("签退成功");
        }

    }

    //条件、分页查询
    public void findPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String empid = req.getParameter("empid");
        String deptno = req.getParameter("deptno");
        String dtdate = req.getParameter("dtdate");
        String index = req.getParameter("index");
        String size = req.getParameter("size");

        EmpCondition condition = new EmpCondition();
        condition.setIndex(Integer.parseInt(index));
        condition.setSize(Integer.parseInt(size));
        condition.setEmpid(empid);
        condition.setDeptno(deptno);
        condition.setDtdate(dtdate);
        DutyService service = new DutyServiceImpl();
        PageBean<Duty> dutyPageBean = service.selectPage(condition);

        resp.getWriter().print(new Gson().toJson(dutyPageBean));
    }
    //数据导出成excel
    public void exportExcel(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //1.设置响应类型
        resp.setContentType("application/vnd.ms-excel");
        resp.setHeader("Content-Disposition","attachment;filename=\"duty.xls\"");

        //2.获取请求参数

        //3.得到响应数据
        DutyService service = new DutyServiceImpl();
        List<Duty> duties = service.selectAll();

        //4.做出响应
        ArrayList<String> cols = new ArrayList<>();
        cols.add("用户名");
        cols.add("真实姓名");
        cols.add("所属部门");
        cols.add("出勤日期");
        cols.add("签到时间");
        cols.add("签退时间");
        ExcelUtil.exportExcel(duties,cols,resp.getOutputStream());
    }

    //我的考勤记录
    public void myDuty(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //1.设置响应类型
        resp.setContentType("text/html;utf-8");
        Employee emp = (Employee) req.getSession().getAttribute("emp");
        String empid = emp.getEmpid();
        //2.获取请求参数
        String index = req.getParameter("index");
        String size = req.getParameter("size");
        EmpCondition condition = new EmpCondition();
        condition.setEmpid(empid);
        condition.setIndex(Integer.parseInt(index));
        condition.setSize(Integer.parseInt(size));
        //3.获取请求数据
        DutyService service = new DutyServiceImpl();
        PageBean<Duty> pageBean = service.myDuty(condition);
        //4.做出响应
        resp.getWriter().print(new Gson().toJson(pageBean));
    }
}
