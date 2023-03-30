package com.stadium.entity;

import lombok.Data;

@Data
public class Course {
    int id;
    String teacher;
    int teacherid;
    int course_id;
    String course_name;
    String time;
    int timeid;
    String location;
    int venueid;
    int current_number;
    int maximum_number;
}
