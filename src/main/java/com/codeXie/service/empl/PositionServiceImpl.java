package com.codeXie.service.empl;

import com.codeXie.mapper.DeptMapper;
import com.codeXie.mapper.PositionMapper;
import com.codeXie.pojo.Dept;
import com.codeXie.pojo.Position;
import com.codeXie.service.PositionService;
import com.codeXie.utils.DBUtil;
import com.codeXie.utils.PageBean;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PositionServiceImpl implements PositionService {
    /**
     * 添加岗位
     * @param position
     * @return
     */
    @Override
    public int addPosition(Position position) {
        SqlSession sqlSession = DBUtil.getSqlSession();
        PositionMapper mapper = sqlSession.getMapper(PositionMapper.class);
        int i = mapper.addPosition(position);
        sqlSession.commit();
        DBUtil.closeAll();
        return i;
    }

    /**
     * 根据页码、单页记录数来查找岗位集合
     * @param index 页码
     * @param size 单页记录数
     * @return
     */
    @Override
    public PageBean<Position> selectPage(int index, int size) {
        PageBean<Position> PageBean = new PageBean<>();
        PageBean.setIndex(index);
        PageBean.setSize(size);
        SqlSession sqlSession = DBUtil.getSqlSession();
        PositionMapper mapper = sqlSession.getMapper(PositionMapper.class);
        int count = mapper.count();
        PageBean.setTotalCount(count);
        List<Position> positions = mapper.selectPage(PageBean.getStartRow(), size);
        PageBean.setList(positions);
        return PageBean;
    }

    @Override
    public int delPosition(int posid) {
        SqlSession sqlSession = DBUtil.getSqlSession();
        PositionMapper mapper = sqlSession.getMapper(PositionMapper.class);
        int i = mapper.deleteOne(posid);
        sqlSession.commit();
        DBUtil.closeAll();
        return i;
    }

    @Override
    public Position selectPos(int posid) {
        SqlSession sqlSession = DBUtil.getSqlSession();
        PositionMapper mapper = sqlSession.getMapper(PositionMapper.class);
        Position position = mapper.selectOne(posid);
        DBUtil.closeAll();
        return  position;
    }

    @Override
    public int updatePos(Position position) {
        SqlSession sqlSession = DBUtil.getSqlSession();
        PositionMapper mapper = sqlSession.getMapper(PositionMapper.class);
        int i = mapper.upDateOne(position);
        sqlSession.commit();
        DBUtil.closeAll();
        return i;
    }

    @Override
    public List<Position> selectAll() {
        SqlSession sqlSession = DBUtil.getSqlSession();
        PositionMapper mapper = sqlSession.getMapper(PositionMapper.class);
        List<Position> positions = mapper.selectAll();
        DBUtil.closeAll();
        return positions;
    }
}
