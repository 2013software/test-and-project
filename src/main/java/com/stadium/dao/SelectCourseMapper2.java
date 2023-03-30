package com.stadium.dao;

import com.stadium.entity.Course;
import com.stadium.entity.Course_state;
import com.stadium.entity.User;
import com.stadium.entity.Venue;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SelectCourseMapper2 {

    @Results({
            @Result(column = "username", property = "teacher"),
            @Result(column = "courseid", property = "course_id"),
            @Result(column = "course_name", property = "course_name"),
            @Result(column = "time", property = "time"),
            @Result(column = "current_number", property = "current_number"),
            @Result(column = "venue_name", property = "location")
    })
    @Select("select * from course_state, course, venue, time,user " +
            "where course_state.courseid = course.id " +
            "and course.venueid = venue.id " +
            "and course.course_time = time.id  " +
            "and course.tid = user.userid " +
            "and course_state.userid = #{userid}")
    List<Course> getSelectCourseList2(int userid);
//

    //    @Insert("delete from course_state where id = #{id}")
    @Insert("insert into course_state (userid,courseid) values ( #{userid}, #{courseid})")
    void AddCourse(Course_state state);
//    @Delete("delete from course_state where id = #{id}")
//    void CancelCourse(String id);
//    //    User getUser(@Param("username") String username, @Param("password") String password, @Param("identity") String identity);



}
