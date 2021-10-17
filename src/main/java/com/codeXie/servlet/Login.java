package com.codeXie.servlet;

import com.codeXie.pojo.Employee;
import com.codeXie.service.EmployeeService;
import com.codeXie.service.empl.EmpServiceImpl;
import com.google.gson.Gson;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
@WebServlet("/Login")
public class Login extends BaseServlet {
    public void produceYZM(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 在内存中创建图象
        int width = 110, height = 30;
        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics g = image.getGraphics();
        // 生成随机类
        Random random = new Random();
        // 设定背景色
        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        // 设定字体
        g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        // 取随机产生的认证码(6位数字)
        String sRand = "";
        for (int i = 0; i < 6; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            // 将认证码显示到图象中
            g.setColor(new Color(20 + random.nextInt(110), 20 + random
                    .nextInt(110), 20 + random.nextInt(110)));
            // 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
            g.drawString(rand, 13 * i + 6, 16);
        }
        // 图象生效
        g.dispose();
        //保存验证码到Session
        request.getSession().setAttribute("randStr", sRand);
        System.out.println(request.getSession().getAttribute("randStr"));
        try {
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (Exception e) {
            System.out.println("验证码图片产生出现错误：" + e.toString());
        }


    }
    public void checkYZM(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        String code = req.getParameter("code");
        if(code.equals((String) req.getSession().getAttribute("randStr"))){
            resp.getWriter().print("flag=true");
        }else{
            resp.getWriter().print("flag=false");
        }
    }
    public void userLogin(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        String ename = req.getParameter("ename");
        String pwd = req.getParameter("pwd");

        EmployeeService service = new EmpServiceImpl();
        Employee employee = service.selectOne(ename, pwd);
        if(employee != null){
            req.getSession().setAttribute("emp",employee);
            resp.getWriter().print("msg='ok'");
        }else{
            resp.getWriter().print("msg='error'");
        }
    }
    public void logout(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        req.getSession().invalidate();
        resp.getWriter().print("msg='ok'");
    }
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
