package com.stadium.dao;

import com.stadium.entity.Course;
import com.stadium.entity.User;
import com.stadium.entity.Venue;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SelectCourseMapper {

    @Results({
            @Result(column = "username", property = "teacher"),
            @Result(column = "id", property = "course_id"),
            @Result(column = "course_name", property = "course_name"),
            @Result(column = "time", property = "time"),
            @Result(column = "current_number", property = "current_number"),
            @Result(column = "venue_name", property = "location")
    })
    @Select("select * from course, venue, time, user " +
            "where course.tid = user.userid " +
            "and course.venueid = venue.id " +
            "and course.course_time = time.id")
    List<Course> getSelectCourseList();

//    @Delete("delete from course_state where id = #{id}")
//    void CancelCourse(String id);
//    //    User getUser(@Param("username") String username, @Param("password") String password, @Param("identity") String identity);

    @Update("update course set current_number = current_number+1 where id = #{course_id}")
    void SelectNumber(Course course);
}
