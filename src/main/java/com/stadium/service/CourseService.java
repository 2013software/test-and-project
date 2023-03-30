package com.stadium.service;

import com.stadium.entity.Course;
import com.stadium.entity.Time;
import com.stadium.entity.User;
import com.stadium.entity.Venue;

import java.util.List;

public interface CourseService {
    List<Course> getCourseList(int userid);
    List<Course> getCourseList_Teacher(int userid);
    List<Course> getCourseList_Teacher_manage(int userid);
    List<Time> getTimeList();
    List<Venue> getVenueList_addcourse();
    void MinusNumber(String courseid);
    boolean addcourse(Course course);
    void cancelCourse(String id);
}
