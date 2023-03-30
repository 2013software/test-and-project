package com.stadium.service;

import com.stadium.entity.Course;

import java.util.List;

public interface SelectCourseService {
    List<Course> getSelectCourseList();
    void selectNumber(Course course);
}
