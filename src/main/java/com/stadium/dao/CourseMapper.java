package com.stadium.dao;

import com.stadium.entity.Course;
import com.stadium.entity.Time;
import com.stadium.entity.User;
import com.stadium.entity.Venue;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CourseMapper {

    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "username", property = "teacher"),
            @Result(column = "courseid", property = "course_id"),
            @Result(column = "course_name", property = "course_name"),
            @Result(column = "time", property = "time"),
            @Result(column = "venue_name", property = "location")
    })
    @Select("select * from course_state, course, venue, time,user " +
            "where course_state.courseid = course.id " +
            "and course.venueid = venue.id " +
            "and course.course_time = time.id  " +
            "and course.tid = user.userid " +
            "and course_state.userid = #{userid}")
    List<Course> getCourseList(int userid);


    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "course_name", property = "course_name"),
            @Result(column = "current_number", property = "current_number"),
    })
    @Select("select * from course where tid = #{userid}")
    List<Course> getCourseList_Teacher(int userid);

    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "course_name", property = "course_name"),
            @Result(column = "current_number", property = "current_number"),
            @Result(column = "time", property = "time"),
            @Result(column = "venue_name", property = "location")
    })
    @Select("select * from course, venue, time where course.venueid = venue.id and course.course_time = time.id and course.tid = #{userid}")
    List<Course> getCourseList_Teacher_manage(int userid);

    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "time", property = "time")
    })
    @Select("select * from time")
    List<Time> getTimeList();

    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "time", property = "time"),
            @Result(column = "venueid", property = "venueid")
    })
    @Select("select * from venue_state, time where venue_state.timeid = time.id")
    List<Time> getTimeList2(int userid);

    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "venue_name", property = "venue_name")
    })
    @Select("select * from venue")
    List<Venue> getVenueList_addcourse();

    @Delete("delete from course_state where id = #{id}")
    void CancelCourse(String id);

    @Update("update course set current_number = current_number-1 where id = #{id}")
    void MinusNumber(String courseid);

    @Select("select * from course where course_time = #{timeid} and venueid = #{venueid}")
    Course SelectByTimeVenue(Course course);

    @Insert("insert into course(tid,course_name,course_time,venueid,maximum_number,current_number) value(#{teacherid}, #{course_name}, #{timeid}, #{venueid}, #{maximum_number}, '0')")
    void Add_Course(Course course);

//    @Update("update time set current_number = current_number+1 where id = #{course_id}")
//    void select_time(Time time);
}
