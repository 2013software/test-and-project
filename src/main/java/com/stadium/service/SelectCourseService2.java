package com.stadium.service;

import com.stadium.entity.Course;
import com.stadium.entity.Course_state;

import java.util.List;

public interface SelectCourseService2 {
    List<Course> getSelectCourseList2(int userid);
    void addCourse(Course_state state);
}
