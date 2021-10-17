package com.codeXie.servlet;

import com.codeXie.pojo.Dept;
import com.codeXie.pojo.Position;
import com.codeXie.service.DeptService;
import com.codeXie.service.PositionService;
import com.codeXie.service.empl.DeptServiceImpl;
import com.codeXie.service.empl.PositionServiceImpl;
import com.codeXie.utils.PageBean;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/PositionServlet")
public class PositionServlet extends BaseServlet{
    public void addPosition(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String posid = req.getParameter("posid");
        String pname = req.getParameter("pname");
        String pdesc = req.getParameter("pdesc");

        Position position = new Position(Integer.parseInt(posid), pname, pdesc);
        PositionService service = new PositionServiceImpl();
        int i = service.addPosition(position);
        System.out.println(i);
        if(i>=0){
            resp.getWriter().print(" var msg='ok'");
        }else{
            resp.getWriter().print(" var msg='fail'");
        }
    }

    public void findPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("html/text;utf-8");
        String index = req.getParameter("index");
        String size = req.getParameter("size");

        PositionService service = new PositionServiceImpl();
        PageBean<Position> pageBean = service.selectPage(Integer.parseInt(index), Integer.parseInt(size));
        String json = new Gson().toJson(pageBean);
        resp.getWriter().print(json);
    }

    public void removeItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String posid = req.getParameter("posid");

        PositionService service = new PositionServiceImpl();
        int i = service.delPosition(Integer.parseInt(posid));
        if(i>0){
            resp.getWriter().print("var msg = 'ok'");
        }else{
            resp.getWriter().print("var msg = 'false'");
        }
    }

    public void updatePos(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String posid = req.getParameter("posid");
        PositionService service = new PositionServiceImpl();
        Position position = service.selectPos(Integer.parseInt(posid));
        req.getSession().setAttribute("position",position);
        resp.getWriter().print("var msg = 'ok'");
    }

    public void updateInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        Position position = (Position)req.getSession().getAttribute("position");
        String json = new Gson().toJson(position);
        resp.getWriter().print(json);
    }
    public void updateSave(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String posid = req.getParameter("posid");
        String pname = req.getParameter("pname");
        String pdesc = req.getParameter("pdesc");
        Position position = new Position(Integer.parseInt(posid), pname, pdesc);
        PositionService service = new PositionServiceImpl();
        int i = service.updatePos(position);
        if(i>0) {
            resp.getWriter().print("var msg = 'ok'");
        }else{
            resp.getWriter().print("var msg = 'false'");

        }
    }

    public void findAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PositionService service = new PositionServiceImpl();
        List<Position> depts = service.selectAll();
        resp.getWriter().print(new Gson().toJson(depts));
    }
}
