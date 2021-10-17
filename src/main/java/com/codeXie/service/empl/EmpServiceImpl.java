package com.codeXie.service.empl;

import com.codeXie.mapper.EmployeeMapper;
import com.codeXie.pojo.EmpCondition;
import com.codeXie.pojo.Employee;
import com.codeXie.service.EmployeeService;
import com.codeXie.utils.DBUtil;
import com.codeXie.utils.PageBean;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public
class EmpServiceImpl implements EmployeeService {
    @Override
    public Employee selectOne(String ename, String pwd) {
        SqlSession sqlSession = DBUtil.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = mapper.selectOne(ename, pwd);
        DBUtil.closeAll();
        return employee;
    }

    @Override
    public int addEmp(Employee employee) {
        SqlSession sqlSession = DBUtil.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        int i = mapper.addEmp(employee);
        sqlSession.commit();
        DBUtil.closeAll();
        return i;
    }

    @Override
    public PageBean<Employee> selectPage(EmpCondition condition) {
        SqlSession sqlSession = DBUtil.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        PageBean<Employee> pageBean = new PageBean<>();
        pageBean.setIndex(condition.getIndex());
        pageBean.setSize(condition.getSize());

        int count = mapper.empCount(condition);
        pageBean.setTotalCount(count);
        condition.setStart(pageBean.getStartRow());
        List<Employee> employees = mapper.selectPage(condition);
        pageBean.setList(employees);
        DBUtil.closeAll();
        return pageBean;
    }

    @Override
    public List<Employee> selectManager() {
        SqlSession sqlSession = DBUtil.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> employees = mapper.selectManager();
        return employees;
    }

    public Employee selectUpdate(String empid){
        SqlSession sqlSession = DBUtil.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = mapper.selectUpdate(empid);
        DBUtil.closeAll();
        return employee;
    }

    @Override
    public int updateEmp(Employee employee) {
        SqlSession sqlSession = DBUtil.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        int i = mapper.updateEmp(employee);
        sqlSession.commit();
        DBUtil.closeAll();
        return i;
    }

    @Override
    public int delEmp(String empid) {
        SqlSession sqlSession = DBUtil.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        int i = mapper.delEmp(empid);
        sqlSession.commit();
        DBUtil.closeAll();
        return i;
    }

    @Override
    public int resetPwd(String empid) {
        SqlSession sqlSession = DBUtil.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        int i = mapper.resetPwd(empid);
        sqlSession.commit();
        DBUtil.closeAll();
        return i;
    }
}
