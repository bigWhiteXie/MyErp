package com.codeXie.servlet;

import com.codeXie.pojo.EmpCondition;
import com.codeXie.pojo.Employee;
import com.codeXie.pojo.Position;
import com.codeXie.service.EmployeeService;
import com.codeXie.service.PositionService;
import com.codeXie.service.empl.EmpServiceImpl;
import com.codeXie.service.empl.PositionServiceImpl;
import com.codeXie.utils.PageBean;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends BaseServlet{
    public void addEmployee(HttpServletRequest req, HttpServletResponse resp) throws IOException, ParseException {
        String empid = req.getParameter("empid");
        String realname = req.getParameter("realname");
        String sex = req.getParameter("sex");
        String birthdate = req.getParameter("birthdate");
        String hiredate = req.getParameter("hiredate");
        String leavedate = req.getParameter("leavedate");
        String onduty = req.getParameter("onduty");
        String emptype = req.getParameter("emptype");
        String deptno = req.getParameter("deptno");
        String posid = req.getParameter("posid");
        String mgrid = req.getParameter("mgrid");
        String phone = req.getParameter("phone");
        String qq = req.getParameter("qq");
        String emercontactperson = req.getParameter("emercontactperson");
        String idcard = req.getParameter("idcard");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Employee employee = new Employee(empid, "123", Integer.parseInt(deptno), Integer.parseInt(posid), mgrid, realname, sex,
                birthdate, hiredate, leavedate, Integer.parseInt(onduty), Integer.parseInt(emptype),
                phone, qq, emercontactperson, idcard, null, null,null);
        System.out.println(employee);
        EmployeeService service = new EmpServiceImpl();
        int i = service.addEmp(employee);
        System.out.println(i);
        if(i>=0){
            resp.getWriter().print(" var msg='ok'");
        }else{
            resp.getWriter().print(" var msg='fail'");
        }
    }
    public void findManager(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        EmployeeService service = new EmpServiceImpl();
        List<Employee> employees = service.selectManager();
        resp.getWriter().print(new Gson().toJson(employees));
    }
    public void findPage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ParseException {
        resp.setContentType("text/html;utf-8");
        String index = req.getParameter("index");
        String size = req.getParameter("size");
        String realname = req.getParameter("realname");
        String deptno = req.getParameter("deptno");
        String onduty = req.getParameter("onduty");
        String hiredate = req.getParameter("hiredate");
        SimpleDateFormat format = new SimpleDateFormat();
        EmpCondition condition = new EmpCondition(null,Integer.parseInt(index),Integer.parseInt(size),realname,deptno,onduty,hiredate,null,null);
        EmployeeService service = new EmpServiceImpl();
        PageBean<Employee> employeePageBean = service.selectPage(condition);
        resp.getWriter().print(new Gson().toJson(employeePageBean));

    }
    public void updateEmp(HttpServletRequest req, HttpServletResponse resp) throws IOException, ParseException {
        String empid = req.getParameter("empid");
        System.out.println(empid);
        EmployeeService service = new EmpServiceImpl();
        Employee employee = service.selectUpdate(empid);
        if(employee != null){
            System.out.println(employee);
            req.getSession().setAttribute("empInfo",employee);
            resp.getWriter().print("var msg ='ok'");
        }else{
            resp.getWriter().print("var msg = 'false'");
        }

    }
    public void updateInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException, ParseException {
        resp.setContentType("text/html;utf-8");
        Employee empInfo = (Employee) req.getSession().getAttribute("empInfo");
        resp.getWriter().print(new Gson().toJson(empInfo));
    }

    public void updateSave(HttpServletRequest req, HttpServletResponse resp) throws IOException, ParseException {
        String empid = req.getParameter("empid");
        String realname = req.getParameter("realname");
        String sex = req.getParameter("sex");
        String birthdate = req.getParameter("birthdate");
        String hiredate = req.getParameter("hiredate");
        String leavedate = req.getParameter("leavedate");
        String onduty = req.getParameter("onduty");
        String emptype = req.getParameter("emptype");
        String deptno = req.getParameter("deptno");
        String posid = req.getParameter("posid");
        String mgrid = req.getParameter("mgrid");
        String phone = req.getParameter("phone");
        String qq = req.getParameter("qq");
        String emercontactperson = req.getParameter("emercontactperson");
        String idcard = req.getParameter("idcard");
        Employee employee = new Employee(empid, null, Integer.parseInt(deptno), null, mgrid, realname, sex,
                birthdate, hiredate, leavedate, Integer.parseInt(onduty), null,
                phone, qq, emercontactperson, idcard, null, null,null);
        System.out.println(employee);
        EmployeeService service = new EmpServiceImpl();
        int i = service.updateEmp(employee);
        if(i>0){
            resp.getWriter().print("var msg='ok'");
        }else{
            resp.getWriter().print("var msg='false'");
        }
    }

    public void delEmp(HttpServletRequest req, HttpServletResponse resp) throws IOException, ParseException {
        String empid = req.getParameter("empid");
        EmployeeService service = new EmpServiceImpl();
        int i = service.delEmp(empid);
        if(i>0){
            resp.getWriter().print("msg='ok'");
        }else {
            resp.getWriter().print("msg='false'");

        }
    }
    public void resetPwd(HttpServletRequest req, HttpServletResponse resp) throws IOException, ParseException {
        String empid = req.getParameter("empid");
        EmployeeService service = new EmpServiceImpl();
        int i = service.resetPwd(empid);
        if(i>0){
            resp.getWriter().print("msg='ok'");
        }else {
            resp.getWriter().print("msg='false'");

        }
    }
}
