package com.codeXie.service.empl;

import com.codeXie.mapper.DutyMapper;
import com.codeXie.pojo.Duty;
import com.codeXie.pojo.EmpCondition;
import com.codeXie.service.DutyService;
import com.codeXie.utils.DBUtil;
import com.codeXie.utils.PageBean;
import org.apache.ibatis.session.SqlSession;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DutyServiceImpl implements DutyService {
    @Override
    public int signIn(Duty duty) {
        SqlSession sqlSession = DBUtil.getSqlSession();
        DutyMapper mapper = sqlSession.getMapper(DutyMapper.class);
        int i = mapper.signInMapper(duty);
        sqlSession.commit();
        DBUtil.closeAll();
        return i;
    }

    @Override
    public int signOut(Duty duty) {
        SqlSession sqlSession = DBUtil.getSqlSession();
        DutyMapper mapper = sqlSession.getMapper(DutyMapper.class);
        int i = mapper.signOutMapper(duty);
        sqlSession.commit();
        DBUtil.closeAll();
        return i;
    }

    @Override
    public Duty selectOne(Duty duty) {
        SqlSession sqlSession = DBUtil.getSqlSession();
        DutyMapper mapper = sqlSession.getMapper(DutyMapper.class);
        Duty duty1 = mapper.selectOne(duty);
        sqlSession.commit();
        DBUtil.closeAll();
        return duty1;
    }

    @Override
    public PageBean<Duty> selectPage(EmpCondition condition) {
        //设置当前页码和单页记录条数
        PageBean<Duty> dutyPageBean = new PageBean<>();
        dutyPageBean.setIndex(condition.getIndex());
        dutyPageBean.setSize(condition.getSize());

        SqlSession sqlSession = DBUtil.getSqlSession();
        DutyMapper mapper = sqlSession.getMapper(DutyMapper.class);
        //查询总共多少条记录
        int total = mapper.selectCount();
        //设置总条数并自动更新总页数、开始行
        dutyPageBean.setTotalCount(total);
        //设置开始的记录编号
        condition.setStart(dutyPageBean.getStartRow());
        List<Duty> duties = mapper.dutyManageMapper(condition);
        dutyPageBean.setList(duties);
        return dutyPageBean;
    }

    @Override
    public List<Duty> selectAll() {
        SqlSession sqlSession = DBUtil.getSqlSession();
        DutyMapper mapper = sqlSession.getMapper(DutyMapper.class);
        List<Duty> duties = mapper.selectAll();
        DBUtil.closeAll();
        return duties;
    }

    @Override
    public PageBean<Duty> myDuty(EmpCondition condition) {
        //获取sqlsession以及mapper
        SqlSession sqlSession = DBUtil.getSqlSession();
        DutyMapper mapper = sqlSession.getMapper(DutyMapper.class);

        //创建pageBean并设置index、size、totalcount
        PageBean<Duty> pageBean = new PageBean<>();
        int count = mapper.myDutyCount(condition.getEmpid());
        pageBean.setIndex(condition.getIndex());
        pageBean.setSize(condition.getSize());
        pageBean.setTotalCount(count);

        //得到请求的数据并将其封装到pageBean中
        condition.setStart(pageBean.getStartRow());
        List<Duty> list = mapper.myDuty(condition);
        pageBean.setList(list);
        return pageBean;
    }
}
