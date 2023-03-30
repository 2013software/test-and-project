package com.stadium.service.impl;

import com.stadium.dao.SelectCourseMapper2;
import com.stadium.entity.Course;
import com.stadium.entity.Course_state;
import com.stadium.service.SelectCourseService2;
import com.stadium.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;


public class SelectCourseServiceImpl2 implements SelectCourseService2 {

    @Override
    public List<Course> getSelectCourseList2(int userid) {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)){
            SelectCourseMapper2 mapper = sqlSession.getMapper(SelectCourseMapper2.class);
            return  mapper.getSelectCourseList2(userid);
        }
    }

    @Override
    public void addCourse(Course_state state) {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)){
            SelectCourseMapper2 mapper = sqlSession.getMapper(SelectCourseMapper2.class);
            mapper.AddCourse(state);
        }
    }

//    @Override
//    public List<Course> getSelectCourseList() {
//        return null;
//    }


}

