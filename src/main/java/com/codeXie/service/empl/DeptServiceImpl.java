package com.codeXie.service.empl;

import com.codeXie.mapper.DeptMapper;
import com.codeXie.pojo.Dept;
import com.codeXie.service.DeptService;
import com.codeXie.utils.DBUtil;
import com.codeXie.utils.PageBean;
import org.apache.ibatis.session.SqlSession;

import java.net.ResponseCache;
import java.util.List;

public class DeptServiceImpl implements DeptService {
    @Override
    public int addDept(Dept dept) {
        SqlSession sqlSession = DBUtil.getSqlSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        int i = mapper.addDept(dept);
        sqlSession.commit();
        DBUtil.closeAll();
        return i;
    }

    @Override
    public PageBean<Dept> selectPage(int index, int size) {
        SqlSession sqlSession = DBUtil.getSqlSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        PageBean<Dept> pageBean = new PageBean<>();
        pageBean.setIndex(index);
        pageBean.setSize(size);
        int count = mapper.count();
        pageBean.setTotalCount(count);
        List<Dept> depts = mapper.selectList(pageBean.getStartRow(), size);
        pageBean.setList(depts);
        DBUtil.closeAll();
        return pageBean;
    }

    @Override
    public int delDept(int deptno) {
        SqlSession sqlSession = DBUtil.getSqlSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        int i = mapper.deleteDept(deptno);
        sqlSession.commit();
        DBUtil.closeAll();
        return i;
    }

    public Dept selectOne(int deptno){
        SqlSession sqlSession = DBUtil.getSqlSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = mapper.selectOne(deptno);
        DBUtil.closeAll();
        return dept;
    }

    @Override
    public int update(Dept dept) {
        SqlSession sqlSession = DBUtil.getSqlSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        int i = mapper.updateDept(dept);
        sqlSession.commit();
        DBUtil.closeAll();
        return i;
    }

    @Override
    public List<Dept> selectAll() { ;
        SqlSession sqlSession = DBUtil.getSqlSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        List<Dept> depts = mapper.selectAll();
        DBUtil.closeAll();
        return depts;
    }
}
