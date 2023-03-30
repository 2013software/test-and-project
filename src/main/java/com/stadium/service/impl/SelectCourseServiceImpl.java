package com.stadium.service.impl;

import com.stadium.dao.SelectCourseMapper;
import com.stadium.entity.Course;
import com.stadium.service.SelectCourseService;
import com.stadium.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;


public class SelectCourseServiceImpl implements SelectCourseService {

    @Override
    public List<Course> getSelectCourseList() {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)){
            SelectCourseMapper mapper = sqlSession.getMapper(SelectCourseMapper.class);
            return  mapper.getSelectCourseList();
        }
    }

    @Override
    public void selectNumber(Course course) {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            SelectCourseMapper mapper = sqlSession.getMapper(SelectCourseMapper.class);
            mapper.SelectNumber(course);
        }
    }

//    @Override
//    public void addCourse(String id) {
//        try (SqlSession sqlSession = MybatisUtil.getSession()){
//            CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
////            mapper.SelectCourse(course);
//        }
//    }
}
