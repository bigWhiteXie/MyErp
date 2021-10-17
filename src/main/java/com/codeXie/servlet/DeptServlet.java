package com.codeXie.servlet;

import com.codeXie.pojo.Dept;
import com.codeXie.service.DeptService;
import com.codeXie.service.empl.DeptServiceImpl;
import com.codeXie.utils.PageBean;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/DeptServlet")
public class DeptServlet extends BaseServlet{
    public void addDept(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String depno = req.getParameter("deptno");
        String depName = req.getParameter("deptname");
        String location = req.getParameter("location");

        Dept dept = new Dept(Integer.parseInt(depno), depName, location);
        DeptServiceImpl service = new DeptServiceImpl();
        int i = service.addDept(dept);
        System.out.println(i);
        if(i>=0){
            resp.getWriter().print(" var msg='ok'");
        }else{
            resp.getWriter().print(" var msg='fail'");
        }
    }

    public void findPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String index = req.getParameter("index");
        String size = req.getParameter("size");

        DeptService service = new DeptServiceImpl();
        PageBean<Dept> pageBean = service.selectPage(Integer.parseInt(index), Integer.parseInt(size));
        String json = new Gson().toJson(pageBean);
        resp.getWriter().print(json);
    }

    public void delDept(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String deptno = req.getParameter("deptno");
        DeptService service = new DeptServiceImpl();
        int i = service.delDept(Integer.parseInt(deptno));
        if(i>0){
            resp.getWriter().print("var msg = 'ok'");
        }else{
            resp.getWriter().print("var msg = 'false'");
        }
    }

    public void updateDept(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String deptno = req.getParameter("deptno");
        DeptService service = new DeptServiceImpl();
        Dept dept = service.selectOne(Integer.parseInt(deptno));
        if(dept != null){
            req.getSession().setAttribute("updateDept", dept);
            resp.getWriter().print("msg = 'ok'");
        }else{
            resp.getWriter().print("msg = 'false'");
        }
    }

    public void updateNow(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Dept dept = (Dept) req.getSession().getAttribute("updateDept");
        String json = new Gson().toJson(dept);
        resp.getWriter().print(json);

    }

    public void saveDept(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String deptno = req.getParameter("deptno");
        String deptname = req.getParameter("deptname");
        String location = req.getParameter("location");
        Dept dept = new Dept(Integer.parseInt(deptno), deptname, location);
        DeptService service = new DeptServiceImpl();
        int i = service.update(dept);
        if(i>0){
            resp.getWriter().print("var msg='ok'");
        }else{
            resp.getWriter().print("var msg='false'");
        }
    }

    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;utf-8");
        DeptService service = new DeptServiceImpl();
        List<Dept> depts = service.selectAll();
        resp.getWriter().print(new Gson().toJson(depts));
    }

}
