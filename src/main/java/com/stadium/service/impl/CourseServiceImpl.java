package com.stadium.service.impl;

import com.stadium.dao.CourseMapper;
import com.stadium.entity.Course;
import com.stadium.entity.Time;
import com.stadium.entity.Venue;
import com.stadium.service.CourseService;
import com.stadium.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CourseServiceImpl implements CourseService {
    @Override
    public List<Course> getCourseList(int userid) {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)){
            CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
            return mapper.getCourseList(userid);
        }
    }

    @Override
    public List<Course> getCourseList_Teacher(int userid) {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)){
            CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
            return mapper.getCourseList_Teacher(userid);
        }
    }

    @Override
    public List<Course> getCourseList_Teacher_manage(int userid) {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)){
            CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
            return mapper.getCourseList_Teacher_manage(userid);
        }
    }

    @Override
    public void cancelCourse(String id) {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)){
            CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
            mapper.CancelCourse(id);
        }
    }

    @Override
    public void MinusNumber(String courseid) {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)){
            CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
            mapper.MinusNumber(courseid);
        }
    }

    @Override
    public List<Time> getTimeList() {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)){
            CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
            return mapper.getTimeList();
        }
    }

    @Override
    public List<Venue> getVenueList_addcourse() {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)){
            CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
            return mapper.getVenueList_addcourse();
        }
    }

    @Override
    public boolean addcourse(Course course) {
        try (SqlSession sqlSession = MybatisUtil.getSession(true)) {
            CourseMapper mapper = sqlSession.getMapper(CourseMapper.class);
            Course c = mapper.SelectByTimeVenue(course);
            if(c == null){
                mapper.Add_Course(course);
                sqlSession.commit();
            }
            sqlSession.close();
            return c == null;
        }
    }
}
